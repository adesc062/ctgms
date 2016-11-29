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
import userSubsystem.Supervisor;
import userSubsystem.User;

/**
 *
 * @author user1
 */
@Named(value = "signInBean")
@RequestScoped
public class SignInBean {

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    
    private String loginId;
    private String password;
    
    /**
     * Creates a new instance of CreateApplication
     */
    public SignInBean() {
    }

    public String submit() {
        //FacesContext context = FacesContext.getCurrentInstance();
        //ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //this.conferenceTravelGrantSystem.createApplication(this.title, this.description, this.status, this.conference);
         User user = conferenceTravelGrantSystem.login(loginId, password);
         if(user.getClass().getName() != null){
              return "requesterScreen";
         }
        return "signin";
         //for testing the list return
        //conferenceTravelGrantSystem.getApplicationsRequiringSupervisorAttention();
    }
    
    public String createRequesterAccount() {
        return "RegisterRequesterScreen";
    }
    
    public String createSupervisorAccount() {
        return "RegisterSupervisorScreen";
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

}
