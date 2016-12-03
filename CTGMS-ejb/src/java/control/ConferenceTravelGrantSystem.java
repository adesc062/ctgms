/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import applicationSubSystem.ApplicationFacadeLocal;

import applicationSubSystem.ApplicationStatusEnum;

import applicationSubSystem.Conference;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import applicationSubSystem.GrantApplication;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.EJB;
import userSubsystem.Requester;
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
    private ApplicationFacadeLocal applicationFacade;

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
    public User login(String username, String unhashedPassword) {       
        return userFacade.login(username, unhashedPassword);
    }
    
    @Override
    public ArrayList<GrantApplication> getApplicationsRequiringSupervisorAttention(){
        //if user NOT supervisor they should not be calling this OR user null
        //May need to fix this so that it doesnt type cast a user
        return applicationFacade.getListOfGrantApplicationsNeedingSupervisorApproval((Supervisor) user);
    }

    /**
     * The method used for adding a REQUESTER
     * 
     * @param loginId
     * @param surname
     * @param givenNames
     * @param email
     * @param unhashedPassword
     * @param studentNumber
     * @param academicUnit
     * @param program
     * @param sessionNumber
     * @param thesisTopic
     * @param bankAccountNumber
     * @param requesterType
     * @param supervisorGivenNames
     * @param supervisorSurname
     * @return 
     */
    @Override
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, 
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic,
            String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname) {
       return userFacade.addUser(loginId, thesisTopic, givenNames, surname, email, studentNumber, academicUnit, program, sessionNumber, thesisTopic, bankAccountNumber, requesterType, supervisorGivenNames, supervisorSurname);
    }

    /**
     * The method used for adding a SUPERVISOR
     * 
     * @param loginId
     * @param unhashedPassword
     * @param givenNames
     * @param surname
     * @param email
     * @param employeeNumber
     * @return 
     */
    @Override
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, String employeeNumber) {
        return userFacade.addUser(loginId, unhashedPassword, givenNames, surname, email, employeeNumber);
    }

    @Override
    public boolean createGrantApplication(String title, String description, String status,
            String conferenceName, String conferenceWebsite, Date conferenceStartDate, Date conferenceEndDate,
            double registrationAmount, double transportationAmount, double accomodationAmount, double mealsAmount) {
        Conference conference = applicationFacade.findConference(conferenceName);
        if (conference == null) {
            conference = applicationFacade.createConference(conferenceName, conferenceWebsite, conferenceStartDate, conferenceEndDate);
        }
        applicationFacade.createGrantApplication(title, conference, description, (Requester) user,
                registrationAmount, transportationAmount, accomodationAmount, mealsAmount);
        return true;
    }

    @Override
    public boolean makeRecommendation(ApplicationStatusEnum status,Supervisor sup, String requestedChanges, GrantApplication application) {
       // this.applicationFacade.
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
