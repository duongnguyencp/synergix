package demoOTO;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import entities.Person;
import util.JPAUtil;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToOne(mappedBy = "customer")
	CustomerRecord customerRecord;
	public CustomerRecord getCustomerRecord() {
		return customerRecord;
	}
	public void setCustomerRecord(CustomerRecord customerRecord) {
		this.customerRecord = customerRecord;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory=JPAUtil.geEntityManagerFactory();
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Customer p=new Customer();
		p.setName("Customer");
		CustomerRecord customerRecord=new CustomerRecord();
		customerRecord.setName("Customer Record");
		p.setCustomerRecord(customerRecord);
		customerRecord.setCustomer(p);
		entityManager.persist(customerRecord);
		entityManager.persist(p);
		entityManager.getTransaction().commit();
	}

}
