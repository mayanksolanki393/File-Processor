package cyb.jee.excelcmp.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import cyb.excelproc.entities.Action;
import cyb.excelproc.utilities.LoggerUtils;

public class ActionTest {
	static Logger logger = Logger.getLogger(ActionTest.class);
	public static void main(String[] args) {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			entityManager = factory.createEntityManager();
			foo(entityManager);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (factory != null) {
				factory.close();
			}
		} 
	}
	
	public static void foo(EntityManager entityManager){
		entityManager.getTransaction().begin();
		logger.info("transaction started");
		logger.info("creating action object");
		Action action = new Action();
		logger.info("setting action object values");
		action.setActionName("Compare");
		action.setActionNoif(2);
		logger.info("persisting action");
		entityManager.persist(action);
		logger.info("commiting transaction");
		entityManager.getTransaction().commit();
	}
}
