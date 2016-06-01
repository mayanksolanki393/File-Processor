package cyb.excelproc.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import cyb.excelproc.entities.Action;

public interface IActionDao {

	public List<Action> getActions() throws PersistenceException;

	public Action getActionById(long actionId) throws PersistenceException;
}
