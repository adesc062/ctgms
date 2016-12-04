/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import applicationSubSystem.GrantApplication;
import com.sun.mail.smtp.SMTPTransport;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author adesc062
 */
@Stateless
public class UserFacade implements UserFacadeLocal {

    @PersistenceContext(unitName = "CTGMS-ejbPU")
    private EntityManager em;

    //@Resource
    //private javax.transaction.UserTransaction utx;
    @Override
    public byte[] findSalt(String loginId) {
        try {
            Query query = em.createQuery(
                    "SELECT s FROM User s"
                    + " WHERE s.loginId = :loginId");
            query.setParameter("loginId", loginId);
            List resultList = query.getResultList();
            ArrayList<User> users = new ArrayList<User>();
            users.addAll(resultList);
            User user = users.get(0);
            System.out.println("POW");
            System.out.println(user.getSalt());
            return user.getSalt();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String getRequesterName(GrantApplication grantApp) {
        try {
            Query query = em.createQuery(
                    "SELECT r FROM Requester r"
                    + " JOIN GrantApplication gA"
                    + " WHERE gA = :grantApp");
            query.setParameter("grantApp", grantApp);
            List resultList = query.getResultList();
            ArrayList<Requester> requesters = new ArrayList<Requester>();
            requesters.addAll(resultList);
            Requester requester = requesters.get(0);
            return requester.getGivenNames() + " " + requester.getSurname();
        } catch (Exception e) {
        }
        return "failedTogetName";
    }

    @Override
    public String getRequesterEmail(GrantApplication grantApp) {
        GrantApplication dbGrantApp = em.find(GrantApplication.class, grantApp.getId());
        Requester requester = dbGrantApp.getRequester();
        return requester.getEmail();
        /*try {
            Query query = em.createQuery(
                    "SELECT r FROM Requester r"
                    + " JOIN GrantApplication gA"
                    + " WHERE gA = :grantApp");
            query.setParameter("grantApp", dbGrantApp);
            List resultList = query.getResultList();
            ArrayList<Requester> requesters = new ArrayList<Requester>();
            requesters.addAll(resultList);
            Requester requester = requesters.get(0);
            return requester.getEmail();
        } catch (Exception e) {
        }
        return "failedToGetEmail"; */
    }

    @Override
    public Supervisor findSupervisorByName(String givenNames, String surname) {
        try {
            Query query = em.createQuery(
                    "SELECT s FROM Supervisor s"
                    + " WHERE s.givenNames = :givenNames AND s.surname = :surname"
            );
            query.setParameter("givenNames", givenNames);
            query.setParameter("surname", surname);
            List resultList = query.getResultList();
            ArrayList<Supervisor> supervisors = new ArrayList<Supervisor>();
            supervisors.addAll(resultList);
            Supervisor sup = supervisors.get(0);
            return sup;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public User signIn(String loginId, String unhashedPassword) {
        User user = null;
        try {
            user = em.find(User.class, loginId);
        } catch (Exception e) {

        }
        if (user != null) {
            try {
                // utx.begin();
                //Check password validity
                byte[] salt = user.getSalt();
                String saltString = new String(salt, "UTF-8");
                String checkPass = saltString + unhashedPassword;
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] checkPassHash = digest.digest(checkPass.getBytes("UTF-8"));
                if (Arrays.equals(checkPassHash, user.getHashedPassword())) {
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                    session.setAttribute("User", user);
                    return user;
                } else {
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("TEST");
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email,
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic,
            String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname) {
        try {
            //Randomly generate salt value
            final Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            String saltString = new String(salt, "UTF-8");
            //Hash password using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedUnhashedPass = saltString + unhashedPassword;
            byte[] hashedPassword = digest.digest(saltedUnhashedPass.getBytes("UTF-8"));
            //Find supervisor
            Supervisor supervisor = findSupervisorByName(supervisorGivenNames, supervisorSurname);
            Requester requester = new Requester(loginId, hashedPassword, salt, givenNames, surname, email, studentNumber, academicUnit,
                    program, sessionNumber, thesisTopic, bankAccountNumber, requesterType, supervisor);
            //Persist user
            em.persist(requester);
            return true;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, String employeeNumber) {
        try {
            //Randomly generate salt value
            final Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            String saltString = new String(salt, "UTF-8");
            byte[] hashedPassword = this.hashPassword(unhashedPassword, saltString);
            Supervisor supervisor = new Supervisor();
            supervisor.setup(loginId, surname, givenNames, email, hashedPassword, salt, employeeNumber);
            //Persist user
            em.persist(supervisor);
            return true;
        } catch (UnsupportedEncodingException | RuntimeException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

    private byte[] hashPassword(String unhashedPassword, String salt) {
        try {
            //Hash password using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedUnhashedPass = salt + unhashedPassword;
            return digest.digest(saltedUnhashedPass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean emailUser(String recipentEmail, String subject, String text) {

        String from = "ctgsteamone2016@gmail.com";
        String host = "localhost";

        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthentication("ctgsteamone2016@gmail.com", "ctgms2016");

        try {
            email.setFrom(from);
            email.addTo(recipentEmail);
            email.setSubject(subject);
            email.setHtmlMsg(text);
            email.send();
            System.out.println("Email sent successfully42343");
        } catch (Exception e) {
            System.out.println("Error sending email23" + e);

        }

        /*
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        try {
            msg.setFrom(new InternetAddress("ctgmsTeamOne2016@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipentEmail, false));
            msg.setSubject(subject);
            msg.setText(text, "utf-8");
            msg.setSentDate(new Date());

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

            t.connect("smtp.gmail.com", "ctgmsTeamOne2016", "ctgms2016");
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            System.out.println("Email sent successfully");
        } catch (Exception e) {
            System.out.println("Error sending email");

        } */

 /*
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } */
        return true;
    }

}
