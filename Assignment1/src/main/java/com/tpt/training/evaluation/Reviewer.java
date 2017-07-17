/**
 * 
 */
package com.tpt.training.evaluation;

import java.util.ArrayList;
import java.util.Date;

import javax.management.loading.PrivateClassLoader;

/**
 * @author Suraj.Kumar
 *
 */
public class Reviewer extends User{
	
	
	ArrayList<String> modules = new ArrayList<String>();
	
	
	
	public Reviewer(String name, int userId, String emailId, ArrayList<String> modules) {
		super(name, userId, emailId);
		this.modules.addAll(modules);
	}
	
	public void accumulateRequest() {
		
		ArrayList<ReviewRequest> reviewRequests = Reviewee.getReviewRequests();
		for(ReviewRequest reviewRequest : reviewRequests) {
			if(reviewRequest.getReviewerName().equals(this.name)) {
				reviewRequest.setStatus("closed");
				reviewRequest.setComments("Its good");
				reviewRequest.setClosedTime(20); //(new Date()).getTime()
				reviewRequest.getReviewTime();
			}
		}
		
	}

	

	


	
}
