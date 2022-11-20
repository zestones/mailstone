package com.mail.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String getIndex() {

        System.out.println("Sending Email...");

        sendEmail();

        System.out.println("Done");

        return "/index";
    }

    void sendEmail() {

        // SENDER
        final String username = "idrissbenguezzou@gmail.com";
        final String password = "znjtzsrearitnnzw";

        Properties prop = new Properties();

        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("localhost"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("mailstone2022@gmail.com, idrissbenguezzou@gmail.com"));
            message.setSubject("Testing Gmail TLS");
            message.setText("Bonjour à vous Monsieur Ghilas,"
                    + "\n\n Je vous envoie ce mail depuis spring boot \n A l'heur ou je vous parle il est très tard et je vais dodo \n\n Bien cordialement, \nSpringBoot !!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
