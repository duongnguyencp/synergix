package services;

import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.SessionScoped;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.transaction.UserTransaction;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import entities.Department;
import entities.Department_;
import entities.Employee;
import entities.Employee_;
import util.JPAUtil;
@EmployeeQualifier
@RequestScoped
public class EmployeeService implements CrudService{
	private static  List<Employee> employees;
	private EmployeeDAO employeeDAO;
	EntityManagerFactory emf=JPAUtil.geEntityManagerFactory();
	
	private  EntityManager entityManager;
	@Resource
	UserTransaction utx;
	public EmployeeService() {
		entityManager=emf.createEntityManager();
		employees=getEmployees();
	}
	public void shutdown() {
		entityManager.close();
		JPAUtil.shutdown();
	}
	public List<Employee> getEmployees() {
		TypedQuery<Employee> q=entityManager.createQuery("from Employee",Employee.class);
		employees=q.getResultList();
		return employees;
	}
	public void addEmployee(Employee employee) {
		try {
			utx.begin();
			entityManager.persist(employee);
			utx.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		employees.add(employee);
		
	}
	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
		entityManager.getTransaction().begin();
		Employee employee2=entityManager.find(Employee.class, employee.getId());
		entityManager.remove(employee2);
		entityManager.getTransaction().commit();
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
		entityManager.getTransaction().begin();
		Employee employee2=entityManager.find(Employee.class, employee.getId());
		employee2.setAge(employee.getAge());
		employee2.setDepartment(employee.getDepartment());
		employee2.setDOB(employee.getDOB());
		employee2.setGender(employee.getGender());
		employee2.setName(employee.getName());
		entityManager.getTransaction().commit();
		cancelEdit(employee);
	}
	public boolean checkNameDuplicated(String name) {
		List<Employee> employees=getEmployees();
		for(Employee e:employees) {
			if(e.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	public List<Employee> getEmployeeByDepartment(Department department){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query=builder.createQuery(Employee.class);
		Root<Employee> emp=query.from(Employee.class);
		Join<Employee,Department> dept=emp.join(Employee_.department);
		query.where(builder.equal(dept.get(Department_.id),department.getId()));
		TypedQuery<Employee> typedQuery=entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
	public List<Employee> getEmployeeNamedQuery(String name){
		Query query=entityManager.createNamedQuery("findAllEmployeeByName");
		query.setParameter("name", name);
		return query.getResultList();
	}
	public void updateEmployeeNamedQuery(Employee emp) {
		
		Query query=entityManager.createNamedQuery("updateEmployee");
		query.setParameter("name", emp.getName());
		query.setParameter("age", emp.getAge());
		query.setParameter("DOB", emp.getDOB());
		query.setParameter("gender", emp.getGender());
		query.setParameter("department", emp.getDepartment());
		query.setParameter("id", emp.getId()).getResultList();
		try {
			query.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public List<Employee> getEmployeeCriteriaQuery(String name){
		CriteriaBuilder builder=entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query=builder.createQuery(Employee.class);
		Root<Employee> root=query.from(Employee.class);
		query.select(root);
		query.where(builder.equal(root.get(Employee_.name),name));
		return entityManager.createQuery(query).getResultList();
	}
	public List<Employee> getEmployeeByDepartment(String name){
		TypedQuery<Employee> query=entityManager.createQuery("Select e from Employee e join e.department d where d.name=:name",Employee.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
	public void testEntityState() {
		entityManager.getTransaction().begin();
		Employee e1=entityManager.find(Employee.class, 4);
		entityManager.detach(e1);
		Employee e2=entityManager.merge(e1);
		entityManager.merge(e1);
		System.out.println();
		entityManager.flush();
		entityManager.getTransaction().commit();
	}
	public void testEntityState3() {
		entityManager.getTransaction().begin();
		Employee e1=entityManager.find(Employee.class, 4);
		while(true) {
			e1.setAge(12);
			entityManager.flush();
			
		}
		
	}
	public void testEntityState2() {
		entityManager.getTransaction().begin();
		
		Department d=entityManager.find(Department.class, 1);
		entityManager.detach(d);
		Department e=entityManager.merge(d);
		e.setName("training1");
		List<Employee> lsEmployee=e.getEmployees();
		System.out.println(lsEmployee);
		lsEmployee.get(0).setName("Jack dep trai");
		entityManager.merge(e);
		entityManager.flush();
		entityManager.getTransaction().commit();
		
		
	}
	public static void main(String[] args) {
		EmployeeService employeeService=new EmployeeService();
		//System.out.println(employeeService.getEmployeeNamedQuery("thomas1"));
		//System.out.println(employeeService.getEmployeeCriteriaQuery("thomas1"));
//		Department department=new Department();
//		department.setId(2);
//		System.out.println(employeeService.getEmployeeByDepartment(department));
//		Employee emp=new Employee();
//		emp.setId(2);
//		emp.setName("Jack");
//		emp.setDOB(new Date());
//		
//		emp.setDepartment(department);
//		emp.setGender("Male");
//		employeeService.updateEmployeeNamedQuery(emp);
		employeeService.testEntityState();
//		Employee e=new Employee();
//		e.setAge(12);
//		employeeService.addEmployee(e);
		
	}
}
