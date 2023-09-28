package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import co.edu.uniquindio.women_entrepeneurs_api.repo.LevelAccessRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final LevelAccessRepo levelAccessRepo;


    public UserServiceImpl(UserRepo userRepo, LevelAccessRepo levelAccessRepo) {
        this.userRepo = userRepo;
        this.levelAccessRepo = levelAccessRepo;
    }

    @Override
    public Boolean registerUser(UserRegisterDTO newUserInfo) throws Exception {

        Optional<User> searched = userRepo.findByEmail(newUserInfo.getEmail());
        if (searched.isPresent()){
            throw new Exception("El correo del usuario ya existe");
        }
        User newUser = new User();

        newUser.setId(1);
        newUser.setEmail(newUserInfo.getEmail());
        newUser.setPassword(newUserInfo.getPassword());
        newUser.setIsActive(true);

        Optional<LevelAccess> levelAccess = levelAccessRepo.findByAccessCode(2);
        if (levelAccess.isPresent()){
            newUser.setLevelAccess(levelAccess.get());
            return userRepo.save(newUser) != null;
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
    public LoginResponseDTO login(LoginRequestDTO loginInfo) throws Exception {

        Optional<User> user = userRepo.findByIdNumber(loginInfo.getIdNumber());

        if (user.isEmpty()) {
            throw new Exception("Usuario no existe");
        }

        User foundUser = user.get();
        validateUser(foundUser, loginInfo.getPassword());

        LoginResponseDTO responseDTO = new LoginResponseDTO(null,user.get().getEmail(),null);

        return responseDTO;

       /* if(user.isPresent()){
            String email = user.get().getEmail();
            return new String[]{email,null};
        }else{
            throw  new Exception(user.toString());
        }*/
    }
    private void validateUser(User user, String password) throws Exception {
        if (!user.getIsActive()) {
            throw new Exception("Usuario no está activo");
        }

        if (user.getEmailVerifiedAt() == null) {
            throw new Exception("El usuario no cuenta con correo electrónico verificado");
        }

        if (!user.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }
    }

}
