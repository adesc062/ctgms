/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adesc062
 */
@Stateless
public class UserFacade implements UserFacadeLocal {

    @PersistenceContext(unitName = "CTGMS-ejbPU")
    private EntityManager em;

    @Override
    public User login(String loginId, String unhashedPassword) {
        User user = em.find(User.class, loginId);
        if (user != null) {
             try {
                 // check password
                 byte[] salt = user.getSalt();
                 String saltString = new String(salt, "UTF-8");
                 String checkPass = saltString + unhashedPassword;
                 MessageDigest digest = MessageDigest.getInstance("SHA-256");
                 byte[] checkPassHash = digest.digest(checkPass.getBytes("UTF-8"));
                 if (Arrays.equals(checkPassHash, user.getHashedPassword())) {
                     //login ok - set user in session context
                     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                     session.setAttribute("User", user);
                     return user;
                 } else {
                    return null;
                 }
             } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                 Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         return null;
    }

    @Override
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] unhashedPassword,
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic, String bankAccountNumber,
            Requester.RequesterType requesterType) {
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
            Requester requester = new Requester(loginId, surname, givenNames, email, hashedPassword, salt, studentNumber, academicUnit,
                    program, sessionNumber, thesisTopic, bankAccountNumber, requesterType);
            //Persist user
            em.persist(requester);
            return true;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] unhashedPassword, String employeeNumber) {
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
            Supervisor supervisor = new Supervisor(loginId, surname, givenNames, email, hashedPassword, salt, employeeNumber);
            //Persist user
            em.persist(supervisor);
            return true;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
