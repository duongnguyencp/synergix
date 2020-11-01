import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Employee;
import entities.Employee2;

public class StoreData2 {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		TypedQuery<Employee2> query=entityManager.createQuery("from Employee where id=:id",Employee2.class);
		query.setParameter("id",);
		Employee2 employee=new Employee2();
		employee.setFirstName("first_name");
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
	}
}
