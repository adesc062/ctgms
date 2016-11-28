/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import applicationSubSystem.GrantApplication;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import userSubsystem.RequesterTypeEnum;
import userSubsystem.Supervisor;
import userSubsystem.User;
import userSubsystem.UserFacadeLocal;

/**
 *
 * @author user1
 */
@Stateful
public class ConferenceTravelGrantSystem implements ConferenceTravelGrantSystemLocal {

    @EJB
    private UserFacadeLocal userFacade;

    @PersistenceContext(unitName = "CTGMS-ejbPU")
    private EntityManager em;
    
    
    
    User user;

    @Override
    public User findUser(String username, String unhashedPassword) {
        return userFacade.findUser(username, unhashedPassword);
    }

    @Override
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] unhashedPassword, String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic, String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, String employeeNumber) {
        return userFacade.addUser(loginId, unhashedPassword, givenNames, surname, email, employeeNumber);
    }

    @Override
    public boolean createApplication(String title, String description, String status, String conference) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean makeRecommendation(boolean isApproved, boolean isSigned, String requestedChanges, Supervisor supervisor, GrantApplication application) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
