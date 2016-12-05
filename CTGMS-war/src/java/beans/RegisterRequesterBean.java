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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.swing.JRadioButton;
import userSubsystem.RequesterTypeEnum;

/**
 *
 * @author user1
 */
@Named(value = "registerRequesterBean")
@RequestScoped
public class RegisterRequesterBean {

    private RequesterTypeEnum studentTypes[] = RequesterTypeEnum.values();

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;

    private String loginId;
    private String password;
    private String givenNames;
    private String surname;
    private String email;
    private String studentNumber;
    private String academicUnit;
    private String program;
    private String sessionNumber;
    private String thesisTopic;
    private String bankAccountNumber;
    private RequesterTypeEnum studentType;
    private String supervisorGivenNames;
    private String supervisorSurname;
    private boolean isMasters;

    //private UIComponent mybutton;

    /**
     * Creates a new instance of CreateApplication
     */
    public RegisterRequesterBean() {
    }

    public String submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        if (this.conferenceTravelGrantSystem.findSupervisorByName(supervisorGivenNames, supervisorSurname) == null) {
            FacesMessage msg = new FacesMessage("Supervisor validation failed. Please provide a valid supervisor.", "");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("supervisorGivenNamesIn", msg);
            return null;
        }
        this.conferenceTravelGrantSystem.addUser(loginId, password, givenNames, surname, email,
                studentNumber, academicUnit, program, sessionNumber, thesisTopic, bankAccountNumber, studentType,
                supervisorGivenNames, supervisorSurname);
        return "/SignInScreen?faces-redirect=true";
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
     * @return the studentType
     */
    public RequesterTypeEnum getStudentType() {
        return studentType;
    }

    /**
     * @param studentType the studentType to set
     */
    public void setStudentType(RequesterTypeEnum studentType) {
        this.studentType = studentType;
    }

    /**
     * @return the supervisorGivenNames
     */
    public String getSupervisorGivenNames() {
        return supervisorGivenNames;
    }

    /**
     * @param supervisorGivenNames the supervisorGivenNames to set
     */
    public void setSupervisorGivenNames(String supervisorGivenNames) {
        this.supervisorGivenNames = supervisorGivenNames;
    }

    /**
     * @return the supervisorSurname
     */
    public String getSupervisorSurname() {
        return supervisorSurname;
    }

    /**
     * @param supervisorSurname the supervisorSurname to set
     */
    public void setSupervisorSurname(String supervisorSurname) {
        this.supervisorSurname = supervisorSurname;
    }

    /**
     * @return the isMasters
     */
    public boolean isIsMasters() {
        return isMasters;
    }

    /**
     * @param isMasters the isMasters to set
     */
    public void setIsMasters(boolean isMasters) {
        this.isMasters = isMasters;
    }

}
