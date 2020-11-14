package services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import entities.Department;
import entities.Employee;
import util.JPAUtil;
@RequestScoped
@Named
public class DepartmentService {
	private static  List<Department> departments;
	@Inject @CreateEM
	private EntityManagerFactory emf;
	private  EntityManager entityManager;
	@PostConstruct
	public void init() {
		entityManager=emf.createEntityManager();
		departments=getDepartments();
	}
	public Department find(int id) {
		return entityManager.find(Department.class, id);
	}
	public DepartmentService() {
	
	}
	public List<Department> getDepartments() {
		TypedQuery<Department> q=entityManager.createQuery("from Department",Department.class);
		departments=q.getResultList();
		return departments;
	}
	public void addDepartment(Department department) {
		entityManager.getTransaction().begin();
		entityManager.merge(department);
		entityManager.getTransaction().commit();
	}
	public void deleteDepartment(Department department) {
		departments.remove(department);
		entityManager.getTransaction().begin();
		Department department2=entityManager.find(Department.class, department.getId());
		entityManager.remove(department2);
		entityManager.getTransaction().commit();
	}

	public void editDepartment(Department department) {
		for(Department d:departments) {
			d.setCanEdit(false);
		}
		department.setCanEdit(true);
	}
	public void cancelEdit(Department department) {
		department.setCanEdit(false);
	}
	public void saveDepartments(Department department) {
		try {
			departments.set(departments.indexOf(department),department);
		
		}
		catch (Exception e) {
			departments.get(0);
			System.out.println();
		}
		entityManager.getTransaction().begin();
		Department department2=entityManager.find(Department.class, department.getId());
		department2.setName(department.getName());
		department2.setDescription(department.getDescription());
		department2.setCode(department.getCode());
		entityManager.getTransaction().commit();
		cancelEdit(department);
	}
}
