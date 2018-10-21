package resources.database.entities.File;
 
 
import java.util.Date;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType; 
import javax.validation.constraints.NotNull;
 

/**
 * Entitiy Class for handeling the 
 * users table in the database 
 * 
 * 
 * There will be a ONE User MANY Accounts Relationship
 * 
 * , uniqueConstraints={@UniqueConstraint(columnNames={"USER_EMAIL"})}
 * */


@Entity
@Table(name = "files" )
public class Files {

	@Id
	@NotNull
	@Column(name = "FILE_NAME" , length = 128)
	private String fileName;
	
	@Column(name = "FILE_PATH" , length = 191) 
	private String filePath;
	
	@Column(name = "USER_EMAIL" , length = 50)
	private String userEmail;
	
	@Column(name = "FILE_UPLOADED" , columnDefinition = "TINYINT(1) default 0")
	private boolean fileUploaded;
	 
	@Column(name = "File_UPLOADER" , length = 50)
	private String fileUploader;
	
	@Column(name = "File_UPLOADED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileUploadedAt;

	@Column(name = "FILE_DELETED" , columnDefinition = "TINYINT(1) default 0")
	private boolean filedeleted;
	 
	@Column(name = "File_DELETER" , length = 50)
	private String fileDeleter;
	 
	@Column(name = "File_DELETED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fileDeletedAt;
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public boolean isFileUploaded() {
		return fileUploaded;
	}

	public void setFileUploaded(boolean fileUploaded) {
		this.fileUploaded = fileUploaded;
	}

	public String getFileUploader() {
		return fileUploader;
	}

	public void setFileUploader(String fileUploader) {
		this.fileUploader = fileUploader;
	}

	public Date getFileUploadedAt() {
		return fileUploadedAt;
	}

	public void setFileUploadedAt(Date fileUploadedAt) {
		this.fileUploadedAt = fileUploadedAt;
	}

	public String getFileDeleter() {
		return fileDeleter;
	}

	public void setFileDeleter(String fileDeleter) {
		this.fileDeleter = fileDeleter;
	}

	public boolean isFiledeleted() {
		return filedeleted;
	}

	public void setFiledeleted(boolean filedeleted) {
		this.filedeleted = filedeleted;
	}

	public Date getFileDeletedAt() {
		return fileDeletedAt;
	}

	public void setFileDeletedAt(Date fileDeletedAt) {
		this.fileDeletedAt = fileDeletedAt;
	}
	
    public void setFilePath(String filePath) {
		
		this.filePath = filePath;
	}
	
	public String  getFilePath() {
		
		return this.filePath;
	}
}
