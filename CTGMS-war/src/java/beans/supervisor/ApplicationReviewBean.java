/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.supervisor;

import control.ConferenceTravelGrantSystemLocal;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexandre Seguin
 */
@Named(value = "applicationReviewBean")
@RequestScoped
public class ApplicationReviewBean {

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    private String title;
    private String status;//should be enum/set value
    private String conference;
    private String description;
    private String comments;
    private double registrationAmount;
    private double transportationAmount;
    private double accomodationAmount;
    private double mealsAmount;
    
    public void accept(){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //this.conferenceTravelGrantSystem.acceptApplication();
    }

    public void markIncomplete(){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //this.conferenceTravelGrantSystem.markApplicationIncomplete();
    }
    
    public void reject(){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //this.conferenceTravelGrantSystem.rejectApplication();
    }
    
    public ConferenceTravelGrantSystemLocal getConferenceTravelGrantSystem() {
        return conferenceTravelGrantSystem;
    }

    public void setConferenceTravelGrantSystem(ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem) {
        this.conferenceTravelGrantSystem = conferenceTravelGrantSystem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getRegistrationAmount() {
        return registrationAmount;
    }

    public void setRegistrationAmount(double registrationAmount) {
        this.registrationAmount = registrationAmount;
    }

    public double getTransportationAmount() {
        return transportationAmount;
    }

    public void setTransportationAmount(double transportationAmount) {
        this.transportationAmount = transportationAmount;
    }

    public double getAccomodationAmount() {
        return accomodationAmount;
    }

    public void setAccomodationAmount(double accomodationAmount) {
        this.accomodationAmount = accomodationAmount;
    }

    public double getMealsAmount() {
        return mealsAmount;
    }

    public void setMealsAmount(double mealsAmount) {
        this.mealsAmount = mealsAmount;
    }
    /**
     * Creates a new instance of ApplicationReviewBean
     */
    public ApplicationReviewBean() {
    }
    
}
