/**
 * 
 */
package com.tpt.training.evaluation;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Suraj.Kumar
 *
 */
public class Reviewee extends User {
	
	private static ArrayList<ReviewRequest> reviewRequests = new ArrayList<ReviewRequest>();
	
 	public Reviewee(String name, int userId, String emailId) {
		super(name, userId, emailId);
	}
	
	public void requestCodeReviews(String reviewerName, String module, String descriptionCodeChanges, String status, long requestTime) {
		
		ReviewRequest reviewRequest = new ReviewRequest(reviewerName, module, descriptionCodeChanges, status, requestTime); 	//(new Date()).getTime() could be used
		reviewRequests.add(reviewRequest);
		
	}

	public static ArrayList<ReviewRequest> getReviewRequests() {
		return reviewRequests;
	}

}
