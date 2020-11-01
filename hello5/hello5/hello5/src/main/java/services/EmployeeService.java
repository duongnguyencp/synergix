package services;

import java.util.List;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import entities.Department;
import entities.Employee;

public class EmployeeService {
	private static  List<Employee> employees;
	private EmployeeDAO employeeDAO=new EmployeeDAO();
	public EmployeeService() {
		employees=employeeDAO.getListEmployee();
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);
		employees.add(employee);
		
	}
	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
		employeeDAO.deleteEmployee(employee.getId());
	}

	public void editEmployee(Employee employee) {
		for(Employee e:employees) {
			e.setCanEdit(false);
		}
		employee.setCanEdit(true);
	}
	public void cancelEdit(Employee employee) {
		employee.setCanEdit(false);
	}
	public void saveEmployees(Employee employee) {
		try {
			employees.set(employees.indexOf(employee),employee);
		}
		catch (Exception e) {
			employees.get(0);
			System.out.println();
		}
		employeeDAO.updateEmployee(employee.getId(), employee);
		cancelEdit(employee);
	}
	public boolean checkNameDuplicated(String name) {
		List<Employee> employees=employeeDAO.getListEmployee();
		for(Employee e:employees) {
			if(e.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
