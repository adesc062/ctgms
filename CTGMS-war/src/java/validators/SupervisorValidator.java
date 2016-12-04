/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import control.ConferenceTravelGrantSystemLocal;
import static java.rmi.server.LogStream.log;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alexandre Seguin
 */
@FacesValidator("supervisorValidator")
public class SupervisorValidator implements Validator{
    
    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    log("Validating supervisor name -- " + value.toString());
    String supervisorGivenNames = value.toString();
    UIInput supervisorSurnameIn = (UIInput) context.getViewRoot().findComponent("supervisorSurnameIn");
    String supervisorSurname = (String)supervisorSurnameIn.getSubmittedValue();
    if(this.conferenceTravelGrantSystem.findSupervisorByName(supervisorGivenNames, supervisorSurname) == null){
      FacesMessage msg =
              new FacesMessage("Supervisor validation failed.",
              "Please provide a valid supervisor");
      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
    
      throw new ValidatorException(msg);
    }
  }
}
