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
import javax.persistence.TemporalType;;

@Entity
@Table(name="transactions")
@NamedQueries({
	@NamedQuery(name="Transaction.list",query="select t from Transaction t")
})
public class Transaction {
	//state members
	private long tnxId;
	private User user;
	private Action action;
	private Date tnxCreationTime;
	private Date tnxLastModificationTime;
	private List<File> tnxFiles;
	
	//constructors
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(long tnxId) {
		super();
		this.tnxId = tnxId;
	}

	public Transaction(User user, Action action, Date tnxCreationTime, Date tnxLastModificationTime,
			List<File> tnxFiles) {
		super();
		this.user = user;
		this.action = action;
		this.tnxCreationTime = tnxCreationTime;
		this.tnxLastModificationTime = tnxLastModificationTime;
		this.tnxFiles = tnxFiles;
	}

	public Transaction(long tnxId, User user, Action action, Date tnxCreationTime, Date tnxLastModificationTime,
			List<File> tnxFiles) {
		super();
		this.tnxId = tnxId;
		this.user = user;
		this.action = action;
		this.tnxCreationTime = tnxCreationTime;
		this.tnxLastModificationTime = tnxLastModificationTime;
		this.tnxFiles = tnxFiles;
	}

	//getters and setters
	@Id
	@GeneratedValue
	public long getTnxId() {
		return tnxId;
	}

	public void setTnxId(long tnxId) {
		this.tnxId = tnxId;
	}
	
	@OneToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne
	@JoinColumn(name="actionId")
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	  
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTnxCreationTime() {
		return tnxCreationTime;
	}

	public void setTnxCreationTime(Date tnxCreationTime) {
		this.tnxCreationTime = tnxCreationTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTnxLastModificationTime() {
		return tnxLastModificationTime;
	}

	public void setTnxLastModificationTime(Date tnxLastModificationTime) {
		this.tnxLastModificationTime = tnxLastModificationTime;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="tnx")
	public List<File> getTnxFiles() {
		return tnxFiles;
	}

	public void setTnxFiles(List<File> tnxFiles) {
		this.tnxFiles = tnxFiles;
	}

	public void addFile(File file){
		if(tnxFiles == null){
			tnxFiles = new LinkedList<>();
		}
		tnxFiles.add(file);
	}
	@Override
	public String toString() {
		return "Transaction [tnxId=" + tnxId + ", user=" + user + ", action=" + action + ", tnxCreationTime="
				+ tnxCreationTime + ", tnxLastModificationTime=" + tnxLastModificationTime + ", tnxFiles=" + tnxFiles
				+ "]";
	}
	
	
	
	
	
	
}
