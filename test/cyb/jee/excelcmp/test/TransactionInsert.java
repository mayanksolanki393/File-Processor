package cyb.jee.excelcmp.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cyb.excelproc.entities.Action;
import cyb.excelproc.entities.File;
import cyb.excelproc.entities.Transaction;
import cyb.excelproc.entities.User;
import cyb.excelproc.entities.File.FileType;

public class TransactionInsert {
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
		User user = entityManager.find(User.class, (long)1);
		Action action = entityManager.find(Action.class, (long)1);
		
		List<File> list = new LinkedList<File>();
		Transaction trans = new Transaction(user, action, new Date(), new Date(), list);
		
		File f = new File();
		f.setFileLocation("c/test/test.xlsx");
		f.setTnx(trans);
		f.setFileType(FileType.INPUT);
		
		File f2 = new File();
		f2.setFileLocation("c/test/test2.xlsx");
		f2.setFileType(FileType.OUTPUT);
		f2.setTnx(trans);
		
		list.add(f);
		list.add(f2);
		
		entityManager.getTransaction().begin();
		entityManager.persist(trans);
		entityManager.getTransaction().commit();
	}
}
