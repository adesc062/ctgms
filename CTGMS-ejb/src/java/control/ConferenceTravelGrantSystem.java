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
import applicationSubSystem.SupervisorRecommendation;
import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public String getRequesterName(GrantApplication grantApp) {
        return userFacade.getRequesterName(grantApp);
    }

    @Override
    public Supervisor findSupervisorByName(String supervisorGivenNames, String supervisorSurname){
        return userFacade.findSupervisorByName(supervisorGivenNames, supervisorSurname);
    }

    @Override
    public User signIn(String username, String unhashedPassword) {
        return userFacade.signIn(username, unhashedPassword);
    }

    @Override
    public ArrayList<GrantApplication> getApplicationsRequiringSupervisorAttention(Supervisor supervisor) {
        return applicationFacade.getListOfGrantApplicationsNeedingSupervisorApproval(supervisor);
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
        return userFacade.addUser(loginId, unhashedPassword, givenNames, surname, email, studentNumber, academicUnit, program, sessionNumber, thesisTopic, bankAccountNumber, requesterType, supervisorGivenNames, supervisorSurname);
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
    public boolean createGrantApplication(String title, String description, String status, Requester requester,
            String conferenceName, String conferenceWebsite, Date conferenceStartDate, Date conferenceEndDate,
            double registrationAmount, double transportationAmount, double accomodationAmount, double mealsAmount) {
        Conference conference = applicationFacade.findConference(conferenceName);
        if (conference == null) {
            conference = applicationFacade.createConference(conferenceName, conferenceWebsite, conferenceStartDate, conferenceEndDate);
        }
        applicationFacade.createGrantApplication(title, conference, description, requester,
                registrationAmount, transportationAmount, accomodationAmount, mealsAmount);
        String subject = "A new application is pending your approval";
        String text = "Requester " + requester.getGivenNames() + " " + requester.getSurname() + " has created a new application. Sign into the Conference Travel Grant System to review it.";
        String supervisorEmail = userFacade.getSupervisorEmail(requester);
        userFacade.emailUser(supervisorEmail, subject, text);
        return true;
    }

    @Override
    public boolean makeRecommendation(ApplicationStatusEnum status, Supervisor supervisor, String requestedChanges, GrantApplication application) {
        applicationFacade.setStatus(application, status);
        SupervisorRecommendation superRec = this.applicationFacade.createSupervisorRecommendation(supervisor, requestedChanges);
        applicationFacade.addSupervisorRecommendation(application, superRec);
        String requesterEmail = userFacade.getRequesterEmail(application);
        String subject = "One of your applications has been marked as ";
        switch (status) {
            case INCOMPLETE:
                subject = subject + "incomplete";
            case PENDING_FACULTY_APPROVAL:
                subject = subject + "approved";
            case REFUSED:
                subject = subject + "refused";
            default:
                break;             
        }      
        String text = "View your application by signing into the Conference Travel Grant System. Your supervisor left the following comments: ";
        text = text + requestedChanges;
        userFacade.emailUser(requesterEmail, subject, text);
        return true;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public boolean loginIdExists(String loginId) {
        return userFacade.loginIdExists(loginId);
    }

}
