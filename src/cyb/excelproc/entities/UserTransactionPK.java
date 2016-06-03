package cyb.excelproc.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserTransactionPK implements Serializable{
	private long userId;
	private long tnxId;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getTnxId() {
		return tnxId;
	}
	public void setTnxId(long tnxId) {
		this.tnxId = tnxId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (tnxId ^ (tnxId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTransactionPK other = (UserTransactionPK) obj;
		if (tnxId != other.tnxId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserTransactionPK [userId=" + userId + ", tnxId=" + tnxId + "]";
	}

}
