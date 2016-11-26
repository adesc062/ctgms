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
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public Supervisor findSupervisorByName(String givenNames, String surname) {
        try {
            Query query = em.createQuery(
                    "SELECT s FROM Supervisor s"
                    + " WHERE s.givenNames = :givenNames AND s.surname = :surname"
                    + "LIMIT 1");
            query.setParameter("givenNames", givenNames);
            query.setParameter("surname", surname);
            List resultList = query.getResultList();
            return (Supervisor) resultList.get(0);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public User login(String loginId, String unhashedPassword) {
        User user = em.find(User.class, loginId);
        if (user != null) {
            try {
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
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] unhashedPassword,
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic, String bankAccountNumber,
            RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname) {
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
            Requester requester = new Requester(loginId, surname, givenNames, email, hashedPassword, salt, studentNumber, academicUnit,
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
