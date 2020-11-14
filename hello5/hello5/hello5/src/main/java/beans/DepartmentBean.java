package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import entities.Department;
import entities.Employee;
import services.CrudService;
import services.DepartmentService;
import services.EmployeeQualifier;
import services.EmployeeService;

@Named
@ConversationScoped
public class DepartmentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String code;
	private static  List<Department> departments ;
	@Inject
	private Conversation conversation;
	 private EmployeeService employeeService=new EmployeeService();
	private DepartmentService departmentService=new DepartmentService();
	@PostConstruct
	public void init() {
		name="";
		description="";
		code="";
		departments=departmentService.getDepartments();
		initConversation();
	}
	public void initConversation() {
		boolean isTransient=conversation.isTransient();
		boolean isPostBack=FacesContext.getCurrentInstance().isPostback();
		if(!FacesContext.getCurrentInstance().isPostback()&&conversation.isTransient()) {
			conversation.begin();
		}
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
	public List<Employee> getEmployeeEachDepartment(String name) {
		return ((EmployeeService)employeeService).getEmployeeByDepartment(name);
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
