/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import javax.ejb.Stateless;

/**
 *
 * @author adesc062
 */
@Stateless
public class UserFacade implements UserFacadeLocal {

    @Override
    public User login(String loginId, String unhashedPassword) {
        throw new UnsupportedOperationException("Not supported yet XD."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] password, byte[] salt,
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic, String bankAccountNumber,
            Requester.RequesterType requesterType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUser(String loginId, String surname, String givenNames, String email, byte[] password, byte[] salt, String employeeNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
