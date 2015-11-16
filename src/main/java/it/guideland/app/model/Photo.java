package it.guideland.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "PHOTOS")
public class Photo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 398954632734305953L;
	
	@Id
	@Column(name = "photo_id")
	@GeneratedValue
	private int photoId;
	
	private String relativePath;
	
	private String fileName;
	
	//private String imagePath;
	
	private String contentType;	
	
	@Transient
	private byte[] arrayByte;

    @OneToOne(targetEntity = User.class, mappedBy = "profilePhoto")
    private User user;

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getArrayByte() {
		return arrayByte;
	}

	public void setArrayByte(byte[] arrayByte) {
		this.arrayByte = arrayByte;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
