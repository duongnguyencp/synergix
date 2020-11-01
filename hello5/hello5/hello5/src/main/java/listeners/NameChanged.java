package listeners;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

public class NameChanged implements ValueChangeListener{
	@Override
	public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
		if(null!=event.getNewValue()) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("name", event.getNewValue());
		}
		
	}

}
