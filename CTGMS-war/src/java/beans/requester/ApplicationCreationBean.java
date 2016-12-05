/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.requester;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import control.ConferenceTravelGrantSystemLocal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userSubsystem.Requester;
import userSubsystem.RequesterTypeEnum;

/**
 *
 * @author user1
 */
@Named(value = "applicationCreationBean")
@RequestScoped
public class ApplicationCreationBean {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    private String title;
    private String status;
    private String description;

    private String conferenceName;
    private String conferenceWebsite;
    private Date conferenceStartDate;
    private Date conferenceEndDate;
    private String conferenceStartDateString;
    private String conferenceEndDateString;

    private double registrationAmount;
    private double transportationAmount;
    private double accomodationAmount;
    private double mealsAmount;

    /**
     * Creates a new instance of CreateApplication
     */
    public ApplicationCreationBean() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        try {
            conferenceStartDate = dateFormat.parse(conferenceStartDateString);
            conferenceEndDate = dateFormat.parse(conferenceEndDateString);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage("Invalid date format. Please use yyyy/mm/dd", "");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("conferenceStartDate", msg);
            return null;
        }
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Requester requester = (Requester) session.getAttribute("Requester");
        this.conferenceTravelGrantSystem.createGrantApplication(title, description, status, requester,
                conferenceName, conferenceWebsite, conferenceStartDate, conferenceEndDate,
                registrationAmount, transportationAmount, accomodationAmount, mealsAmount);

        //Redirect to main page
        return "/requester/RequesterScreen?faces-redirect=true";
    }

    /**
     * @return the registrationAmount
     */
    public double getRegistrationAmount() {
        return registrationAmount;
    }

    /**
     * @param registrationAmount the registrationAmount to set
     */
    public void setRegistrationAmount(double registrationAmount) {
        this.registrationAmount = registrationAmount;
    }

    /**
     * @return the transportationAmount
     */
    public double getTransportationAmount() {
        return transportationAmount;
    }

    /**
     * @param transportationAmount the transportationAmount to set
     */
    public void setTransportationAmount(double transportationAmount) {
        this.transportationAmount = transportationAmount;
    }

    /**
     * @return the accomodationAmount
     */
    public double getAccomodationAmount() {
        return accomodationAmount;
    }

    /**
     * @param accomodationAmount the accomodationAmount to set
     */
    public void setAccomodationAmount(double accomodationAmount) {
        this.accomodationAmount = accomodationAmount;
    }

    /**
     * @return the mealsAmount
     */
    public double getMealsAmount() {
        return mealsAmount;
    }

    /**
     * @param mealsAmount the mealsAmount to set
     */
    public void setMealsAmount(double mealsAmount) {
        this.mealsAmount = mealsAmount;
    }

    /**
     * @return the conferenceName
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * @param conferenceName the conferenceName to set
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    /**
     * @return the conferenceWebsite
     */
    public String getConferenceWebsite() {
        return conferenceWebsite;
    }

    /**
     * @param conferenceWebsite the conferenceWebsite to set
     */
    public void setConferenceWebsite(String conferenceWebsite) {
        this.conferenceWebsite = conferenceWebsite;
    }

    /**
     * @return the conferenceStartDate
     */
    public Date getConferenceStartDate() {
        return conferenceStartDate;
    }

    /**
     * @param conferenceStartDate the conferenceStartDate to set
     */
    public void setConferenceStartDate(Date conferenceStartDate) {
        this.conferenceStartDate = conferenceStartDate;
    }

    /**
     * @return the conferenceStartDateString
     */
    public String getConferenceStartDateString() {
        return conferenceStartDateString;
    }

    /**
     * @param conferenceStartDateString the conferenceStartDateString to set
     */
    public void setConferenceStartDateString(String conferenceStartDateString) {
        this.conferenceStartDateString = conferenceStartDateString;
    }

    /**
     * @return the conferenceEndDateString
     */
    public String getConferenceEndDateString() {
        return conferenceEndDateString;
    }

    /**
     * @param conferenceEndDateString the conferenceEndDateString to set
     */
    public void setConferenceEndDateString(String conferenceEndDateString) {
        this.conferenceEndDateString = conferenceEndDateString;
    }

}
