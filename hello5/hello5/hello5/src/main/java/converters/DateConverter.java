package converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateConverter")
public class DateConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		Date date1 = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			date1 = dateFormat.parse(value);
		} catch (ParseException e) {
			FacesMessage errMsg = new FacesMessage("date format invalid",null);
			throw new ConverterException(errMsg);
			// TODO: handle exception
		}
		return date1;

	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate=dateFormat.format(value);
		System.out.println(strDate);
		return dateFormat.format(value);
	}
}
