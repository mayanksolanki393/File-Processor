package cyb.excelproc.dao;

import java.util.List;

import javax.persistence.PersistenceException;

public interface IGenericDao<T> {
	
	/**
	 * use for insert operations on the database
	 * @param instance of T
	 * @return persistent instance of object 
	 * @throws Exception
	 */
	public T add(T type) throws PersistenceException;
	
	
	/**
	 * use of update operations on the database
	 * @param instance of T
	 * @return updated instance of persisted object
	 * @throws Exception
	 */
	public T set(T type) throws PersistenceException;
	

	/**
	 * use for insertion operations on the database
	 * @param instance of T
	 * @return persistent instance of object 
	 * @throws Exception
	 */
	public T remove(T type) throws PersistenceException;

	
	/**
	 * use to perform get by id operations,if you specify a named query <b>getById<b>
	 * then it will be called make sure the first parameter is id(primary key)
	 * @param id
	 * @return instance of T 
	 * @throws Exception
	 */
	public T getById(Object id) throws PersistenceException;
	
	/**
	 * use to get list of Objects , if you specify a named query <b>list</b> then it will
	 * be called
	 * @return
	 * @throws Exception
	 */
	public List<T> list() throws PersistenceException;
	
}
