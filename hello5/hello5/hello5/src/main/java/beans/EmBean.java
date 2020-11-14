package beans;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import services.CreateEM;
import services.RandomQualifier;


public class EmBean implements Serializable {
	private static final long serialVersionUID=-7698506329160109476L;
	@Produces @CreateEM 
    public EntityManagerFactory geEntityManagerFactory() {
    	try {
        
            return Persistence.createEntityManagerFactory("persistence");
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}