package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import co.edu.uniquindio.women_entrepeneurs_api.repo.LevelAccessRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.ProfileRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    private final LevelAccessRepo levelAccessRepo;
    private  MailServiceImpl mailService;


    public UserServiceImpl(UserRepo userRepo, LevelAccessRepo levelAccessRepo,ProfileRepo profileRepo, MailServiceImpl mailService) {
        this.userRepo = userRepo;
        this.levelAccessRepo = levelAccessRepo;
        this.profileRepo = profileRepo;
        this.mailService = mailService;
    }

    @Override
    public Boolean registerUser(UserRegisterDTO newUserInfo) throws Exception {

        Optional<User> searched = userRepo.findByEmail(newUserInfo.getEmail());
        if (searched.isPresent()){
            throw new Exception("El correo del usuario ya existe");
        }
        Optional<Profile> profileSearched = profileRepo.findByIdNumber(newUserInfo.getIdNumber());
        if (profileSearched.isPresent()){
            throw new Exception("La identificacion de usuario ya existe");
        }

        User newUser = new User();
        newUser.setEmail(newUserInfo.getEmail());
        newUser.setPassword(newUserInfo.getPassword());
        newUser.setIsActive(true);

        Profile newProfile = new Profile();
        newProfile.setIdNumber(newUserInfo.getIdNumber());
        newProfile.setNames(newUserInfo.getNames());
        newProfile.setLastNames(newUserInfo.getLastNames());
        newProfile.setUser(newUser);


        Optional<LevelAccess> levelAccess = levelAccessRepo.findByAccessCode(2);
        if (levelAccess.isPresent()){
            newUser.setLevelAccess(levelAccess.get());
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
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @Override
    public String[] login(LoginRequestDTO loginInfo) throws Exception {

        Optional<User> user = userRepo.findByEmailAndPassword(loginInfo.getEmail(),loginInfo.getPassword());
        if(user.isPresent()){
            String email = user.get().getEmail();
            return new String[]{email,null};
        }else{
            throw  new Exception(user.toString());
        }
    }

}
