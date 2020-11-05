package services;

import java.util.List;

import entities.Employee;

public interface CrudService {
	public List<Employee> getEmployees();
	public void addEmployee(Employee employee);
	public void deleteEmployee(Employee employee) ;
	public void editEmployee(Employee employee) ;
	public void cancelEdit(Employee employee);
	public void saveEmployees(Employee employee) ;
	public boolean checkNameDuplicated(String name) ;
}
