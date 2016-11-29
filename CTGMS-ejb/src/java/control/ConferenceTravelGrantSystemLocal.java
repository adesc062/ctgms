/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.Local;
import applicationSubSystem.GrantApplication;
import java.util.ArrayList;
import userSubsystem.RequesterTypeEnum;
import userSubsystem.Supervisor;
import userSubsystem.User;

/**
 *
 * @author user1
 */
@Local
public interface ConferenceTravelGrantSystemLocal {
    
    public User findUser(String username, String unhashedPassword);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, 
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic,
            String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, String employeeNumber);
    public boolean createApplication(String title, String description, String status, String conference);
    public boolean makeRecommendation(boolean isApproved, boolean isSigned, String requestedChanges, Supervisor supervisor, GrantApplication application);
    public ArrayList<GrantApplication> getApplicationsRequiringSupervisorAttention();
}
