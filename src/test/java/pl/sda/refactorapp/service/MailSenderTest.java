package pl.sda.refactorapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.Transport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class MailSenderTest {
    @Test
    void shouldSendMail() {
        //given
        final var mockedStaticTransport = Mockito.mockStatic(Transport.class);

        MailSender mailSender = new MailSender();
        String address = "test@test.pl";
        String subject = "TOPIC: Very important message";
        String message = "Thanks for ordering our products. Your order will be send very soon!";

        //when
        boolean isSent = mailSender.sendEmail(address, subject, message);

        //then
        assertTrue(isSent);
        mockedStaticTransport.verify(() -> Transport.send(any()));
    }
}
