package cyb.jee.excelcmp.dao.impl.test;

import java.util.List;

import cyb.excelproc.dao.IActionDao;
import cyb.excelproc.dao.impl.ActionDaoImpl;
import cyb.excelproc.entities.Action;
import cyb.excelproc.utilities.EntityManagerUtils;

public class ActionDoaTest {
	
	public static void main(String[] args) {
		
		EntityManagerUtils.getFactory();
		try{
			IActionDao actionDao = new ActionDaoImpl();
		
			/*List<Action> list = actionDao.getActions();
			if(list!=null)
			for(Action a:list){
				System.out.println(a.getActionName());
			}*/
			
			
			
			Action action = actionDao.getActionById(1);
			System.out.println("Here : "+action.getActionName());
			System.out.println("Here : "+action.getActionNoif());
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			EntityManagerUtils.closeFactory();
		}
		
	}
}
