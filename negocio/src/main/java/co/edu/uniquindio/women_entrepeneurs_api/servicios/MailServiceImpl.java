package co.edu.uniquindio.women_entrepeneurs_api.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

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
        return "<!doctype html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title>Validación de correo | CryptoLuck</title>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "        p{\n" +
                "            margin:10px 0;\n" +
                "            padding:0;\n" +
                "        }\n" +
                "        table{\n" +
                "            border-collapse:collapse;\n" +
                "        }\n" +
                "        h1,h2,h3,h4,h5,h6{\n" +
                "            display:block;\n" +
                "            margin:0;\n" +
                "            padding:0;\n" +
                "        }\n" +
                "        img,a img{\n" +
                "            border:0;\n" +
                "            height:auto;\n" +
                "            outline:none;\n" +
                "            text-decoration:none;\n" +
                "        }\n" +
                "        body,#bodyTable,#bodyCell{\n" +
                "            height:100%;\n" +
                "            margin:0;\n" +
                "            padding:0;\n" +
                "            width:100%;\n" +
                "        }\n" +
                "        .mcnPreviewText{\n" +
                "            display:none !important;\n" +
                "        }\n" +
                "        #outlook a{\n" +
                "            padding:0;\n" +
                "        }\n" +
                "        img{\n" +
                "            -ms-interpolation-mode:bicubic;\n" +
                "        }\n" +
                "        table{\n" +
                "            mso-table-lspace:0pt;\n" +
                "            mso-table-rspace:0pt;\n" +
                "        }\n" +
                "        .ReadMsgBody{\n" +
                "            width:100%;\n" +
                "        }\n" +
                "        .ExternalClass{\n" +
                "            width:100%;\n" +
                "        }\n" +
                "        p,a,li,td,blockquote{\n" +
                "            mso-line-height-rule:exactly;\n" +
                "        }\n" +
                "        a[href^=tel],a[href^=sms]{\n" +
                "            color:inherit;\n" +
                "            cursor:default;\n" +
                "            text-decoration:none;\n" +
                "        }\n" +
                "        p,a,li,td,body,table,blockquote{\n" +
                "            -ms-text-size-adjust:100%;\n" +
                "            -webkit-text-size-adjust:100%;\n" +
                "        }\n" +
                "        .ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{\n" +
                "            line-height:100%;\n" +
                "        }\n" +
                "        a[x-apple-data-detectors]{\n" +
                "            color:inherit !important;\n" +
                "            text-decoration:none !important;\n" +
                "            font-size:inherit !important;\n" +
                "            font-family:inherit !important;\n" +
                "            font-weight:inherit !important;\n" +
                "            line-height:inherit !important;\n" +
                "        }\n" +
                "        #bodyCell{\n" +
                "            padding:10px;\n" +
                "        }\n" +
                "        .templateContainer{\n" +
                "            max-width:600px !important;\n" +
                "        }\n" +
                "        a.mcnButton{\n" +
                "            display:block;\n" +
                "        }\n" +
                "        .mcnImage,.mcnRetinaImage{\n" +
                "            vertical-align:bottom;\n" +
                "        }\n" +
                "        .mcnTextContent{\n" +
                "            word-break:break-word;\n" +
                "        }\n" +
                "        .mcnTextContent img{\n" +
                "            height:auto !important;\n" +
                "        }\n" +
                "        .mcnDividerBlock{\n" +
                "            table-layout:fixed !important;\n" +
                "        }\n" +
                "    </style></head>\n" +
                "<body>\n" +
                "<center>\n" +
                "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" valign=\"top\" id=\"bodyCell\">\n" +
                "                <!-- BEGIN TEMPLATE // -->\n" +
                "                <!--[if (gte mso 9)|(IE)]>\n" +
                "                <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" style=\"width:600px;\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "                <![endif]-->\n" +
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\">\n" +
                "                    <tr>\n" +
                "                        <td valign=\"top\" id=\"templateHeader\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnCodeBlock\">\n" +
                "                                <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnTextBlockInner\">\n" +
                "                                        <div class=\"mcnTextContent\" style=\"background-color: #26b99a\"><p style=\"color:white; text-align: center;\"><strong>Validación de correo | turismoenlacordillera</strong></p></div>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td valign=\"top\" id=\"templateBody\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "                                            <tr>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "                                        <![endif]-->\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                                            <tbody><tr>\n" +
                "\n" +
                "                                                <td valign=\"top\" class=\"mcnTextContent\" style=\"padding: 0px 18px 9px; line-height: 200%; text-align: left;\">\n" +
                "\n" +
                "\t\t\t\t\t\t    <h1><span style=\"font-family:verdana,geneva,sans-serif\">Hola "+username+"</span></h1>\n" +
                "\t\t\t\t\t\t    <p style=\"text-align: left; line-height: 200%;\"><span style=\"font-family:verdana,geneva,sans-serif\">Turismoenlacordillera te informa que el link  para verificar tú  correo ha sido generado, para validarlo debes dar click al botón 'Validar Correo' .<br></span></p>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t    </tr>\n" +
                "\t\t\t\t\t    </tbody></tabl   e>\n" +
                "\t\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t<![endif]-->\n" +
                "\n" +
                "\t\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t<![endif]-->\n" +
                "\t\t\t\t    </td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t    </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnDividerBlock\" style=\"min-width:100%;\">\n" +
                "\t\t\t\t<tbody class=\"mcnDividerBlockOuter\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t    <td class=\"mcnDividerBlockInner\" style=\"min-width:100%; padding:18px;\">\n" +
                "\t\t\t\t\t<table class=\"mcnDividerContent\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width: 100%;border-top: 2px solid #EAEAEA;\">\n" +
                "\t\t\t\t\t    <tbody><tr>\n" +
                "\t\t\t\t\t\t<td>\n" +
                "\t\t\t\t\t\t    <span></span>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t    </tr>\n" +
                "\t\t\t\t\t    </tbody></table>\n" +
                "\t\t\t\t\t<!--\n" +
                "\t\t\t\t\t\t\t<td class=\"mcnDividerBlockInner\" style=\"padding: 18px;\">\n" +
                "\t\t\t\t\t\t\t<hr class=\"mcnDividerContent\" style=\"border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;\" />\n" +
                "\t\t\t\t\t-->\n" +
                "\t\t\t\t    </td>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t    </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                "\t\t\t\t<tbody class=\"mcnTextBlockOuter\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t    <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "\t\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t\t<table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "\t\t\t\t\t    <tr>\n" +
                "\t\t\t\t\t<![endif]-->\n" +
                "\n" +
                "\t\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t\t<td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t\t<![endif]-->\n" +
                "\t\t\t\t\t<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "\t\t\t\t\t    <tbody><tr>\n" +
                "\n" +
                "\t\t\t\t\t\t<td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\">\n" +
                "\n" +
                "\t\t\t\t\t\t    <div style=\"text-align: center;\"><span style=\"font-family:verdana,geneva,sans-serif\"><span style=\"font-size:18px\"><strong></strong></span></span><br>\n" +
                "\t\t\t\t\t\t\t&nbsp;</div>\n" +
                "\n" +
                "\t\t\t\t\t\t    <div><span style=\"font-family:verdana,geneva,sans-serif\">Link de validación</span></div>\n" +
                "\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </td>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </tr>\n" +
                "                                        </table>\n" +
                "                                        <![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnButtonBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnButtonBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td style=\"padding-top:0; padding-right:18px; padding-bottom:18px; padding-left:18px;\" valign=\"top\" align=\"center\" class=\"mcnButtonBlockInner\">\n" +
                "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"mcnButtonContentContainer\" style=\"border-collapse: separate !important;border-radius: 4px;background-color: #ED8E20;\">\n" +
                "                                            <tbody>\n" +
                "                                            <tr>\n" +
                "                                                <td align=\"center\" valign=\"middle\" class=\"mcnButtonContent\" style=\"font-family: Arial, &quot;Helvetica Neue&quot;, Helvetica, sans-serif; font-size: 16px; padding: 18px;\">\n" +
                "                                                    <a class=\"mcnButton \" title=\"Validación de correo\" href=\"turismoenlacordillera/user/register/"+emailToken+"\" style=\"font-weight: bold;letter-spacing: normal;line-height: 100%;text-align: center;text-decoration: none;color: #FFFFFF;\">Validar Correo</a>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnDividerBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnDividerBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"mcnDividerBlockInner\" style=\"min-width:100%; padding:18px;\">\n" +
                "                                        <table class=\"mcnDividerContent\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width: 100%;border-top: 2px solid #EAEAEA;\">\n" +
                "                                            <tbody><tr>\n" +
                "                                                <td>\n" +
                "                                                    <span></span>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--\n" +
                "                                                        <td class=\"mcnDividerBlockInner\" style=\"padding: 18px;\">\n" +
                "                                                        <hr class=\"mcnDividerContent\" style=\"border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;\" />\n" +
                "                                        -->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "                                            <tr>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "                                        <![endif]-->\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                                            <tbody><tr>\n" +
                "\n" +
                "                                                <td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\">\n" +
                "\n" +
                "                                                    <div style=\"text-align: center;\"><span style=\"font-family:verdana,geneva,sans-serif\"><span style=\"font-size:18px\"><strong>Recomendación</strong></span></span><br>\n" +
                "                                                        &nbsp;</div>\n" +
                "\n" +
                "                                                    <div><span style=\"font-family:verdana,geneva,sans-serif\">Una vez validado el correo te recomendamos activar la verificacón a dos pasos en el apartado mi cuenta.</span></div>\n" +
                "\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </td>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </tr>\n" +
                "                                        </table>\n" +
                "                                        <![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnDividerBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnDividerBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"mcnDividerBlockInner\" style=\"min-width:100%; padding:18px;\">\n" +
                "                                        <table class=\"mcnDividerContent\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width: 100%;border-top: 2px solid #EAEAEA;\">\n" +
                "                                            <tbody><tr>\n" +
                "                                                <td>\n" +
                "                                                    <span></span>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--\n" +
                "                                                        <td class=\"mcnDividerBlockInner\" style=\"padding: 18px;\">\n" +
                "                                                        <hr class=\"mcnDividerContent\" style=\"border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;\" />\n" +
                "                                        -->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnCodeBlock\">\n" +
                "                                <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnTextBlockInner\">\n" +
                "                                        <div class=\"mcnTextContent\">\n" +
                "                                            <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ED8E20\">\n" +
                "                                                <tbody><tr>\n" +
                "                                                    <td align=\"center\">\n" +
                "                                                        <img src=\"https://cdn.pixabay.com/photo/2013/04/01/03/45/cat-98359_1280.jpg\" alt=\"CryptoLuck logo\" style=\"display: block; margin-top: 10px;\" title=\"CryptoLuck logo\" width=\"77\" height=\"79\" class=\"mcnImage\">\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td align=\"center\" class=\"mcnTextContent\">\n" +
                "                                                        <p style=\"color: #ffffff; text-align: center;\">Copyright © 2023 turismoenlacordillera.com, All rights reserved.</p>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                </tbody></table>\n" +
                "                                        </div>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnBoxedTextBlock\" style=\"min-width:100%;\">\n" +
                "                                <!--[if gte mso 9]>\n" +
                "                                <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\n" +
                "                                <![endif]-->\n" +
                "                                <tbody class=\"mcnBoxedTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnBoxedTextBlockInner\">\n" +
                "\n" +
                "                                        <!--[if gte mso 9]>\n" +
                "                                        <td align=\"center\" valign=\"top\" \">\n" +
                "                                        <![endif]-->\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\" class=\"mcnBoxedTextContentContainer\">\n" +
                "                                            <tbody><tr>\n" +
                "\n" +
                "                                                <td style=\"padding-top:9px; padding-left:18px; padding-bottom:9px; padding-right:18px;\">\n" +
                "\n" +
                "                                                    <table border=\"0\" cellspacing=\"0\" class=\"mcnTextContentContainer\" width=\"100%\" style=\"min-width: 100% !important;background-color: #404040;\">\n" +
                "                                                        <tbody><tr>\n" +
                "                                                            <td valign=\"top\" class=\"mcnTextContent\" style=\"padding: 18px;color: #F2F2F2;font-family: Helvetica;font-size: 14px;font-weight: normal;text-align: center;\">\n" +
                "                                                                Si crees que este mensaje no es para ti, por favor ignora este mensaje.\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody></table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--[if gte mso 9]>\n" +
                "                                        </td>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if gte mso 9]>\n" +
                "                                        </tr>\n" +
                "                                        </table>\n" +
                "                                        <![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td valign=\"top\" id=\"templateFooter\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnFollowBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td align=\"center\" valign=\"top\" style=\"padding:9px\" class=\"mcnFollowBlockInner\">\n" +
                "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentContainer\" style=\"min-width:100%;\">\n" +
                "                                            <tbody><tr>\n" +
                "                                                <td align=\"center\" style=\"padding-left:9px;padding-right:9px;\">\n" +
                "                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\" class=\"mcnFollowContent\">\n" +
                "                                                        <tbody><tr>\n" +
                "                                                            <td align=\"center\" valign=\"top\" style=\"padding-top:9px; padding-right:9px; padding-left:9px;\">\n" +
                "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                                                                    <tbody><tr>\n" +
                "                                                                        <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                                                                <tr>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:inline;\">\n" +
                "                                                                                <tbody><tr>\n" +
                "                                                                                    <td valign=\"top\" style=\"padding-right:10px; padding-bottom:9px;\" class=\"mcnFollowContentItemContainer\">\n" +
                "                                                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentItem\">\n" +
                "                                                                                            <tbody><tr>\n" +
                "                                                                                                <td align=\"left\" valign=\"middle\" style=\"padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;\">\n" +
                "                                                                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\">\n" +
                "                                                                                                        <tbody><tr>\n" +
                "\n" +
                "                                                                                                            <td align=\"center\" valign=\"middle\" width=\"24\" class=\"mcnFollowIconContent\">\n" +
                "                                                                                                                <a href=\"https://www.facebook.com/cryptoLuck\" target=\"_blank\"><img src=\"https://cdn-images.mailchimp.com/icons/social-block-v2/color-facebook-48.png\" alt=\"CryptoLuck | Facebook\" style=\"display:block;\" height=\"24\" width=\"24\" class=\"\"></a>\n" +
                "                                                                                                            </td>\n" +
                "\n" +
                "\n" +
                "                                                                                                        </tr>\n" +
                "                                                                                                        </tbody></table>\n" +
                "                                                                                                </td>\n" +
                "                                                                                            </tr>\n" +
                "                                                                                            </tbody></table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                </tbody></table>\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </td>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:inline;\">\n" +
                "                                                                                <tbody><tr>\n" +
                "                                                                                    <td valign=\"top\" style=\"padding-right:10px; padding-bottom:9px;\" class=\"mcnFollowContentItemContainer\">\n" +
                "                                                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentItem\">\n" +
                "                                                                                            <tbody><tr>\n" +
                "                                                                                                <td align=\"left\" valign=\"middle\" style=\"padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;\">\n" +
                "                                                                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\">\n" +
                "                                                                                                        <tbody><tr>\n" +
                "\n" +
                "                                                                                                            <td align=\"center\" valign=\"middle\" width=\"24\" class=\"mcnFollowIconContent\">\n" +
                "                                                                                                                <a href=\"https://www.instagram.com/cryptoLuck/\" target=\"_blank\"><img src=\"https://cdn-images.mailchimp.com/icons/social-block-v2/color-instagram-48.png\" alt=\"CryptoLuck software | Instagram\" style=\"display:block;\" height=\"24\" width=\"24\" class=\"\"></a>\n" +
                "                                                                                                            </td>\n" +
                "\n" +
                "\n" +
                "                                                                                                        </tr>\n" +
                "                                                                                                        </tbody></table>\n" +
                "                                                                                                </td>\n" +
                "                                                                                            </tr>\n" +
                "                                                                                            </tbody></table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                </tbody></table>\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </td>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:inline;\">\n" +
                "                                                                                <tbody><tr>\n" +
                "                                                                                    <td valign=\"top\" style=\"padding-right:10px; padding-bottom:9px;\" class=\"mcnFollowContentItemContainer\">\n" +
                "                                                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentItem\">\n" +
                "                                                                                            <tbody><tr>\n" +
                "                                                                                                <td align=\"left\" valign=\"middle\" style=\"padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;\">\n" +
                "                                                                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\">\n" +
                "                                                                                                        <tbody><tr>\n" +
                "\n" +
                "                                                                                                            <td align=\"center\" valign=\"middle\" width=\"24\" class=\"mcnFollowIconContent\">\n" +
                "                                                                                                                <a href=\"https://twitter.com/maloca37393103\" target=\"_blank\"><img src=\"https://cdn-images.mailchimp.com/icons/social-block-v2/color-twitter-48.png\" alt=\"CryptoLuck software | Twitter\" style=\"display:block;\" height=\"24\" width=\"24\" class=\"\"></a>\n" +
                "                                                                                                            </td>\n" +
                "\n" +
                "\n" +
                "                                                                                                        </tr>\n" +
                "                                                                                                        </tbody></table>\n" +
                "                                                                                                </td>\n" +
                "                                                                                            </tr>\n" +
                "                                                                                            </tbody></table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                </tbody></table>\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </td>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:inline;\">\n" +
                "                                                                                <tbody><tr>\n" +
                "                                                                                    <td valign=\"top\" style=\"padding-right:10px; padding-bottom:9px;\" class=\"mcnFollowContentItemContainer\">\n" +
                "                                                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentItem\">\n" +
                "                                                                                            <tbody><tr>\n" +
                "                                                                                                <td align=\"left\" valign=\"middle\" style=\"padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;\">\n" +
                "                                                                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\">\n" +
                "                                                                                                        <tbody><tr>\n" +
                "\n" +
                "                                                                                                            <td align=\"center\" valign=\"middle\" width=\"24\" class=\"mcnFollowIconContent\">\n" +
                "                                                                                                                <a href=\"https://www.linkedin.com/in/maloca-software-7a9915209/\" target=\"_blank\"><img src=\"https://cdn-images.mailchimp.com/icons/social-block-v2/color-linkedin-48.png\" alt=\"CryptoLuck software | LinkedIn\" style=\"display:block;\" height=\"24\" width=\"24\" class=\"\"></a>\n" +
                "                                                                                                            </td>\n" +
                "\n" +
                "\n" +
                "                                                                                                        </tr>\n" +
                "                                                                                                        </tbody></table>\n" +
                "                                                                                                </td>\n" +
                "                                                                                            </tr>\n" +
                "                                                                                            </tbody></table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                </tbody></table>\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </td>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:inline;\">\n" +
                "                                                                                <tbody><tr>\n" +
                "                                                                                    <td valign=\"top\" style=\"padding-right:10px; padding-bottom:9px;\" class=\"mcnFollowContentItemContainer\">\n" +
                "                                                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentItem\">\n" +
                "                                                                                            <tbody><tr>\n" +
                "                                                                                                <td align=\"left\" valign=\"middle\" style=\"padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;\">\n" +
                "                                                                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\">\n" +
                "                                                                                                        <tbody><tr>\n" +
                "\n" +
                "                                                                                                            <td align=\"center\" valign=\"middle\" width=\"24\" class=\"mcnFollowIconContent\">\n" +
                "                                                                                                                <a href=\"mailto:contacto@maloca.co\" target=\"_blank\"><img src=\"https://cdn-images.mailchimp.com/icons/social-block-v2/color-forwardtofriend-48.png\" alt=\"Email maloca\" style=\"display:block;\" height=\"24\" width=\"24\" class=\"\"></a>\n" +
                "                                                                                                            </td>\n" +
                "\n" +
                "\n" +
                "                                                                                                        </tr>\n" +
                "                                                                                                        </tbody></table>\n" +
                "                                                                                                </td>\n" +
                "                                                                                            </tr>\n" +
                "                                                                                            </tbody></table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                </tbody></table>\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </td>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            <td align=\"center\" valign=\"top\">\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"display:inline;\">\n" +
                "                                                                                <tbody><tr>\n" +
                "                                                                                    <td valign=\"top\" style=\"padding-right:0; padding-bottom:9px;\" class=\"mcnFollowContentItemContainer\">\n" +
                "                                                                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnFollowContentItem\">\n" +
                "                                                                                            <tbody><tr>\n" +
                "                                                                                                <td align=\"left\" valign=\"middle\" style=\"padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;\">\n" +
                "                                                                                                    <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"\">\n" +
                "                                                                                                        <tbody><tr>\n" +
                "\n" +
                "                                                                                                            <td align=\"center\" valign=\"middle\" width=\"24\" class=\"mcnFollowIconContent\">\n" +
                "                                                                                                                <a href=\"https://www.maloca.co\" target=\"_blank\"><img src=\"https://cdn-images.mailchimp.com/icons/social-block-v2/color-link-48.png\" alt=\"CryptoLuck\" style=\"display:block;\" height=\"24\" width=\"24\" class=\"\"></a>\n" +
                "                                                                                                            </td>\n" +
                "\n" +
                "\n" +
                "                                                                                                        </tr>\n" +
                "                                                                                                        </tbody></table>\n" +
                "                                                                                                </td>\n" +
                "                                                                                            </tr>\n" +
                "                                                                                            </tbody></table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                </tbody></table>\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </td>\n" +
                "                                                                            <![endif]-->\n" +
                "\n" +
                "                                                                            <!--[if mso]>\n" +
                "                                                                            </tr>\n" +
                "                                                                            </table>\n" +
                "                                                                            <![endif]-->\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                    </tbody></table>\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                        </tbody></table>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "                                            <tr>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "                                        <![endif]-->\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                                            <tbody><tr>\n" +
                "\n" +
                "                                                <td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px; text-align: center\">\n" +
                "\n" +
                "                                                    Contáctanos al + (57) 000000\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </td>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </tr>\n" +
                "                                        </table>\n" +
                "                                        <![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnDividerBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnDividerBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"mcnDividerBlockInner\" style=\"min-width: 100%; padding: 10px 18px 25px;\">\n" +
                "                                        <table class=\"mcnDividerContent\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width: 100%;border-top: 2px solid #EEEEEE;\">\n" +
                "                                            <tbody><tr>\n" +
                "                                                <td>\n" +
                "                                                    <span></span>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--\n" +
                "                                                        <td class=\"mcnDividerBlockInner\" style=\"padding: 18px;\">\n" +
                "                                                        <hr class=\"mcnDividerContent\" style=\"border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;\" />\n" +
                "                                        -->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                "                                <tbody class=\"mcnTextBlockOuter\">\n" +
                "                                <tr>\n" +
                "                                    <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "                                            <tr>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        <td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "                                        <![endif]-->\n" +
                "                                        <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                                            <tbody><tr>\n" +
                "\n" +
                "                                                <td valign=\"top\" class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\">\n" +
                "\n" +
                "                                                    <em style=\"text-align: center;\">Copyright © 2023 turismoenlacordillera, All rights reserved.</em><br>\n" +
                "                                                    <br>\n" +
                "                                                    <em style=\"text-align: center;\">Este mensaje ha sido generado de forma automática, por favor no responder ni dirigir algún correo a este remitente.</em>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                            </tbody></table>\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </td>\n" +
                "                                        <![endif]-->\n" +
                "\n" +
                "                                        <!--[if mso]>\n" +
                "                                        </tr>\n" +
                "                                        </table>\n" +
                "                                        <![endif]-->\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                </tbody>\n" +
                "                            </table></td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                <!--[if (gte mso 9)|(IE)]>\n" +
                "                </td>\n" +
                "                </tr>\n" +
                "                </table>\n" +
                "                <![endif]-->\n" +
                "                <!-- // END TEMPLATE -->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</center>\n" +
                "</body>\n" +
                "</html>\n" ;
    }
}
