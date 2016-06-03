package cyb.excelproc.entities;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findByUsername",query="Select u from User u where u.userName = :name"),
	@NamedQuery(name="User.list",query="Select u from User u")
})
public class User {
	//state members
	private long userId;
	private String userName;
	private String userPass;
	private List<UserTransaction> transactionList = new LinkedList<>();	
	
	//constructors
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(long userId) {
		super();
		this.userId = userId;
	}

	public User(String userName, String userPass) {
		super();
		this.userName = userName;
		this.userPass = userPass;
	}

	public User(long userId, String userName, String userPass) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPass = userPass;
	}

	//getters and setters
	@Id
	@GeneratedValue
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(unique=true,nullable=false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(nullable=false)
	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	@OneToMany(mappedBy="user")
	public List<UserTransaction> getTransactionList() {
		if(transactionList== null)
			transactionList = new LinkedList<>();
		return transactionList;
	}

	public void setTransactionList(List<UserTransaction> transactionList) {
		this.transactionList = transactionList;
	}

	//to String
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPass=" + userPass + "]";
	}
}
