package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.NegocioApplication;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;

import co.edu.uniquindio.women_entrepeneurs_api.dto.UserProfileDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;


@SpringBootTest(classes= NegocioApplication.class)
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    //@Sql("classpath:dataset.sql")
    public void getUserInfoTest(){
        try {
            UserProfileDTO userInfo = userService.getUserProfileInfo("optt.itudes@gmail.com");
            Assertions.assertTrue(userInfo != null);
            System.out.println(userInfo.toString());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            Assertions.fail("No se encontró el usuario");
        }


    }
}
