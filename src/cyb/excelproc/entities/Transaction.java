package cyb.excelproc.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;;

@Entity
@Table(name = "transactions")
@NamedQueries({ @NamedQuery(name = "Transaction.list", query = "select t from Transaction t") })
public class Transaction {
	// state members
	private long tnxId;
	private Action action;
	private List<File> tnxFiles;
	private UserTransaction userTransaction;

	// constructors
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(long tnxId) {
		super();
		this.tnxId = tnxId;
	}

	public Transaction(Action action, List<File> tnxFiles, UserTransaction userTransaction) {
		super();
		this.action = action;
		this.tnxFiles = tnxFiles;
		this.userTransaction = userTransaction;
	}

	public Transaction(long tnxId, Action action, List<File> tnxFiles, UserTransaction userTransaction) {
		super();
		this.tnxId = tnxId;
		this.action = action;
		this.tnxFiles = tnxFiles;
		this.userTransaction = userTransaction;
	}

	// getters and setters
	@Id
	@GeneratedValue
	public long getTnxId() {
		return tnxId;
	}

	public void setTnxId(long tnxId) {
		this.tnxId = tnxId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "actionId")
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tnx")
	public List<File> getTnxFiles() {
		return tnxFiles;
	}

	public void setTnxFiles(List<File> tnxFiles) {
		this.tnxFiles = tnxFiles;
	}

	@OneToOne(mappedBy = "transaction")
	@Transient
	public UserTransaction getUserTransaction() {
		return userTransaction;
	}

	public void setUserTransaction(UserTransaction userTransaction) {
		this.userTransaction = userTransaction;
	}

	public void addFile(File file) {
		if (tnxFiles == null) {
			tnxFiles = new LinkedList<>();
		}
		tnxFiles.add(file);
	}

	@Override
	public String toString() {
		return "Transaction [tnxId=" + tnxId + ", action=" + action + ", tnxFiles=" + tnxFiles + ", userTransaction="
		        + userTransaction + "]";
	}

}
