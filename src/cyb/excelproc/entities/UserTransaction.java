package cyb.excelproc.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserTransaction {
	private UserTransactionPK userTransactionPK;
	private Date tnxCreationTime;
	private Date tnxLastModificationTime;
	private Transaction transaction;
	private User user;

	@PrePersist
	private void setId(){
		if(userTransactionPK == null)
			userTransactionPK = new UserTransactionPK();
		userTransactionPK.setTnxId(transaction.getTnxId());
		userTransactionPK.setUserId(user.getUserId());
	}
	
	@EmbeddedId
	public UserTransactionPK getUserTransactionPK() {
		return userTransactionPK;
	}

	public void setUserTransactionPK(UserTransactionPK userTransactionPK) {
		this.userTransactionPK = userTransactionPK;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tnxId", insertable = false, updatable = false)
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

}
