package dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Department;
import util.HibernateUtil;
public class DepartmentDAO {
	public List<Department> getListDepartment( ){
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	    	 List<Department> lsDepartment=new ArrayList<Department>();

	      try {
	         tx = session.beginTransaction();
	         List departments = session.createQuery("FROM Department").list(); 
	         for (Iterator<Department> iterator = departments.iterator(); iterator.hasNext();){
	            Department deparment = (Department) iterator.next(); 
	            lsDepartment.add(deparment);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return lsDepartment;
	   }
	public void addDepartment(Department department) {
		Transaction trans = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trans = session.beginTransaction();
			session.save(department);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Method to UPDATE salary for an employee */
	   public void updateDepartment(Integer DepartmentID, Department department ){
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Department department2 = (Department)session.get(Department.class, DepartmentID); 
	         department2.setCode(department.getCode());
	         department2.setDescription(department.getDescription());
	         department2.setName(department.getName());
			 session.update(department2); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   
	   /* Method to DELETE an employee from the records */
	   public void deleteDepartment(Integer DepartmentID){
		   Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Department department = (Department)session.get(Department.class, DepartmentID); 
	         session.delete(department); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   void showDepartment(List<Department> lsDepartment) {
		   for(Department de:lsDepartment) {
			   System.out.println(de.toString());
			   
		   }
	   }
	   public static void main(String[] args) {
		   DepartmentDAO departmentDAO=new DepartmentDAO();
		   List<Department> lsDepartment=departmentDAO.getListDepartment();
		   departmentDAO.showDepartment(lsDepartment);
//		   Department department=new Department("marketing","mar001","khong co gi");
//		   departmentDAO.addDepartment(department);
//		   departmentDAO.deleteDepartment(1);
		   Department department=new Department("marketing","mar001","khong co gi");
		   departmentDAO.updateDepartment(2, department);
		   
	   }
		
	}

