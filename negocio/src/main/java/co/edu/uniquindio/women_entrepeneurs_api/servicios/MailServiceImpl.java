package co.edu.uniquindio.women_entrepeneurs_api.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailServiceImpl {

    private final JavaMailSender javaMailSender;
    private final String from="turismoenlacordillera@test.com";

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendSimpleEmail(String email,String subject, String text) {
        return sendSingleSimpleMail(email,subject,text);
    }
    public boolean sendSingleSimpleMail(String email, String subject,String content){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(this.from);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(content);

            javaMailSender.send(message);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage()+e.getCause());
            return false;
        }

    }
    public boolean sendSingleHtmlMail(String email, String subject,String content){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(this.from);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true); // El segundo parámetro "true" indica que el contenido es HTML
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
            return false;
        }
    }
    public boolean sendMultipleHtmlMail(List<String> emails, String subject, String content) {
        for(String email:emails) sendSingleSimpleMail(email, subject, content);
        return true;
    }
    public boolean sendMicrositeNotificationClient(String email,String microSiteName,String ventureName,String status){
        String subject = "Notificación solicitud micrositio";
        String content = getMicroSiteNotificationClientView(microSiteName,ventureName,status);
        return sendSingleHtmlMail(email,subject,content);
    }
    public boolean sendMicrositeUpdatedSolicitudeNotificationClient(String email,String microSiteName,String comment,String status){
        String subject = "Notificación cambio de estado solicitud micrositio";
        String content = getMicrositeUpdatedSolicitudeNotificationClientView(microSiteName,comment,status);
        return sendSingleHtmlMail(email,subject,content);
    }
    public boolean sendMicrositeNotificationAdmin(List<String> email, String userName, String microSiteName, String ventureName){
        String subject = "Notificación solicitud micrositio";
        String content = getMicroSiteNotificationAdminView(microSiteName,ventureName,userName);
        return sendMultipleHtmlMail(email,subject,content);
    }
    public boolean sendPasswordResetToken(String email, String token){
        String subject = "Cambio de clave";
        String content = getPasswordResetTokenView(token);
        return sendSingleHtmlMail(email,subject,content);
    }

    private String getPasswordResetTokenView(String token) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Solicitud de cambio de clave</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table style=\"background-color: #520120; padding: 5px; text-align: center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgfg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #08403E; padding: 8px;\">\n" +
                "        <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"text-align: center; color: aliceblue;\">\n" +
                "                <h2>Solicitud de cambio de clave</h2>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"font-family: 'Montserrat', sans-serif; font-size: 20px; text-align: center; color: aliceblue;\">\n" +
                "                <p>\n" +
                "                  Se ha registrado la solicitud de cambio de clave \n"+
                "                </p>\n" +
                "                <p>\n" +
                "                  Para efectuar el cambio de clave  da clic en el botón.\n" +
                "                </p>\n" +
                "                <table style=\"margin: 0 auto;\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                  <tr>\n" +
                "                    <td>\n" +
                "                        <a href='turismoenlacordillera.com/RecoverPassword/"+token+"' style=\"font-size: 14px;background-color: #962B09; color: #fff; padding: 15px 20px; border-radius: 25px; cursor: pointer; display: block;\">Cambiar clave</a>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "                <div style=\"text-align: center;\">\n" +
                "                  <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgbg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "                </div>\n" +
                "                <p style=\"font-family: 'Montserrat', sans-serif; font-size: 12px; color: aliceblue;\">\n" +
                "                  WWW.TURISMOENLACORDILLERA.COM\n" +
                "                </p>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #520120; color: white; padding: 10px; text-align: center;\">\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Copyright © 2023 turismoenlacordillera, all rights reserved.\n" +
                "        </p>\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Este mensaje ha sido generado de forma automática, por favor no responder ni dirigir algún correo a este remitente.\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
    }

    private String getMicroSiteNotificationAdminView(String microSiteName, String ventureName, String userName) {
    return "<!DOCTYPE html>\n" +
            "<html lang=\"es\">\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "  <title>Solicitud de micrositio</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "    <tr>\n" +
            "      <td>\n" +
            "        <table style=\"background-color: #520120; padding: 5px; text-align: center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "          <tr>\n" +
            "            <td>\n" +
            "              <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgfg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td style=\"background-color: #08403E; padding: 8px;\">\n" +
            "        <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "          <tr>\n" +
            "            <td>\n" +
            "              <div style=\"text-align: center; color: aliceblue;\">\n" +
            "                <h2>Solicitud de micrositio</h2>\n" +
            "              </div>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "          <tr>\n" +
            "            <td>\n" +
            "              <div style=\"font-family: 'Montserrat', sans-serif; font-size: 20px; text-align: center; color: aliceblue;\">\n" +
            "                <p>\n" +
            "                  Se ha registrado la solicitud de un micrositio por el usuario <strong>"+userName+"</strong>\n" +
            "                </p>\n" +
            "                <p>\n" +
            "                  para el micrositio <strong>"+microSiteName+"</strong> de la microempresa <strong>"+ventureName+"</strong>.\n" +
            "                </p>\n" +
            "                <p>\n" +
            "                  Para ver más detalles ingresa a url o da clic en el botón.\n" +
            "                </p>\n" +
            "                <table style=\"margin: 0 auto;\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                  <tr>\n" +
            "                    <td>\n" +
            "                      <button style=\"background-color: #962B09; color: #fff; padding: 15px 20px; border-radius: 25px; cursor: pointer; display: block;\">\n" +
            "                        <span href='turismoenlacordillera.com' style=\"font-size: 14px;\">Visitar</span>\n" +
            "                      </button>\n" +
            "                    </td>\n" +
            "                  </tr>\n" +
            "                </table>\n" +
            "                <div style=\"text-align: center;\">\n" +
            "                  <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgbg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
            "                </div>\n" +
            "                <p style=\"font-family: 'Montserrat', sans-serif; font-size: 12px; color: aliceblue;\">\n" +
            "                  WWW.TURISMOENLACORDILLERA.COM\n" +
            "                </p>\n" +
            "              </div>\n" +
            "            </td>\n" +
            "          </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "      <td style=\"background-color: #520120; color: white; padding: 10px; text-align: center;\">\n" +
            "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
            "          Copyright © 2023 turismoenlacordillera, all rights reserved.\n" +
            "        </p>\n" +
            "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
            "          Este mensaje ha sido generado de forma automática, por favor no responder ni dirigir algún correo a este remitente.\n" +
            "        </p>\n" +
            "      </td>\n" +
            "    </tr>\n" +
            "  </table>\n" +
            "</body>\n" +
            "</html>";
    }
    private String getMicrositeUpdatedSolicitudeNotificationClientView(String microSiteName, String comment, String status){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Solicitud de micrositio</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table style=\"background-color: #520120; padding: 5px; text-align: center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgfg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #08403E; padding: 8px;\">\n" +
                "        <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"text-align: center; color: aliceblue;\">\n" +
                "                <h2>Cambio de estado solicitud de micrositio</h2>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"font-family: 'Montserrat', sans-serif; font-size: 20px; text-align: center; color: aliceblue;\">\n" +
                "                <p>\n" +
                "                  Su solicitud para el micrositio <strong>"+microSiteName+"</strong>.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                  Ha cambiado de estado a <strong>"+status+"</strong>.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                  Comentarios del admin:" +
                "                </p>\n" +
                                 "<p>\n" +comment+
                "                </p>\n" +
                "                <div style=\"text-align: center;\">\n" +
                "                  <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgbg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "                </div>\n" +
                "                <p style=\"font-family: 'Montserrat', sans-serif; font-size: 14px; color: aliceblue;\">\n" +
                "                  WWW.TURISMOENLACORDILLERA.COM\n" +
                "                </p>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #520120; color: white; padding: 10px; text-align: center;\">\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Copyright © 2023 turismoenlacordillera, all rights reserved.\n" +
                "        </p>\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Este mensaje ha sido generado de forma automática, por favor no responder ni dirigir algún correo a este remitente.\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
    }
    private String getMicroSiteNotificationClientView(String microSiteName, String ventureName, String status){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Solicitud de micrositio</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table style=\"background-color: #520120; padding: 5px; text-align: center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgfg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #08403E; padding: 8px;\">\n" +
                "        <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"text-align: center; color: aliceblue;\">\n" +
                "                <h2>Solicitud de micrositio</h2>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"font-family: 'Montserrat', sans-serif; font-size: 20px; text-align: center; color: aliceblue;\">\n" +
                "                <p>\n" +
                "                  Hemos recibido su solicitud para el micrositio <strong>"+microSiteName+"</strong> de su empresa <strong>"+ventureName+"</strong>.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                  Actualmente, su solicitud se encuentra en el estado <strong>"+status+"</strong>.\n" +
                "                </p>\n" +
                "                <p>\n" +
                "                  Le informaremos de cualquier avance en esta solicitud.\n" +
                "                </p>\n" +
                "                <div style=\"text-align: center;\">\n" +
                "                  <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgbg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "                </div>\n" +
                "                <p style=\"font-family: 'Montserrat', sans-serif; font-size: 14px; color: aliceblue;\">\n" +
                "                  WWW.TURISMOENLACORDILLERA.COM\n" +
                "                </p>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #520120; color: white; padding: 10px; text-align: center;\">\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Copyright © 2023 turismoenlacordillera, all rights reserved.\n" +
                "        </p>\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Este mensaje ha sido generado de forma automática, por favor no responder ni dirigir algún correo a este remitente.\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
    }

    public boolean sendEmailVerification(String email,String username,String emailToken){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(this.from);
            helper.setTo(email);
            helper.setSubject("Validación de correo");
            helper.setText(getEmailVerificationView(username, emailToken), true); // El segundo parámetro "true" indica que el contenido es HTML

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
            return false;
        }
    }

    private String getEmailVerificationView(String username, String emailToken) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <title>Verificación de correo</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "    <tr>\n" +
                "      <td>\n" +
                "        <table style=\"background-color: #520120; padding: 5px; text-align: center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgfg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #08403E; padding: 8px;\">\n" +
                "        <table role=\"presentation\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"text-align: center; color: aliceblue;\">\n" +
                "                <h2>Solicitud de cambio de clave</h2>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "            <td>\n" +
                "              <div style=\"font-family: 'Montserrat', sans-serif; font-size: 20px; text-align: center; color: aliceblue;\">\n" +
                "                <p>\n" +
                "                  Se ha registrado el usuario con nombre "+username+" con este correo\n"+
                "                </p>\n" +
                "                <p>\n" +
                "                  Para confirmar el registro da clic en el botón.\n" +
                "                </p>\n" +
                "                <table style=\"margin: 0 auto;\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                  <tr>\n" +
                "                    <td>\n" +
                "                        <a href='turismoenlacordillera.com/VerifyEmail/"+emailToken+"' style=\"font-size: 14px;background-color: #962B09; color: #fff; padding: 15px 20px; border-radius: 25px; cursor: pointer; display: block;\">Cambiar clave</a>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </table>\n" +
                "                <div style=\"text-align: center;\">\n" +
                "                  <img src=\"https://beta.api.turismoenlacordillera.com/api/appinfo/getAppImage/lgbg.png\" style=\"width: 100%; max-width: 150px; height: auto;\" />\n" +
                "                </div>\n" +
                "                <p style=\"font-family: 'Montserrat', sans-serif; font-size: 12px; color: aliceblue;\">\n" +
                "                  WWW.TURISMOENLACORDILLERA.COM\n" +
                "                </p>\n" +
                "              </div>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #520120; color: white; padding: 10px; text-align: center;\">\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Copyright © 2023 turismoenlacordillera, all rights reserved.\n" +
                "        </p>\n" +
                "        <p style=\"color: aliceblue; font-size: 12px;\">\n" +
                "          Este mensaje ha sido generado de forma automática, por favor no responder ni dirigir algún correo a este remitente.\n" +
                "        </p>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
    }
}
