/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.supervisor;

import applicationSubSystem.GrantApplication;
import control.ConferenceTravelGrantSystemLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import userSubsystem.Supervisor;

/**
 *
 * @author Alexandre Seguin
 */
@Named(value = "supervisorBean")
@RequestScoped
public class SupervisorBean {
    
     @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;   
    private ArrayList<GrantApplication> applicationsRequiringAttention;
    private boolean applicationsInitialized = false;

    /**
     * Creates a new instance of SupervisorBean
     */
    public SupervisorBean() {}
    
    public ArrayList<GrantApplication> getApplicationsRequiringAttention() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Supervisor supervisor = (Supervisor) session.getAttribute("Supervisor");
        if (!applicationsInitialized) {
            applicationsRequiringAttention = conferenceTravelGrantSystem.getApplicationsRequiringSupervisorAttention(supervisor);
            applicationsInitialized = true;
        }
        return applicationsRequiringAttention;
    }

    public void setApplicationsRequiringAttention(ArrayList<GrantApplication> applicationsRequiringAttention) {
        this.applicationsRequiringAttention = applicationsRequiringAttention;
    }
    
    public String viewGrant(GrantApplication grant){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("GrantApp", grant);
        return "ApplicationReviewScreen";
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("Supervisor", null);
        return "/SignInScreen?faces-redirect=true";
    }
}
