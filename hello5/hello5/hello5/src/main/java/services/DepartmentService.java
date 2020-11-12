package services;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import entities.Department;
import entities.Employee;
import util.JPAUtil;
@RequestScoped
public class DepartmentService {
	private static  List<Department> departments;
	EntityManagerFactory emf=JPAUtil.geEntityManagerFactory();
	private  EntityManager entityManager;
	@Resource
	UserTransaction utx;
	public Department find(int id) {
		return entityManager.find(Department.class, id);
	}
	public DepartmentService() {
		entityManager=emf.createEntityManager();
		departments=getDepartments();
	}
	public List<Department> getDepartments() {
		TypedQuery<Department> q=entityManager.createQuery("from Department",Department.class);
		departments=q.getResultList();
		return departments;
	}
	public void addDepartment(Department department) {
		try {
			utx.begin();
			entityManager.persist(department);
			utx.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		departments.add(department);
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
