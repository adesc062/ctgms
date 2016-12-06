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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
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
    private String loginMessage;

    /**
     * Creates a new instance of CreateApplication
     */
    public SignInBean() {
        this.setLoginMessage("");
    }

    public String signIn() {
        User user = conferenceTravelGrantSystem.signIn(loginId, password);
         HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(user==null){
            this.setLoginMessage("INVALID LOGIN");
            return null;
        }
        else{
       session.setAttribute("Requester", null);
        session.setAttribute("Supervisor", null);
        if (user.getClass().getName().equals("userSubsystem.Requester")) {
            session.setAttribute("Requester", user);
            return "requester/RequesterScreen?faces-redirect=true";
        } else if (user.getClass().getName().equals("userSubsystem.Supervisor")) {
            session.setAttribute("Supervisor", user);
            return "supervisor/SupervisorScreen?faces-redirect=true";
        }
        return null;
        }
    }

    public String registerRequesterAccount() {
        return "RegisterRequesterScreen?faces-redirect=true";
    }

    public String registerSupervisorAccount() {
        return "RegisterSupervisorScreen?faces-redirect=true";
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

    /**
     * @return the loginMessage
     */
    public String getLoginMessage() {
        return loginMessage;
    }

    /**
     * @param loginMessage the loginMessage to set
     */
    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

}
