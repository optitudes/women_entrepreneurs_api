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
}
