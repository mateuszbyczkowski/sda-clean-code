package exercises.s13.template.solution;

import pl.sda.refactorapp.annotation.Autowired;
import pl.sda.refactorapp.entity.Customer;
import pl.sda.refactorapp.entity.Item;
import pl.sda.refactorapp.entity.Order;
import pl.sda.refactorapp.service.CustomerService;

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
import java.util.Optional;
import java.util.Properties;

abstract public class DeliveryBase {

    @Autowired
    CustomerService customerService;

    abstract void deliverPackage();

    void deliver(Order order) {
        //calculate cost
        var tp = BigDecimal.ZERO; //total price
        var tw = 0; //total weight
        for (Item i : order.getItems()) {
            tp = tp.add(i.getPrice().multiply(new BigDecimal(i.getQuantity()))); // tp = tp + (i.price * i.quantity)
            tw += (i.getQuantity() * i.getWeight());
        }
        if (tp.compareTo(new BigDecimal(250)) > 0 && tw < 1) {
            order.setDeliveryCost(BigDecimal.ZERO);
        } else if (tw < 1) {
            order.setDeliveryCost(new BigDecimal(15));
        } else if (tw < 5) {
            order.setDeliveryCost(new BigDecimal(35));
        } else {
            order.setDeliveryCost(new BigDecimal(50));
        }

        deliverPackage();

        reportsStatus(order);
    }


    void reportsStatus(Order order) {
        Optional<Customer> optionalCustomer = customerService.findById(order.getCid());
        //reporting status
        sendEmail(optionalCustomer.get().getEmail(), "Delivery started", "We will deliver soon!");

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
