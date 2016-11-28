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
@Named(value = "applicationCreationBean")
@RequestScoped
public class ApplicationCreationBean {

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    private String title;
    private String status;//should be enum/set value
    private String conference;
    private String description;
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
     * @return the conference
     */
    public String getConference() {
        return conference;
    }

    /**
     * @param conference the conference to set
     */
    public void setConference(String conference) {
        this.conference = conference;
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

    public void submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        this.conferenceTravelGrantSystem.createApplication(this.title, this.description, this.status, this.conference);
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

}
