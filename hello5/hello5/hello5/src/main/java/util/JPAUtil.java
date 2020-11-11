package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JPAUtil {
	private static final EntityManagerFactory entityMangerFacetory = buildEntityManagerFactory();
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return Persistence.createEntityManagerFactory("persistence");
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void shutdown() {
    	geEntityManagerFactory().close();
    	
    }
    public static EntityManagerFactory geEntityManagerFactory() {
        return entityMangerFacetory;
    }
}
