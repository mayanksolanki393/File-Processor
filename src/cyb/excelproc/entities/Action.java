/**
 * Author : Mayank Solanki
 */
package cyb.excelproc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;;

@Entity
@Table(name="actions")
@NamedQueries({
	@NamedQuery(name="Action.list",query="select a from Action a")
})
public class Action {
	
	//state members
	private long actionId;
	private String actionName;
	private int actionNoif;
	
	//constructor
	public Action() {
		// TODO Auto-generated constructor stub
	}
	
	public Action(long actionId) {
		super();
		this.actionId = actionId;
	}

	public Action(String actionName, int actionNoif) {
		super();
		this.actionName = actionName;
		this.actionNoif = actionNoif;
	}
	
	public Action(long actionId, String actionName, int actionNoif) {
		super();
		this.actionId = actionId;
		this.actionName = actionName;
		this.actionNoif = actionNoif;
	}

	//getter and setter
	@Id
	@GeneratedValue
	public long getActionId() {
		return actionId;
	}

	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	@Column(unique=true,nullable=false)
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public int getActionNoif() {
		return actionNoif;
	}

	public void setActionNoif(int actionNoif) {
		this.actionNoif = actionNoif;
	}

	@Override
	public String toString() {
		return "Action [actionId=" + actionId + ", actionName=" + actionName + ", actionNoif=" + actionNoif + "]";
	}
}
