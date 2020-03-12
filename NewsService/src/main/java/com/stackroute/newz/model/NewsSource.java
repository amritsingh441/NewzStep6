package com.stackroute.newz.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  
 */
@Document
public class NewsSource {

	/*
	 * This class should have five fields (newssourceId,newssourceName,
	 * newssourceDesc,newssourceCreatedBy,newssourceCreationDate). Out of these five fields, 
	 * the field newssourceId should be annotated with @Id (This annotation explicitly 
	 * specifies the document identifier). This class should also contain the getters and 
	 * setters for the fields, along with the no-arg , parameterized constructor and toString
	 * method.The value of newssourceCreationDate should not be accepted from the user but
	 * should be always initialized with the system date.
	 */
	@Id
	private int newsSourceId;
	private String newsSourceName;
	private String newsSourceDesc;
	private String newsSourceCreatedBy;
	private LocalDateTime newsSourceCreationDate;

	
	public NewsSource(int newsSourceId, String newsSourceName, String newsSourceDesc, 
			String newsSourceCreatedBy ) {
		super();
		this.newsSourceId = newsSourceId;
		this.newsSourceName = newsSourceName;
		this.newsSourceDesc = newsSourceDesc;
		this.newsSourceCreatedBy = newsSourceCreatedBy;
		this.newsSourceCreationDate = LocalDateTime.now();
	}
	
	public NewsSource() {}
	
	
	public int getNewsSourceId() {
		return newsSourceId;
	}

	public void setNewsSourceId(int newsSourceId) {
		this.newsSourceId = newsSourceId;
	}

	public String getNewsSourceName() {
		return newsSourceName;
	}

	public void setNewsSourceName(String newsSourceName) {
		this.newsSourceName= newsSourceName;
	}

	public String getNewsSourceDesc() {
		return newsSourceDesc;
	}

	public void setNewsSourceDesc(String newsSourceDesc) {
		this.newsSourceDesc = newsSourceDesc;
	}

	public String getNewsSourceCreatedBy() {
		return newsSourceCreatedBy;
	}

	public void setNewsSourceCreatedBy(String newsSourceCreatedBy) {
		this.newsSourceCreatedBy = newsSourceCreatedBy;
	}
	

	public LocalDateTime getNewsSourceCreationDate() {
		return newsSourceCreationDate;
	}

	public LocalDateTime setNewsSourceCreationDate() {
		return LocalDateTime.now();
	}


	@Override
	public String toString() {
		return "NewsSource [newsSourceId=" + newsSourceId + ", newsSourceName=" + newsSourceName + ", newsSourceDesc="
				+ newsSourceDesc + ", newsSourceCreatedBy=" + newsSourceCreatedBy + ", newsSourceCreationDate="
				+ newsSourceCreationDate + "]";
	}



}