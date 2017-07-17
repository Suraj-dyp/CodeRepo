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
	
	public static void requestCodeReviews() {
		
		ReviewRequest reviewRequest = new ReviewRequest("Suraj", "trade capture", "performance", "open", 10); //(new Date()).getTime()
		reviewRequests.add(reviewRequest);
		
	}

	public static ArrayList<ReviewRequest> getReviewRequests() {
		return reviewRequests;
	}

}
