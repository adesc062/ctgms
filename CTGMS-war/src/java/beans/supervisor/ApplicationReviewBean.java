/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.supervisor;

import applicationSubSystem.ApplicationStatusEnum;
import applicationSubSystem.ExpenseEntry;
import applicationSubSystem.ExpenseTypeEnum;
import applicationSubSystem.GrantApplication;
import control.ConferenceTravelGrantSystemLocal;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import userSubsystem.Requester;
import userSubsystem.Supervisor;

/**
 *
 * @author Alexandre Seguin
 */
@Named(value = "applicationReviewBean")
@RequestScoped
public class ApplicationReviewBean {

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    private GrantApplication grantApp;
    private String title;
    private String status;//should be enum/set value
    private String conference;
    private String description;
    private String comments;
    private String requesterName;
    private double registrationAmount;
    private double transportationAmount;
    private double accomodationAmount;
    private double mealsAmount;

    private boolean initialized = false;

    public void accept() {
        makeRecommendation(ApplicationStatusEnum.PENDING_FACULTY_APPROVAL);
    }

    public void markIncomplete() {
        makeRecommendation(ApplicationStatusEnum.INCOMPLETE);
    }

    public void refuse() {
        makeRecommendation(ApplicationStatusEnum.REFUSED);
    }

    public String makeRecommendation(ApplicationStatusEnum status) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        this.conferenceTravelGrantSystem.makeRecommendation(status, (Supervisor) session.getAttribute("Supervisor"),
                this.comments, (GrantApplication) session.getAttribute("GrantApp"));
        //return to view page
        return "/supervisor/SupervisorScreen?faces-redirect=true";
    }

    public String getTitle() {
        if (!initialized) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            grantApp = (GrantApplication) session.getAttribute("GrantApp");

            //Populate the ui from grantApp object
            this.conference = grantApp.getConference().getName();
            this.description = grantApp.getDescription();
            this.title = grantApp.getTitle();
            LinkedList<ExpenseEntry> exEnts = this.grantApp.getExpenses();
            for (int i = 0; i < exEnts.size(); i++) {
                ExpenseEntry expense = exEnts.get(i);
                if (null != expense.getExpenseType()) {
                    switch (expense.getExpenseType()) {
                        case Meals:
                            this.setMealsAmount(expense.getExpenseAmount());
                            break;
                        case Accomodation:
                            this.setAccomodationAmount(expense.getExpenseAmount());
                            break;
                        case Transportation:
                            this.setTransportationAmount(expense.getExpenseAmount());
                            break;
                        case Registration:
                            this.setRegistrationAmount(expense.getExpenseAmount());
                            break;
                        default:
                            break;
                    }
                }
            }
            //Requester req = grantApp.getRequester();
            this.requesterName = conferenceTravelGrantSystem.getRequesterName(grantApp);
            initialized = true;
        }
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
    public ApplicationReviewBean() {}

    /**
     * @return the requesterName
     */
    public String getRequesterName() {
        return requesterName;
    }

    /**
     * @param requesterName the requesterName to set
     */
    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }
}
