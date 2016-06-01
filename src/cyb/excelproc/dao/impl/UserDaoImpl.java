package cyb.excelproc.dao.impl;

import static cyb.excelproc.utilities.EntityManagerUtils.getFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.IUserDao;
import cyb.excelproc.entities.User;
import cyb.excelproc.utilities.EntityManagerUtils;

public class UserDaoImpl implements IUserDao {
	
	private static Logger log = Logger.getLogger(UserDaoImpl.class);
	GenericDao<User> genUser;
	
	public UserDaoImpl() throws Exception{
		genUser = new GenericDao<>(EntityManagerUtils.getFactory(),User.class);
	}

	@Override
	public User getUserById(long id) throws PersistenceException {
		return genUser.getById(id);
	}

	@Override
	public User getUserByUsername(String username) throws PersistenceException {
		log.info("getUserByUsername called");
		EntityManager manager = getFactory().createEntityManager();
		try{
			log.info("setting data on query");
			Query query = manager.createNamedQuery("User.findByUsername", User.class);
			query.setParameter("name", username);
			User user = (User) query.getSingleResult();
			
			return user;
		}
		catch(Exception ex){
			log.error(ex);
			ex.printStackTrace();
			return null;
		}
		finally{
			if(manager!=null)
				manager.close();
		}
	}



}
