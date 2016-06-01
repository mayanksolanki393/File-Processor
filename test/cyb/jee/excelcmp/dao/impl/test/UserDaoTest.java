package cyb.jee.excelcmp.dao.impl.test;

import cyb.excelproc.dao.IUserDao;
import cyb.excelproc.dao.impl.UserDaoImpl;
import cyb.excelproc.entities.User;
import cyb.excelproc.utilities.EntityManagerUtils;

public class UserDaoTest {
	public static void main(String[] args) {
		try{
			EntityManagerUtils.getFactory();
			IUserDao userDao = new UserDaoImpl();
		
			User user = userDao.getUserById(1);
			
			//User user = userDao.getUserByUsername("mayank");
			System.out.println(user);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			EntityManagerUtils.closeFactory();
		}
	}
}
