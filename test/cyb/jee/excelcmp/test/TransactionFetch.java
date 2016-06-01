package cyb.jee.excelcmp.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cyb.excelproc.entities.Transaction;

public class TransactionFetch {
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
		Transaction tran = entityManager.find(Transaction.class, (long)2);
		System.out.println(tran);
		entityManager.getTransaction().commit();
	}
}
