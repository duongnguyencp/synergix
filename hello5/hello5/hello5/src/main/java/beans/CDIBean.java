package beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Employee;
import services.CrudService;
import services.EmployeeQualifier;

@ApplicationScoped
@Named
public class CDIBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	@EmployeeQualifier
	private CrudService service;
	private String txt;
	@Inject Conversation conversation;
	private int increment;
	
	private EmployeeBean bean; 
	@PostConstruct
	public void init() {
		initConversation();
		increment=0;
		System.out.println("CDI Bean:init()");
	}
	public CDIBean() {
		System.out.println("CDI Bean:Constructor");
	}
	public String action() {
		System.out.println("CDI Bean: action()");
		return "cdiTest2?faces-redirect=true";
		
	}
	public String action2() {
		System.out.println("CDI Bean: action()");
		return "cdiTest3?faces-redirect=true";
		
	}
	public void initConversation() {
		if(!FacesContext.getCurrentInstance().isPostback()&&conversation.isTransient()) {
			conversation.begin();
			System.out.println("CDI Bean: initConversation()");
		}
	}
	public String endConversation() {
		if(!conversation.isTransient())
			conversation.end();
		return "cdiTest?faces-redirect=true";
	}
	public String takeIncrement() {
		
		increment++;
		return "";
	}
	public Conversation getConversation() {
		return conversation;
	}
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	@PreDestroy
	public void releaseResource() {
		System.out.println("CDI Bean: releaseResource()");
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public int getIncrement() {
		return increment;
	}
	public void setIncrement(int increment) {
		this.increment = increment;
	}
}
