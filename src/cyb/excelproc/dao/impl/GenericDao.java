package cyb.excelproc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.IGenericDao;
import cyb.excelproc.exceptions.QueryNotDefinedException;

public class GenericDao<T> implements IGenericDao<T> {

	EntityManagerFactory emf;
	private boolean getByIdSpecified;
	final Class<T> typeParameterClass;
	private Logger log;

	public GenericDao(EntityManagerFactory emf, Class<T> typeParameterClass)
	        throws QueryNotDefinedException, PersistenceException {
		this.emf = emf;
		this.typeParameterClass = typeParameterClass;
		EntityManager manager = emf.createEntityManager();
		log = Logger.getLogger(GenericDao.class);

		try {
			manager.createNamedQuery(getRealQueryName("getById"), typeParameterClass);
			getByIdSpecified = true;
		} catch (IllegalArgumentException ex) {
			log.error("getById is not defined by the " + typeParameterClass + " " + ex);
			getByIdSpecified = false;
		}

		try {
			manager.createNativeQuery(getRealQueryName("list"), typeParameterClass);
		} catch (IllegalArgumentException ex) {
			log.error("list is not defined by the " + typeParameterClass + " " + ex);
			throw new QueryNotDefinedException("\"list\" named query not defined for " + typeParameterClass.getName());
		}

	}

	@Override
	public T add(T type) throws PersistenceException {

		EntityManager manager = emf.createEntityManager();
		EntityTransaction entitytrans = manager.getTransaction();

		entitytrans.begin();
		manager.persist(type);
		entitytrans.commit();
		return type;

	}

	@Override
	public T set(T type) throws PersistenceException {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction entitytrans = manager.getTransaction();

		entitytrans.begin();
		manager.merge(type);
		entitytrans.commit();
		return type;
	}

	@Override
	public T remove(T entity) throws PersistenceException {
		EntityManager manager = emf.createEntityManager();
		EntityTransaction entitytrans = manager.getTransaction();

		entitytrans.begin();
		manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
		entitytrans.commit();
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Object id) throws PersistenceException {
		EntityManager manager = emf.createEntityManager();
		Object entity;
		if (getByIdSpecified) {
			Query query = manager.createNamedQuery(getRealQueryName("getById"), typeParameterClass);
			query.setParameter(1, id);
			entity = query.getSingleResult();
		} else {
			entity = manager.find(typeParameterClass, id);
		}
		return (T) entity;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> list() throws PersistenceException {
		EntityManager manager = emf.createEntityManager();

		Query query = manager.createNamedQuery(getRealQueryName("list"), typeParameterClass);

		List<Object> entities = query.getResultList();

		if (entities != null) {
			return (List<T>) entities;
		} else {
			throw new EntityNotFoundException("No enity with specified query \"" + query.toString() + "\" found");
		}

	}

	private String getRealQueryName(String queryName) {
		return typeParameterClass.getSimpleName() + "." + queryName;
	}

}
