package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import dao.EmployeeDAO;
import entities.Department;
import entities.Employee;
import services.DepartmentService;
import services.EmployeeService;

@ManagedBean(name="employeeBean",eager=true)
@ViewScoped
public class EmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Department department;
	private int age;
	private String gender;
	private Date DOB;
	private static  List<Employee> employees ;
	private EmployeeService employeeService=new EmployeeService();
	private Employee employeeForEdit=new Employee();
	@PostConstruct
	public void init() {
		employees=employeeService.getEmployees();
	}
	int number=10;

	public List<Employee> getEmployees() {
		return employees;
	}
	public void checkNameDuplicated(FacesContext context, UIComponent component, Object value) throws ValidatorException	 {
		String nameStr=(String)value;
		boolean check=employeeService.checkNameDuplicated(nameStr);
		if(!check) {
			FacesMessage msg =
					new FacesMessage(null,
							"Name is duplicated");
			throw new ValidatorException(msg);
		}
	}
	public void addEmployee() {
		System.out.println("addEmployee");
		Employee employee = new Employee(name, age, gender, DOB, department);
		employeeService.addEmployee(employee);
		employees.add(employee);
		
	}
	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
		employeeService.deleteEmployee(employee);
	}

	public void editEmployee(Employee employee) {
		for(Employee e:employees) {
			e.setCanEdit(false);
		}
		employeeForEdit.setName(employee.getName());
		employeeForEdit.setAge(employee.getAge());
		employeeForEdit.setDOB(employee.getDOB());
		employeeForEdit.setGender(employee.getGender());
		employee.setCanEdit(true);
	}
	public void cancelEdit(Employee employee) {
		employee.setName(employeeForEdit.getName());
		employee.setAge(employeeForEdit.getAge());
		employee.setDOB(employeeForEdit.getDOB());
		employee.setGender(employeeForEdit.getGender());
		employee.setCanEdit(false);
	}
	public void saveEmployees(Employee employee) {
		employee.setDepartment(department);
		employeeService.saveEmployees(employee);
		employee.setCanEdit(false);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
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
	
}
