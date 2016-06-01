package cyb.excelproc.dao;

import javax.persistence.PersistenceException;

import cyb.excelproc.entities.Transaction;

public interface ITransactionDao {
	// Create
	public Transaction addTransaction(Transaction transaction) throws PersistenceException;

	// Read
	public Transaction getTranscationById(long transactionId) throws PersistenceException;

	// Update
	public Transaction setTransaction(Transaction transaction) throws PersistenceException;

}
