/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import applicationSubSystem.GrantApplication;
import javax.ejb.Local;

/**
 *
 * @author adesc062
 */
@Local
public interface UserFacadeLocal {
       
    public User signIn(String loginId, String unhashedPassword);
    public byte[] findSalt(String loginId); 
    public String getRequesterName(GrantApplication grantApp);
    public Boolean loginIdExists(String loginId);
     public String getRequesterEmail(GrantApplication grantApp);
    public Supervisor findSupervisorByName(String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email, 
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic,
            String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email , String employeeNumber);
    public boolean emailUser(String email, String title, String message);
}
