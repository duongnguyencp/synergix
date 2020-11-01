package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

import services.EmployeeService;
@FacesValidator("NameValidator")
public class NameValidator implements Validator {
	public NameValidator() {
	}
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String strValue=(String) value;
		if(strValue.isEmpty()) {
			FacesMessage msg =
					new FacesMessage("Name can not be empty",
							"Name can not be empty");
			throw new ValidatorException(msg);
		}
		EmployeeService employeeService=new EmployeeService();
		boolean check=employeeService.checkNameDuplicated(strValue);
		System.out.println(check);
		if(check) {
			FacesMessage msg =
					new FacesMessage("Name is duplicated",
							null);
			throw new ValidatorException(msg);
		}
		
	}
}
