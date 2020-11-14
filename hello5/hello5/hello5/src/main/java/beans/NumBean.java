package beans;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import services.RandomQualifier;

public class NumBean implements Serializable {
	private static final long serialVersionUID=-7698506329160109476L;
	@Produces 
	@RandomQualifier
	@RequestScoped
	int getNumRandom() {
		return new Random().nextInt();
	}
}
