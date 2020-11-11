package demoMTM;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import demoOTO.CustomerRecord;
import entities.Person;
import util.JPAUtil;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany(mappedBy = "lsAuthor")
	List<Book> lsBook=new ArrayList<Book>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getLsBook() {
		return lsBook;
	}
	public void setLsBook(List<Book> lsBook) {
		this.lsBook = lsBook;
	}
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory=JPAUtil.geEntityManagerFactory();
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Book book1=new Book(); book1.setName("book1");
		
		Book book2=new Book(); book2.setName("book2");
	
		Author author1=new Author(); author1.setName("author1");
		Author author2=new Author(); author2.setName("author2");
		entityManager.getTransaction().commit();
	}
}
