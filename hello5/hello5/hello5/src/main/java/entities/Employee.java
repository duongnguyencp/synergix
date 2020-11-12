package entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "employee")
@NamedQuery(name = "findAllEmployeeByName", query = "SELECT emp FROM Employee emp where emp.name=:name")
@NamedQuery(name = "updateEmployee", query = "update Employee emp set emp.name=:name, emp.age=:age, emp.gender=:gender,emp.department=:department,emp.DOB=:DOB where emp.id=:id ")
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "DOB")
	private Date DOB;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private Department department;
	@Transient
	private boolean canEdit;
	@Version
	@Transient
	private Integer version;

	public Employee() {
	}

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
		this.canEdit = canEdit;
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
		return this.name + " " + this.age + " " + this.DOB + " " + this.gender + " " + this.department.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Employee))
			return false;
		return id != -1 && id == (((Employee) o).getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}