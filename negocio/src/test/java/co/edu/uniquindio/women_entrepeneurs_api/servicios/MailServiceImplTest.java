package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.NegocioApplication;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;


@SpringBootTest(classes= NegocioApplication.class)
@Transactional
public class MailServiceImplTest {
    @Autowired
    private MailServiceImpl mailService;

    @Test
    //@Sql("classpath:dataset.sql")
    public void simpleMailTest(){
        try {
           mailService.sendSimpleEmail("optt.itudes@gmail.com","simple test","esto es un test muy simple");
            Assertions.assertTrue(true);
        }catch (Exception e){
            e.printStackTrace();
            Assertions.fail("No se pudo enviar el correo");
        }

    }
    @Test
    public void sendMicrositeNotificationClient(){
        try{
            mailService.sendMicrositeNotificationClient("optt.itudes@gmail.com","microName","ventureName","pendiente");
            Assertions.assertTrue(true);

        }catch  (Exception e){
            e.printStackTrace();
            Assertions.fail("No se pudo enviar el correo");
        }
    }
}
