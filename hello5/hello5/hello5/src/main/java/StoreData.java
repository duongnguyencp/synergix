import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import entities.*;
import util.HibernateUtil;

public class StoreData {
	private static Session session;

	public static void main(String[] args) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Department department = new Department();
		department.setCode("00department");
		department.setName("Marketing");
		department.setDescription("good");
		Employee employee = new Employee();
		employee.setAge(15);
		employee.setGender("Nam");
		employee.setName("Jack");
		employee.setDOB("16/02/1998");
		Employee employee2 = new Employee();
		employee2.setAge(15);
		employee2.setGender("Nam");
		employee2.setName("Thomas");
		employee2.setDOB("16/02/1998");
		Set<Employee> employeesOfFirstDepartment = new HashSet<Employee>();
		employeesOfFirstDepartment.add(employee);
		employeesOfFirstDepartment.add(employee2);
		department.setEmployees(employeesOfFirstDepartment);
		session.save(department);
		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}

	public void listEmployees() {
		List<Employee> employees = session.createQuery("FROM employee").list();
		for (Iterator<Employee> iterator1 = employees.iterator(); iterator1.hasNext();) {
			Employee employee = (Employee) iterator1.next();
			System.out.print("name: " + employee.toString());
		}
		session.getTransaction().commit();
	}
	public void updateEmployee(Integer EmployeeID, int age ){
	         session.beginTransaction();
	         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
	         employee.setAge(age);
	         session.update(employee);
	         session.getTransaction().commit();
	}