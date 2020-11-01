package listeners;

import java.awt.event.ActionEvent;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionListener;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import entities.HelloWorld;
public class UserActionListener implements ActionListener, ValueChangeListener {
	
	@Override
	public void processAction(javax.faces.event.ActionEvent event) throws AbortProcessingException {
		HelloWorld str=(HelloWorld) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("helloWorld");
		str.setTxt("duong");
		
		
	}
	@Override
	public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
		// TODO Auto-generated method stub
		
	}
}
