package cyb.excelproc.dao;

import javax.persistence.PersistenceException;

import cyb.excelproc.entities.UserTransaction;

public interface IUserTransactionDao {
	public UserTransaction addUserTransaction(UserTransaction userTrans) throws PersistenceException;
}
