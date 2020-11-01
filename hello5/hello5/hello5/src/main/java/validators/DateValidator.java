package validators;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;
@FacesValidator("dateValidator")
public class DateValidator implements Validator{
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value==null) return;
		Date date1=(Date) value;
		Date date2=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		
		if(date1.after(date2)) {
			FacesMessage msg=new FacesMessage("DOB cannot be after date"+formatter.format(date2)+"\n",null);
			throw new ValidatorException(msg);
		}
	
	}


}
