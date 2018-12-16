package persister;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManagerFactory entityManagerFactory = null;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("persister");
		} catch (Throwable e) {
			System.out.println("Es it ein unbekannter Fehler aufgetreten "+ e);
			throw new RuntimeException(e);
		}
	}

	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}