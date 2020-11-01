package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Department;
import entities.Employee;
import util.HibernateUtil;

public class EmployeeDAO {
	public List<Employee> getListEmployee( ){
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	    	 List<Employee> lsEmployee=new ArrayList<Employee>();

	      try {
	         tx = session.beginTransaction();
	         List<Employee> employees = session.createQuery("FROM Employee").list(); 
	         for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();){
	            Employee employee = (Employee) iterator.next(); 
	            lsEmployee.add(employee);
	         }
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return lsEmployee;
	   }
	public void addEmployee(Employee employee) {
		Transaction trans = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trans = session.beginTransaction();
			session.save(employee);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Method to UPDATE salary for an employee */
	   public void updateEmployee(Integer EmployeeID, Employee employee ){
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Employee employee2 = (Employee)session.get(Employee.class, EmployeeID); 
	         employee2.setAge(employee.getAge());
	         employee2.setDepartment(employee.getDepartment());
	         employee2.setDOB(employee.getDOB());
	         employee2.setGender(employee.getGender());
	         employee2.setName(employee.getName());
			 session.update(employee2); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   
	   /* Method to DELETE an employee from the records */
	   public void deleteEmployee(Integer EmployeeID){
		   Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Employee employee = (Employee)session.get(Employee.class, EmployeeID); 
	         session.delete(employee); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	   }
	   void showEmployee(List<Employee> lsEmployee) {
		   for(Employee de:lsEmployee) {
			   System.out.println(de.toString());
			   
		   }
	   }
	 
		
	}