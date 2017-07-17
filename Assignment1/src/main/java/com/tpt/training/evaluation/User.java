/**
 * 
 */
package com.tpt.training.evaluation;

/**
 * @author Suraj.Kumar
 *
 */
public class User {
	
	protected String name;
	protected int userId;
	protected String emailId;
	
	public User(String name, int userId, String emailId) {
		this.name = name;
		this.userId = userId;
		this.emailId = emailId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public int getUserId() {
		return userId;
	}

	public String getEmailId() {
		return emailId;
	}
	
	
	

}
