package entities;

import java.io.Serializable;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
@Entity
@Table(name = "employee")
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@Column (name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column (name="name")
	private String name;
	@Column (name="age")
	private int age;
	@Column(name="gender")
	private String gender;
	@Column(name="DOB")
	private Date DOB;
	@ManyToOne
	private Department department;
	@Transient
	private boolean canEdit;
	@Version
	@Transient
	private Integer version;
	public Employee() {}
	
	public Employee(String name, int age, String gender, Date dOB, Department department) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		DOB = dOB;
		this.department = department;
	}

	public boolean getCanEdit() {
		return canEdit;
	}
	public void setCanEdit(boolean canEdit) {
		this.canEdit=canEdit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" "+this.age+" "+this.DOB+" "+this.gender+" "+this.department.getName();
	}
}