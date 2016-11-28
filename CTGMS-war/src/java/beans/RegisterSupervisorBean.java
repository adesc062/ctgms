/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import control.ConferenceTravelGrantSystemLocal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import userSubsystem.RequesterTypeEnum;

/**
 *
 * @author user1
 */
@Named(value = "registerSupervisorBean")
@RequestScoped
public class RegisterSupervisorBean {

    private RequesterTypeEnum studentTypes[] = RequesterTypeEnum.values();

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    
    private String loginId;
    private String password;
    private String givenNames;
    private String surname;
    private String email;
    private String employeeNumber;
    
    
    /**
     * Creates a new instance of CreateApplication
     */
    public RegisterSupervisorBean() {
    }

    public void submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        this.conferenceTravelGrantSystem.addUser(loginId, password, givenNames, surname, email, employeeNumber);
    }  

    /**
     * @return the studentTypes
     */
    public RequesterTypeEnum[] getStudentTypes() {
        return studentTypes;
    }

    /**
     * @param studentTypes the studentTypes to set
     */
    public void setStudentTypes(RequesterTypeEnum[] studentTypes) {
        this.studentTypes = studentTypes;
    }

    /**
     * @return the conferenceTravelGrantSystem
     */
    public ConferenceTravelGrantSystemLocal getConferenceTravelGrantSystem() {
        return conferenceTravelGrantSystem;
    }

    /**
     * @param conferenceTravelGrantSystem the conferenceTravelGrantSystem to set
     */
    public void setConferenceTravelGrantSystem(ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem) {
        this.conferenceTravelGrantSystem = conferenceTravelGrantSystem;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the givenNames
     */
    public String getGivenNames() {
        return givenNames;
    }

    /**
     * @param givenNames the givenNames to set
     */
    public void setGivenNames(String givenNames) {
        this.givenNames = givenNames;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
