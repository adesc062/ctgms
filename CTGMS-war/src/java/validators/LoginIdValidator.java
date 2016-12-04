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
public class LoginIdValidator implements Validator {

    @EJB
    private ConferenceTravelGrantSystemLocal conferenceTravelGrantSystem;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log("Validating loginid uniqueness -- " + value.toString());
        String loginId = value.toString();
        
        if (this.conferenceTravelGrantSystem.loginIdExists(loginId)) {
            FacesMessage msg
                    = new FacesMessage("Login Id already exists.",
                            "Please pick a different login id");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
}
