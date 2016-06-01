package cyb.jee.excelcmp.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import antlr.debug.Event;
import cyb.excelproc.entities.User;

public class InitializationTest {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		try{
			User user = new User();
			user.setUserName("mayank");
			user.setUserPass("pass");
			
			entityManager.persist(user);
			entityManager.getTransaction().commit();
		}
		catch(Exception ex){
			entityManager.getTransaction().rollback();
		}
		entityManager.close();
		entityManagerFactory.close();
	}
}
