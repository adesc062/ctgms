/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import com.sun.mail.iap.Protocol;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author user1
 */
@Stateless
@LocalBean
public class EmailSender {

    private int port = 587;
    private String host = "smtp.gmail.com";
    private String from = "ctgmsTeamOne2016@gmail.com";
    private boolean auth = true;
    private String username = "ctgmsTeamOne2016@gmail.com";
    private String password = "ctgms2016";
    private String protocol = "SMTPS";
    private boolean debug = true;

    public void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        //props.put("mail.smtp.ssl.enable", true);

        props.put("mail.smtp.starttls.enable", true);

        Authenticator authenticator = null;
        if (auth) {
            props.put("mail.smtp.auth", true);
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
            Session session = Session.getInstance(props, authenticator);
            session.setDebug(debug);

            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                message.setRecipients(Message.RecipientType.TO, address);
                message.setSubject(subject);
                message.setSentDate(new Date());
                message.setText(body);
                Transport.send(message);
            } catch (MessagingException ex) {
                System.out.println();
            }

        }

    }

}
