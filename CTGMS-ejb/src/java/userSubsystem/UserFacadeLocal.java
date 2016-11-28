/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import javax.ejb.Local;

/**
 *
 * @author adesc062
 */
@Local
public interface UserFacadeLocal {
       
    public User login(String loginId, String unhashedPassword);
    public byte[] findSalt(String loginId); 
    public User findUser(String loginId, String unhashedPassword);
    public Supervisor findSupervisorByName(String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] unhashedPassword,
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic,
            String bankAccountNumber, RequesterTypeEnum requesterType, String supervisorGivenNames, String supervisorSurname);
    public boolean addUser(String loginId, String unhashedPassword, String givenNames, String surname, String email , String employeeNumber);
}
