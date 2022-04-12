package pl.sda.refactorapp.service.payment.strategy;

import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;

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
import java.math.BigDecimal;
import java.util.Properties;

public class BankTransferStrategy implements Payment {
    @Override
    public void pay(Customer customer, Order order) throws Exception {
        //verify is user correct
        var totalPrice = BigDecimal.ZERO;
        for (Item item : order.getItems()) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        var result = sendEmail(customer.getEmail(), "Please pay", "Pay your money on our bank account please. You have 3 days. The amount to pay is : " + totalPrice.add(order.getDeliveryCost()));

        if (!result) {
            throw new Exception("Payment failed! Try again later");
        }
    }

    private boolean sendEmail(String address, String subj, String msg) {
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
