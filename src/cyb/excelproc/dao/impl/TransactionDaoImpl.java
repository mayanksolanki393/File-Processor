package cyb.excelproc.dao.impl;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.ITransactionDao;
import cyb.excelproc.entities.Transaction;
import cyb.excelproc.utilities.EntityManagerUtils;

public class TransactionDaoImpl implements ITransactionDao {
	private static Logger log = Logger.getLogger(ActionDaoImpl.class);
	GenericDao<Transaction> genTrans;
	
	public TransactionDaoImpl() throws Exception{
		genTrans = new GenericDao<>(EntityManagerUtils.getFactory(),Transaction.class);
	}

	@Override
	public Transaction addTransaction(Transaction transaction) throws PersistenceException {
		return genTrans.add(transaction);
	}

	@Override
	public Transaction getTranscationById(long transactionId) throws PersistenceException {
		return genTrans.getById(transactionId);
	}

	@Override
	public Transaction setTransaction(Transaction transaction) throws PersistenceException {
		return genTrans.set(transaction);
	}
	
}
