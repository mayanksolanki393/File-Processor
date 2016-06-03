package cyb.excelproc.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cyb.excelproc.dao.IUserDao;
import cyb.excelproc.dao.IUserTransactionDao;
import cyb.excelproc.dao.impl.UserDaoImpl;
import cyb.excelproc.dao.impl.UserTransactionDaoImpl;
import cyb.excelproc.entities.File.FileType;

public class UserTransactionCRUDTest {
	public static void main(String[] args) {
		try{
			IUserDao userDoa = new UserDaoImpl();
			IUserTransactionDao userTrnsDao = new UserTransactionDaoImpl();
			UserTransaction userTransaction = new UserTransaction();
			User user = new User("mayank", "pass");
			Action action = new Action("Concat", 2);
			
			List<File> tnxFiles = new LinkedList<>();
			Transaction tnx = new Transaction(action, tnxFiles,userTransaction);
			
			tnxFiles.add(new File(tnx, "C:/programs/file.txt", FileType.INPUT));
			tnxFiles.add(new File(tnx, "C:/programs/file_outptu.txt", FileType.OUTPUT));
			
			
			userTransaction.setTnxCreationTime(new Date());
			userTransaction.setTnxLastModificationTime(new Date());
			
			userTransaction.setUser(user);
			userTransaction.setTransaction(tnx);
			
			user.getTransactionList().add(userTransaction);
			tnx.setUserTransaction(userTransaction);
			
			userTrnsDao.addUserTransaction(userTransaction);
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
