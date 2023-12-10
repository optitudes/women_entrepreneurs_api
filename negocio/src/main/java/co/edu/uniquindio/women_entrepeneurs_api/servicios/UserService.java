package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserProfileDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;

import java.util.List;

public interface UserService {

    Boolean registerUser(UserRegisterDTO newUserInfo) throws Exception;

    User updateUser(User u) throws Exception;

    void deleteUser(int id)throws Exception;
    void verifyEmail(String email) throws Exception;
    void sendPasswordResetToken(String email) throws Exception;
    void resetPassword(String token,String newPassword) throws  Exception;
    void validateUserRegisterDTO(UserRegisterDTO user) throws Exception;
    List<User> listUsers() throws Exception;
    //metodo que obtiene la informacion del usuario para mostrarla en la vista del perfil
    UserProfileDTO getUserProfileInfo(String email) throws Exception;
    LoginResponseDTO login(LoginRequestDTO loginInfo) throws Exception;
}
