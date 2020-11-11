package demoMTM;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import demoOTO.CustomerRecord;
import entities.Person;
import util.JPAUtil;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToMany
	@JoinTable(name="author_book")
	List<Author> lsAuthor=new ArrayList<Author>();
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
	public List<Author> getLsAuthor() {
		return lsAuthor;
	}
	public void setLsAuthor(List<Author> lsAuthor) {
		this.lsAuthor = lsAuthor;
	}
}