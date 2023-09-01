package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.BillDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Product;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Users;

import java.util.List;

public interface UserService {

    Boolean registerUser(UserRegisterDTO newUserInfo) throws Exception;

    Users updateUser(Users u) throws Exception;

    void deleteUser(int id) throws Exception;

    List<Users> listUsers();

    List<String[]> listComments();

   List<Product> listFavorites(String email);
   String[] login(LoginRequestDTO loginInfo) throws Exception;
   List<BillDTO> getAllBills(String email) throws Exception;
}
