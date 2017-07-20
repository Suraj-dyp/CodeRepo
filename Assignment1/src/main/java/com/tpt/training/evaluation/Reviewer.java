/**
 * 
 */
package com.tpt.training.evaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.management.loading.PrivateClassLoader;

/**
 * @author Suraj.Kumar
 *
 */
public class Reviewer extends User{
	
	Scanner input = new Scanner(System.in);
	ArrayList<String> modules = new ArrayList<String>();
	
	
	
	public Reviewer(String name, int userId, String emailId, ArrayList<String> modules) {
		super(name, userId, emailId);
		this.modules.addAll(modules);
	}
	
	
	public ArrayList<String> getModules() {
		return modules;
	}


	public void accumulateRequest() {
		
		String status = null;
		String comments = null;
		long closedTime = 0L;
		
		ArrayList<ReviewRequest> reviewRequests = Reviewee.getReviewRequests();
		for(ReviewRequest reviewRequest : reviewRequests) {
			if(reviewRequest.getReviewerName().equals(this.name)) {
				
				System.out.println("You are viewing your review request");
				System.out.println("Module Name: " + reviewRequest.getModuleName());
				System.out.println("Description of code changes" + reviewRequest.getDescriptionCodeChanges());
				System.out.println("Status: " + reviewRequest.getStatus());
				System.out.println("Time of request: " + reviewRequest.getRequestTime());
				status = Main.getStatus();
				comments = Main.getComments();
				closedTime = Main.getClosedTime();
				/*System.out.println("Enter the status of review: ");
				status = input.next();
				System.out.println("Enter the comments about the review: ");
				comments = input.next();
				System.out.println("Enter the closing time of review: ");
				closedTime = input.nextLong();*/
				setExpertCommentsAndStatusAndTime(reviewRequest, status, comments, closedTime );
				
			}
		}
		
	}


	private void setExpertCommentsAndStatusAndTime(ReviewRequest reviewRequest, String status, String comments, long closedTime ) {
		
		reviewRequest.setStatus(status);
		reviewRequest.setComments(comments);
		reviewRequest.setClosedTime(closedTime);	 //(new Date()).getTime() could be used
		//reviewRequest.getReviewTime();
	}

	

	


	
}
