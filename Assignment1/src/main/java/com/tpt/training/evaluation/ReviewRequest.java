/**
 * 
 */
package com.tpt.training.evaluation;

import java.util.Date;

/**
 * @author Suraj.Kumar
 *
 */
public class ReviewRequest {
	
	private String reviewerName;
	private String moduleName;
	private String descriptionCodeChanges;
	private String status;
	private long requestTime;
	private String comments;
	private long closedTime;
	
	public ReviewRequest(String reviewerName, String moduleName, String descriptionCodeChanges, String status,
			long requestTime) {
		this.reviewerName = reviewerName;
		this.moduleName = moduleName;
		this.descriptionCodeChanges = descriptionCodeChanges;
		this.status = status;
		this.requestTime = requestTime;
	}
	
	public long getReviewTime() {
		return closedTime - requestTime;
		
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setDescriptionCodeChanges(String descriptionCodeChanges) {
		this.descriptionCodeChanges = descriptionCodeChanges;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setClosedTime(long closedTime) {
		this.closedTime = closedTime;
	}
	
	
	public String getReviewerName() {
		return reviewerName;
	}
	
	

	public long getRequestTime() {
		return requestTime;
	}

	public long getClosedTime() {
		return closedTime;
	}

	private void validateModuleName() {
		

	}
	
	private void validateName() {
		
	}
	

}
