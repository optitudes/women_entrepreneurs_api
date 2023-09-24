package co.edu.uniquindio.women_entrepeneurs_api.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl {

    private final JavaMailSender javaMailSender;
    private final String from="turismoenlacordillera@test.com";

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendSimpleEmail(String email,String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(this.from);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(text);

            javaMailSender.send(message);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage()+e.getCause());
            return false;
        }

    }
    public boolean sendEmailVerification(String email,String username,String emailToken){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(this.from);
            message.setTo(email);
            message.setSubject("Email validation");
            message.setText(getEmailVerificationView(username,emailToken));

            javaMailSender.send(message);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage()+e.getCause());
            return false;
        }
    }

    private String getEmailVerificationView(String username, String emailToken) {
        return "<h1>Verificacion de correo!</h1>" +
                "Hola "+username+"! para validar tu correo electronico haz click <a href='turismoenlacordillera.com/user/verifyEmail/"+emailToken+"' target='_blank' >aqui</a>" ;
    }
}
