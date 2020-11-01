
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
@FacesValidator("myValidator")
public class MyValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException
    {
        System.out.println("MyValidator validate: " + value);
        String strVal=(String) value;
        if(strVal.equals("")) {
        	FacesMessage msg=new FacesMessage("khong duoc de trong",null);
        	context.addMessage(null, msg);
        	throw new ValidatorException(msg);
        }
    }

}