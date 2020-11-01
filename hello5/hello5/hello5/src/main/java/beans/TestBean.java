package beans;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.NumberConverter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "testBean", eager = true)
@RequestScoped
public class TestBean {
	private String text;
	private String text2;
	private List<String> listString;
	private Date date;
	private int money;
	private NumberConverter numberConverter;
	public NumberConverter getNumberConverter() {
		return numberConverter;
	}
	public void setNumberConverter(NumberConverter numberConverter) {
		 numberConverter.setType("currency");
		 numberConverter.setCurrencySymbol("$");
		 this.numberConverter=numberConverter;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public Date getDate() {
		return date;
	}
	public TestBean() {
		listString=new ArrayList<String>();
		listString.add("output");
		listString.add("output2");
		date=new Date();
	}
	public List<String> getListString() {
		return listString;
	}
	public String getText() {
		return this.text;
	}
	public void setText(String s) {
		this.text=s;
	}
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public void makeSomeThing(AjaxBehaviorEvent ax ) {
		log("makeSOmeThing!!!!!!!!!!!!!");
	}
	public void log(Object object) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println("MyBean " + methodName + ": " + object);
    }
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String strValue=(String) value;
		if(strValue.length()<=5||strValue.length()>=12) {
			FacesMessage msg =
					new FacesMessage("Name min 5 character and max 8 character",
							"Name can not be empty");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(component.getClientId(context), msg);
			throw new ValidatorException(msg);
		}
	}
}
