/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userSubsystem;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author adesc062
 */
@Entity
@Table(name="Supervisors7972857")
public class Supervisor extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String employeeNumber;
    
    public Supervisor() {}
    
    public void setup(String loginId, String surname, String givenNames, String email, byte[] hashedPassword, byte[] salt,
            String employeeNumber) {        
        super.setup(loginId, surname, givenNames, email, hashedPassword, salt);
        this.employeeNumber = employeeNumber;
    }

    /**
     * @return the employeeNumber
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @param employeeNumber the employeeNumber to set
     */
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
