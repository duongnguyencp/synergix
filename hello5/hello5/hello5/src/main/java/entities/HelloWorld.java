package entities;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name="helloWorld",eager=true)
@RequestScoped
public class HelloWorld {
	private String txt;
	private Date date;
	private Integer money;
	@PostConstruct
	public void init() {
		money=1000;
		txt="cde";
		date=new Date(1998,12,16);
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date=date;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getMessage() {
		return "helloworld";
	}
	public void updateTxt(ActionEvent evt) {
		txt="abc";
	}
	public void updateTxt(AjaxBehaviorEvent evt) {
		txt="abc";
	}
}
