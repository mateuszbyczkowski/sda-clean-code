package pl.sda.refactorapp.service;

import pl.sda.refactorapp.annotation.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
public class MailSender {

    public boolean sendEmail(String address, String subj, String msg) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "MAIL_SMTP_HOST");
        prop.put("mail.smtp.port", "MAIL_SMTP_PORT");
        prop.put("mail.smtp.ssl.trust", "MAIL_SMTP_SSL_TRUST");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin", "admin");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@company.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(address));
            message.setSubject(subj);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
