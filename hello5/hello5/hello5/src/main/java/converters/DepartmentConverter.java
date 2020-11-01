package converters;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entities.Department;
import services.DepartmentService;
@ManagedBean(name = "departmentConverter") 
public class DepartmentConverter implements Converter {
	private DepartmentService departmentService;
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value==null||value.isEmpty())
			return null;
		try {
			departmentService=new DepartmentService();
			System.err.println(value);
			return departmentService.find(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw new ConverterException(new FacesMessage(String.format("% is not valid name Department",value)),e);
		}
	}
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		if(value==null)
			return "";
		if(value instanceof Department)
			return String.valueOf(((Department)value).getId());
		return "";
	}
}
