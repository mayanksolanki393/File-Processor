package cyb.jee.excelcmp.dao.impl.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cyb.excelproc.dao.IActionDao;
import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.dao.IUserDao;
import cyb.excelproc.dao.impl.ActionDaoImpl;
import cyb.excelproc.dao.impl.TransactionDaoImpl;
import cyb.excelproc.dao.impl.UserDaoImpl;
import cyb.excelproc.entities.Action;
import cyb.excelproc.entities.File;
import cyb.excelproc.entities.Transaction;
import cyb.excelproc.entities.User;
import cyb.excelproc.entities.File.FileType;
import cyb.excelproc.utilities.EntityManagerUtils;

public class TransactionDaoTest {
	public static void main(String[] args) {
		EntityManagerUtils.getFactory();
		try{
			insert();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			EntityManagerUtils.closeFactory();
		}
	}
	
	public static void readById() throws Exception{
		ITransactionDao dao = new TransactionDaoImpl();
		System.out.println(dao.getTranscationById((long)1));
		
	}
	
	public static void insert() throws Exception{
		ITransactionDao dao = new TransactionDaoImpl();
		IActionDao actionDao = new ActionDaoImpl();
		IUserDao userDao = new UserDaoImpl();
		
		List<File> files = new LinkedList<File>();
		
		Transaction trans = new Transaction();
		trans.setUser(userDao.getUserByUsername("mayank"));
		trans.setAction(actionDao.getActionById(1));
		trans.setTnxCreationTime(new Date());
		trans.setTnxLastModificationTime(new Date());
		trans.setTnxFiles(files);
		
		File file = new File(trans, "D:/Files/test.xlsx", FileType.INPUT);
		File file2 = new File(trans,"D:/Files/test_output.xlsx",FileType.OUTPUT);
		
		files.add(file);
		files.add(file2);
		
		dao.addTransaction(trans);
	}
}
