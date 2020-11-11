package entities;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Department.class)
public class Department_ {
	public static volatile SingularAttribute<Department, Integer> id;
	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, String> code;
	public static volatile SingularAttribute<Department, String> description;
	public static volatile SetAttribute<Department, Employee> employees;
}
