package cyb.excelproc.dao.impl;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.IGenericDao;
import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.dao.IUserDao;
import cyb.excelproc.dao.IUserTransactionDao;
import cyb.excelproc.entities.UserTransaction;
import cyb.excelproc.exceptions.QueryNotDefinedException;
import cyb.excelproc.utilities.EntityManagerUtils;

public class UserTransactionDaoImpl implements IUserTransactionDao {
private static Logger log = Logger.getLogger(ActionDaoImpl.class);
	
	private IGenericDao<UserTransaction> genUserTnx;
	private ITransactionDao transactionDao;
	private IUserDao userDao;
	public UserTransactionDaoImpl() throws PersistenceException,QueryNotDefinedException{
		genUserTnx = new GenericDao<>(EntityManagerUtils.getFactory(),UserTransaction.class);
		transactionDao = new TransactionDaoImpl();
		userDao = new UserDaoImpl();
	}
	
	@Override
	public UserTransaction addUserTransaction(UserTransaction userTrans) throws PersistenceException {
		userDao.setUser(userTrans.getUser());
		transactionDao.addTransaction(userTrans.getTransaction());
		return genUserTnx.add(userTrans);
	}

}
