package entities;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "department")
public class Department  implements Serializable{
	private static final long serialVersionUID =  -1798070786993154676L;
	@Id 
	@Column (name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "department_code")
	private String code;
	@Column(name = "department_name")
	private String name;
	@Column(name = "department_description")
	private String description;
	@OneToMany(mappedBy="department",cascade=CascadeType.ALL)
    private List<Employee> employees;
	@Transient
	private boolean canEdit;
	public Department() {
		employees=new ArrayList<Employee>();
	}
	
	public Department(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}
	public void setCanEdit(boolean canEdit) {
		this.canEdit=canEdit;
	}
	
	public boolean getCanEdit() {
		return this.canEdit;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+" "+this.code+" "+this.description;
	}
	@Override
	public boolean equals(Object other) {
		// TODO Auto-generated method stub
		return (other != null && getClass() == other.getClass() && name != null)
	            ? name.equals(((Department) other).name)
	            : (other == this);
	}
	@Override
	public int hashCode() {
		return (name!=null)?(getClass().hashCode()+100):super.hashCode();
	}
}
	