package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.NegocioApplication;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


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
            mailService.sendMicrositeNotificationClient("optt.itudes@gmail.com","microName","ventureName","PENDIENTE");
            Assertions.assertTrue(true);

        }catch  (Exception e){
            e.printStackTrace();
            Assertions.fail("No se pudo enviar el correo");
        }
    }
    @Test
    public void sendUpdatedMicrositeSolicitudeNotificationClient(){
        try{
            mailService.sendMicrositeUpdatedSolicitudeNotificationClient("optt.itudes@gmail.com","microName","El nuevo estado se da gracias a las validaciones pertinentes en gerencia","PENDIENTE");
            Assertions.assertTrue(true);

        }catch  (Exception e){
            e.printStackTrace();
            Assertions.fail("No se pudo enviar el correo");
        }
    }
    @Test
    public void sendMicrositeNotificationAdmin(){
        try{
            List<String> admins = new ArrayList<>();
            admins.add("optt.itudes@gmail.com");
            mailService.sendMicrositeNotificationAdmin(admins,"juan sebastian rojas","micrositio","empresa");
            Assertions.assertTrue(true);

        }catch  (Exception e){
            e.printStackTrace();
            Assertions.fail("No se pudo enviar el correo");
        }
    }
    @Test
    public void sendMultipleHtmlMail(){
        List<String> emails = new ArrayList<>();
        emails.add("optt.itudes@gmail.com");emails.add("miasmadelvacio@gmail.com");
        try{
            mailService.sendMultipleHtmlMail(emails,"multiple emails","contenido de multiple emails");
            Assertions.assertTrue(true);

        }catch  (Exception e){
            e.printStackTrace();
            Assertions.fail("No se pudo enviar el correo");
        }
    }
}
