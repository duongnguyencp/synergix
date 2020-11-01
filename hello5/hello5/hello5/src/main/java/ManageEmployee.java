import java.util.List;
import java.util.Date;
import java.util.Iterator;
import entities.Employee2;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	private static SessionFactory factory;

	public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageEmployee ME = new ManageEmployee();
      /* Add few Employee2 records in database */
      Integer empID1 = ME.addEmployee2("Zara", "Ali", 1000);
      Integer empID2 = ME.addEmployee2("Daisy", "Das", 5000);
      Integer empID3 = ME.addEmployee2("John", "Paul", 10000);
      /* List down all the Employee2s */
//      ME.listEmployee2s();
//      /* Update Employee2's records */
//      ME.updateEmployee2(empID1, 5000);
//
//      ME.deleteEmployee2(empID2);

      /* List down new list of the Employee2s */
      ME.listEmployee2s();
   }

	/* Method to CREATE an Employee2 in the database */
	public Integer addEmployee2(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer Employee2ID = null;

		try {
			tx = session.beginTransaction();
			Employee2 Employee2 = new Employee2(fname, lname, salary);
			Employee2ID = (Integer) session.save(Employee2);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Employee2ID;
	}

	/* Method to READ all the Employee2s */
	public void listEmployee2s() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List Employee2s = session.createQuery("FROM Employee2").list();
			for (Iterator iterator = Employee2s.iterator(); iterator.hasNext();) {
				Employee2 Employee2 = (Employee2) iterator.next();
				System.out.print("First Name: " + Employee2.getFirstName());
				System.out.print("  Last Name: " + Employee2.getLastName());
				System.out.println("  Salary: " + Employee2.getSalary());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an Employee2 */
	public void updateEmployee2(Integer Employee2ID, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee2 Employee2 = (Employee2) session.get(Employee2.class, Employee2ID);
			Employee2.setSalary(salary);
			session.update(Employee2);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an Employee2 from the records */
	public void deleteEmployee2(Integer Employee2ID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee2 Employee2 = (Employee2) session.get(Employee2.class, Employee2ID);
			session.delete(Employee2);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
