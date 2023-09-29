package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.NegocioApplication;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;


@SpringBootTest(classes= NegocioApplication.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    /*
    @Test
    public void registerUser(){
        User user = new User(1,"pan@test.com","pan","1234","119119119",323323323,"adders",true,null,null,new LevelAccess(),null,null,null);
        try{
            User userSaved = userService.registerUser(user);
            Assertions.assertNotNull(userSaved);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void removeUser(){
        try{
            userService.deleteUser(2);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }*/

    @Test
    @Sql("classpath:dataset.sql")
    public void loginUserTest(){
        LoginRequestDTO loginInfo = new LoginRequestDTO("123456789", "password");
        try {
            LoginResponseDTO user =userService.login(loginInfo);
            Assertions.assertEquals("johndoe@example.com", user.getEmail());
            System.out.println(user.getEmail());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            Assertions.fail("No se encontró el usuario");
        }


    }
}
