package co.edu.uniquindio.women_entrepeneurs_api.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sebas@gmail.com");
        message.setTo("optt.itudes@gmail.com");
        message.setSubject("Asunto del correo");
        message.setText("Contenido del correo");

        javaMailSender.send(message);
    }
}
