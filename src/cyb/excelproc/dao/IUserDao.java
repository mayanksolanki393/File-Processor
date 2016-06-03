package cyb.excelproc.dao;

import javax.persistence.PersistenceException;

import cyb.excelproc.entities.User;

public interface IUserDao {

	// Read
	public User getUserById(long id) throws PersistenceException;

	public User getUserByUsername(String username) throws PersistenceException;

	public User setUser(User user) throws PersistenceException;
}
