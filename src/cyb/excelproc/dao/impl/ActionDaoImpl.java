package cyb.excelproc.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;

import cyb.excelproc.dao.IActionDao;
import cyb.excelproc.dao.IGenericDao;
import cyb.excelproc.entities.Action;
import cyb.excelproc.exceptions.QueryNotDefinedException;
import cyb.excelproc.utilities.EntityManagerUtils;
public class ActionDaoImpl implements IActionDao{
	
	private static Logger log = Logger.getLogger(ActionDaoImpl.class);
	
	private IGenericDao<Action> genAction;
	
	public ActionDaoImpl() throws PersistenceException,QueryNotDefinedException{
		genAction = new GenericDao<>(EntityManagerUtils.getFactory(),Action.class);
	}

	@Override
	public List<Action> getActions() throws PersistenceException {
		log.info("get actions called");
		return genAction.list();
	}

	@Override
	public Action getActionById(long actionId) throws PersistenceException {
		log.info("get actions by id called");
		return genAction.getById(actionId);
	}
}
