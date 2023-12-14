package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserProfileDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import co.edu.uniquindio.women_entrepeneurs_api.repo.LevelAccessRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.ProfileRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    private final ProfileRepo profileRepo;

    private final LevelAccessRepo levelAccessRepo;
    private  MailServiceImpl mailService;


    public UserServiceImpl(UserRepo userRepo , LevelAccessRepo levelAccessRepo, ProfileRepo profileRepo, MailServiceImpl mailService) {
        this.userRepo = userRepo;
        this.levelAccessRepo = levelAccessRepo;
        this.profileRepo = profileRepo;
        this.mailService = mailService;
    }

    @Override
    public Boolean registerUser(UserRegisterDTO newUserInfo) throws Exception {

        validateUserRegisterDTO(newUserInfo);

        User newUser = new User();
        newUser.setEmail(newUserInfo.getEmail());
        newUser.setPassword(passwordEncoder.encode(newUserInfo.getPassword()));
        //temporal, luego se validara por correo
        newUser.setIsActive(true);

        Profile newProfile = new Profile();
        newProfile.setIdNumber(newUserInfo.getIdNumber());
        newProfile.setNames(newUserInfo.getNames());
        newProfile.setLastNames(newUserInfo.getLastNames());
        newProfile.setUser(newUser);


        Optional<LevelAccess> levelAccess = levelAccessRepo.findByAccessCode(3001);
        if (levelAccess.isPresent()){
            newUser.setLevelAccess(levelAccess.get()) ;
            userRepo.save(newUser);
            profileRepo.save(newProfile);
            mailService.sendEmailVerification(newUser.getEmail(),newProfile.getNames(), TokenUtils.encriptAES(newUser.getEmail()));
            return  true ;
        }else{
            throw new Exception("Ocurrió un error al asignar el nivel de acceso");
        }
    }

    @Override
    public User updateUser(User u) throws Exception {
        return userRepo.save(u);
    }

    @Override
    public void deleteUser(int id) throws Exception {
        userRepo.deleteById(id);
    }

    @Override
    public void verifyEmail(String email) throws Exception {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty())
            throw  new Exception("Usuario no encontrado");
        User user = userOptional.get();
        user.setEmailVerifiedAt(LocalDateTime.now());
        userRepo.save(user);
    }

    @Override
    public void sendPasswordResetToken(String email) throws Exception {
        Optional<User> userOptional = userRepo.findAvailableByEmail(email);
        if (userOptional.isEmpty())
            throw  new Exception("Usuario no disponible para cambio de clave");
        User user = userOptional.get();
        //
        String baseToken = user.getEmail()+"LocalDateTime"+LocalDateTime.now();
        mailService.sendPasswordResetToken(user.getEmail(),TokenUtils.encriptAES(baseToken));
    }

    @Override
    public void resetPassword(String token,String newPassword) throws Exception {
        String resetTokenInfo = TokenUtils.decryptAES(token);
        String[] resetTokenParts = resetTokenInfo.split("LocalDateTime");
        validateTokenTTL(resetTokenParts[1]);
        Optional<User> userOptional = userRepo.findAvailableByEmail(resetTokenParts[0]);
        if(userOptional.isEmpty())
            throw new Exception("Usuario no disponible para cambio de clave");
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    private void validateTokenTTL(String date) throws Exception {
        //a futuro se debe leer de la base de datos o de un
        //archivo de configuracion (se omite por temas de tiempo)
        int maxHours = 1;
        LocalDateTime tokenDate = LocalDateTime.parse(date);
        LocalDateTime currentDate = LocalDateTime.now();

        Duration timeElapsed = Duration.between(tokenDate,currentDate);
        if(timeElapsed.toHours()>maxHours)
            throw  new Exception("token expirado, genere uno nuevo");
    }

    @Override
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserProfileDTO getUserProfileInfo(String email) throws Exception {

        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()) {
            throw new Exception("Usuario no existe");
        }

        User foundUser = user.get();
        validateUser(foundUser);
        Profile profile = foundUser.getProfile();

        LevelAccess levelAccess = foundUser.getLevelAccess();
        UserProfileDTO userProfileInfo = new UserProfileDTO();
        //llenamos el dto con la info del usuario
        userProfileInfo.setImageUrl(profile.getPicture_url());
        userProfileInfo.setNames(profile.getNames());
        userProfileInfo.setLastNames(profile.getLastNames());
        userProfileInfo.setEmail(foundUser.getEmail());
        userProfileInfo.setAddress(profile.getAddress());
        userProfileInfo.setPhoneNumber(profile.getPhoneNumber());
        userProfileInfo.setIdNumber(profile.getIdNumber());
        userProfileInfo.setAccessName(levelAccess.getName());
        userProfileInfo.setAccessDescription(levelAccess.getDescription());


        return userProfileInfo;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginInfo) throws Exception {
        Optional<User> user = userRepo.findByIdNumber(loginInfo.getIdNumber());

        if (user.isEmpty()) {
            throw new Exception("Usuario no existe");
        }

        User foundUser = user.get();
        validateUser(foundUser);

        if (!passwordEncoder.matches(loginInfo.getPassword(),foundUser.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }
        LevelAccess levelAccess = foundUser.getLevelAccess();

        String userName = foundUser.getProfile().getNames();
        String userEmail = foundUser.getEmail();
        Integer accessCode = levelAccess.getAccessCode();
        String  accessDescription = levelAccess.getDescription();
        String accessName = levelAccess.getName();
        LoginResponseDTO responseDTO = new LoginResponseDTO(null,userEmail,accessCode,accessName,accessDescription,userName);

        return responseDTO;

    }
    private void validateUser(User user) throws Exception {
        if (!user.getIsActive()) {
            throw new Exception("Usuario no está activo");
        }

        if (user.getEmailVerifiedAt() == null) {
            throw new Exception("El usuario no cuenta con correo electrónico verificado");
        }

    }
    public void validateUserRegisterDTO(UserRegisterDTO userRegister) throws Exception {
        Optional<User> searched = userRepo.findByEmail(userRegister.getEmail());
        if (searched.isPresent()){
            throw new Exception("El correo del usuario ya existe");
        }
        Optional<Profile> profileSearched = profileRepo.findByIdNumber(userRegister.getIdNumber());
        if (profileSearched.isPresent()){
            throw new Exception("La identificacion de usuario ya existe");
        }
    }


}
