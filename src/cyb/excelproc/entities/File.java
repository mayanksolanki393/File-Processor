package cyb.excelproc.entities;

import javax.persistence.*;;

@Entity
@Table(name="files")
@NamedQueries({
	@NamedQuery(name="File.list",query="select f from File f")
})
public class File {
	
	//static constants
	public enum FileType{INPUT,OUTPUT}
	
	//state members
	private long fileId;
	private Transaction tnx;
	private String fileLocation;
	private FileType fileType;
	
	//constructors
	public File() {
		// TODO Auto-generated constructor stub
	}
	
	public File(long fileId) {
		super();
		this.fileId = fileId;
	}

	public File(Transaction tnx, String fileLocation, FileType fileType) {
		super();
		this.tnx = tnx;
		this.fileLocation = fileLocation;
		this.fileType = fileType;
	}
	
	public File(long fileId, Transaction tnx, String fileLocation, FileType fileType) {
		super();
		this.fileId = fileId;
		this.tnx = tnx;
		this.fileLocation = fileLocation;
		this.fileType = fileType;
	}

	//getter and setters
	@Id
	@GeneratedValue
	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	public Transaction getTnx() {
		return tnx;
	}

	public void setTnx(Transaction tnx) {
		this.tnx = tnx;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	@Enumerated(EnumType.STRING)
	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", tnx=" + tnx.getTnxId() + ", fileLocation=" + fileLocation + ", fileType=" + fileType
				+ "]";
	}

	
	

	
}
