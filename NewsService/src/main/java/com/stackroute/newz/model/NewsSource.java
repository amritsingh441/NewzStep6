package com.stackroute.newz.model;

import java.time.LocalDateTime;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  
 */

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

	
	public int getNewsSourceId() {
		return 0;
	}

	public void setNewsSourceId(int newsSourceId) {
	}

	public String getNewsSourceName() {
		return null;
	}

	public void setNewsSourceName(String newsSourceName) {
	}

	public String getNewsSourceDesc() {
		return null;
	}

	public void setNewsSourceDesc(String newsSourceDesc) {
	}

	public String getNewsSourceCreatedBy() {
		return null;
	}

	public void setNewsSourceCreatedBy(String newsSourceCreatedBy) {
	}
	

	public LocalDateTime getNewsSourceCreationDate() {
		return null;
	}

	public void setNewsSourceCreationDate() {
	}

}
