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

/**
 *
 * @author adesc062
 */
@Entity
public class Requester extends User implements Serializable {
    
    public enum RequesterType {
        Masters, PhD, FastTrack
    } 

    private static final long serialVersionUID = 1L;
    private String studentNumber;
    private String academicUnit;
    private String program;
    private String sessionNumber;
    private String thesisTopic;
    private String bankAccountNumber;
    private RequesterType requesterType;
    
    public Requester(String loginId, String surname, String givenNames, String email, byte[] password, byte[] salt,
            String studentNumber, String academicUnit, String program, String sessionNumber, String thesisTopic, String bankAccountNumber, RequesterType requesterType) {        
        super(loginId, surname, givenNames, email, password, salt);
        this.studentNumber = studentNumber;
        this.academicUnit = academicUnit;
        this.program = program;
        this.sessionNumber = sessionNumber;
        this.thesisTopic = thesisTopic;
        this.bankAccountNumber = bankAccountNumber;
        this.requesterType = requesterType;
    }
    
    /**
     * @return the studentNumber
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * @param studentNumber the studentNumber to set
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * @return the academicUnit
     */
    public String getAcademicUnit() {
        return academicUnit;
    }

    /**
     * @param academicUnit the academicUnit to set
     */
    public void setAcademicUnit(String academicUnit) {
        this.academicUnit = academicUnit;
    }

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * @return the sessionNumber
     */
    public String getSessionNumber() {
        return sessionNumber;
    }

    /**
     * @param sessionNumber the sessionNumber to set
     */
    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    /**
     * @return the thesisTopic
     */
    public String getThesisTopic() {
        return thesisTopic;
    }

    /**
     * @param thesisTopic the thesisTopic to set
     */
    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    /**
     * @return the bankAccountNumber
     */
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * @param bankAccountNumber the bankAccountNumber to set
     */
    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    /**
     * @return the requesterType
     */
    public RequesterType getRequesterType() {
        return requesterType;
    }

    /**
     * @param requesterType the requesterType to set
     */
    public void setRequesterType(RequesterType requesterType) {
        this.requesterType = requesterType;
    }
}
