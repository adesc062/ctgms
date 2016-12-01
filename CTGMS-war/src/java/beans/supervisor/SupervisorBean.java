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

     
    /**
     * Creates a new instance of SupervisorBean
     */
    public SupervisorBean() {
        applicationsRequiringAttention = conferenceTravelGrantSystem.getApplicationsRequiringSupervisorAttention();
    }

    public ConferenceTravelGrantSystemLocal getConferenceTravelGrantSystem() {
        return conferenceTravelGrantSystem;
    }

    public void setConferenceTravelGrantSystem(ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem) {
        this.conferenceTravelGrantSystem = conferenceTravelGrantSystem;
    }
    
    
    public ArrayList<GrantApplication> getApplicationsRequiringAttention() {
        return applicationsRequiringAttention;
    }

    public void setApplicationsRequiringAttention(ArrayList<GrantApplication> applicationsRequiringAttention) {
        this.applicationsRequiringAttention = applicationsRequiringAttention;
    }
    
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.setAttribute("Requester", null);
        return "/SignInScreen?faces-redirect=true";
    }
}
