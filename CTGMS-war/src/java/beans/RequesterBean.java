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

/**
 *
 * @author Alexandre Seguin
 */
@Named(value = "requesterBean")
@RequestScoped
public class RequesterBean {
    
    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;

    public ConferenceTravelGrantSystemLocal getConferenceTravelGrantSystem() {
        return conferenceTravelGrantSystem;
    }

    public void setConferenceTravelGrantSystem(ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem) {
        this.conferenceTravelGrantSystem = conferenceTravelGrantSystem;
    }
    
    /**
     * Creates a new instance of requesterBean
     */
    public RequesterBean() {
    }
    
    public void createApplication() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //this.conferenceTravelGrantSystem.createApplication();
    }
    
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //this.conferenceTravelGrantSystem.logout();
    }
    
}
