package cyb.excelproc.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class EntityManagerUtils {
	private static Logger LOG = Logger.getLogger(EntityManagerUtils.class);
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getFactory(){
	try {
		if(factory==null){
			LOG.info("creating factory");
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			LOG.info("factory created : "+factory);
		}
		return factory;
	}
	catch(Exception ex){
		ex.printStackTrace();
		LOG.error(ex);
		return null;
	}
	}
	
	public static void closeFactory(){
		try {
			if(factory!=null){
				factory.close();
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			LOG.error(ex);
		}
	}
}
