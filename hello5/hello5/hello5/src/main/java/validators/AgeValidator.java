package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator("ageValidator")
public class AgeValidator implements Validator{
	
	public void validate(FacesContext context,UIComponent ui, Object value) {
		long numAge=(Long)value;
		if(numAge<=0) {
			FacesMessage msg=new FacesMessage("age must be greater than 0", null);
			throw new ValidatorException(msg);
		}
	}

}
