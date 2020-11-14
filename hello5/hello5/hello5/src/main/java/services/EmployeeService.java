package services;

import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import entities.Department;
import entities.Department_;
import entities.Employee;
import entities.Employee_;
import util.JPAUtil;
@RequestScoped
@Transactional
@Named
public class EmployeeService  {
	private static  List<Employee> employees;
	@Inject 
	@CreateEM
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	@PostConstruct
	public void init() {
		
	}
	public EmployeeService() {

	}
	public void shutdown() {
		
	}
	public List<Employee> getEmployees() {
		entityManager=entityManagerFactory.createEntityManager();
		TypedQuery<Employee> q=entityManager.createQuery("from Employee",Employee.class);
		employees=q.getResultList();
		return employees;
	}
	public String getStateEntityD(Department o) {
		if(o.getId()==null) 
			return "TRAINSIENT";
		return "DETACH";
	}
	public String getStateEntityE(Employee o) {
		if(o.getId()==null) 
			return "TRAINSIENT";
		return "DETACH";
	}
	public void addEmployee(Employee employee) {
		entityManager.merge(employee);
		
	}
	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
		Employee employee2=entityManager.find(Employee.class, employee.getId());
		entityManager.remove(employee2);
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
		getStateEntityE(employee);
		entityManager.merge(employee);
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
	
	
}
