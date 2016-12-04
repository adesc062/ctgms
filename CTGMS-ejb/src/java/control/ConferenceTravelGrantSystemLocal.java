/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import applicationSubSystem.ApplicationStatusEnum;
import javax.ejb.Local;
import applicationSubSystem.GrantApplication;
import java.util.ArrayList;
import java.util.Date;
import userSubsystem.Requester;
import userSubsystem.RequesterTypeEnum;
import userSubsystem.Supervisor;
import userSubsystem.User;

/**
 *
 * @author user1
 */
@Local
public interface ConferenceTravelGrantSystemLocal {
    
    public User signIn(String username, String unhashedPassword);
    public String getRequesterName(GrantApplication grantApp);
    public Supervisor findSupervisorByName(String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, 
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic,
            String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, String employeeNumber);
    public boolean createGrantApplication(String title, String description, String status, Requester requester,
            String conferenceName, String conferenceWebsite, Date conferenceStartDate, Date conferenceEndDate,
            double registrationAmount, double transportationAmount, double accomodationAmount, double mealsAmount);
    public boolean makeRecommendation(ApplicationStatusEnum status,Supervisor sup, String requestedChanges, GrantApplication application);
    public ArrayList<GrantApplication> getApplicationsRequiringSupervisorAttention(Supervisor supervisor);
}
