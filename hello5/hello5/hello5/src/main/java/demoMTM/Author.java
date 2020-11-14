package demoMTM;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import demoOTO.CustomerRecord;
import util.JPAUtil;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany(mappedBy = "lsAuthor",cascade = CascadeType.ALL)
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
	public void addBook(Book b) {
		this.lsBook.add(b);
		b.getLsAuthor().add(this);
		
	}
	
}
