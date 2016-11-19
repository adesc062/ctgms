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
 * @author user1
 */
@Named(value = "createApplication")
@RequestScoped
public class CreateApplication {

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
 private String title;
 private String status;//should be enum/set value
 private String conference;
 private String description;
         
    /**
     * Creates a new instance of CreateApplication
     */
    public CreateApplication() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return "test";
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
    public void process(){
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        this.conferenceTravelGrantSystem.createApplication(this.title, this.description, this.status, this.conference);
    }
}
