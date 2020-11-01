package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import dao.DepartmentDAO;
import entities.Department;
import entities.Employee;
import services.DepartmentService;

@ManagedBean(name="departmentBean",eager=true)
@ViewScoped
public class DepartmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String code;
	private static  List<Department> departments ;
	DepartmentDAO departmentDao=new DepartmentDAO();
	private DepartmentService departmentService=new DepartmentService();
	@PostConstruct
	public void init() {
		name="";
		description="";
		code="";
		departments=departmentDao.getListDepartment();
	}
	public List<Department> getDepartments() {
		return departmentService.getDepartments();
	}
	public void addDepartment() {
		Department department = new Department(code,name,description);
		departmentService.addDepartment(department);
		
	}
	public void deleteDepartment(Department department) {
		departmentService.deleteDepartment(department);
	}

	public void editDepartment(Department department) {
		departmentService.editDepartment(department);
	}
	public void cancelEdit(Department department) {
		departmentService.cancelEdit(department);
	}
	public void saveDepartments(Department department) {
		departmentService.saveDepartments(department);
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
