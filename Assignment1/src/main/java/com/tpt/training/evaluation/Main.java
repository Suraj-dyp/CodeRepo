/**
 * 
 */
package com.tpt.training.evaluation;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Suraj.Kumar
 *
 */
public class Main {
	
	private static Scanner input = new Scanner(System.in);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Admin Role
		Admin admin = new Admin("Tarun", 10, "tarun.kumar@iongroup.com");
		boolean choice = true;
		do {
			System.out.println("Adding a reviewer");
			Reviewer reviewer = getReviewer();
			admin.addCodeReviewers(reviewer);
			System.out.println("Do you want to continue adding Reviewers?");
			choice = input.nextBoolean();
		}while(choice);
		
		//admin.getReviewers();
		
		//Reviewee placed a request
		Reviewee reviewee = new Reviewee("Bhavik", 20, "bhavik.k@iongroup.com");
		Reviewee.requestCodeReviews();
		
		//Reviewer retrieve 
		Reviewer reviewer = new Reviewer("Suraj", 30, "suraj.kumar@iongroup.com", getModules());
		reviewer.accumulateRequest();
		
		
		long timeReview=0;
		for(ReviewRequest requestReview : Reviewee.getReviewRequests()) {
			timeReview = requestReview.getReviewTime();
			System.out.println(requestReview.getClosedTime());
			System.out.println(requestReview.getRequestTime());
		}
		System.out.println(timeReview);
		
		
		

	}

	private static Reviewer getReviewer() {
		
		return new Reviewer(getName(), getUserId(), getEmail(), getModules());
	}

	private static ArrayList<String> getModules() {
		ArrayList<String> modules = new ArrayList<String>();
		boolean choice = true;
		do {
			System.out.println("Enter Module for the Reviewer: ");
			String module = input.next();
			modules.add(module);
			System.out.println("Do you want to continue adding Module?");
			choice = input.nextBoolean();
		}while(choice);
		
		return modules;
	}

	private static String getEmail() {
		System.out.println("Enter Email of Reviewer: ");
		String emailId = input.next();
		return emailId;
	}

	private static int getUserId() {
		System.out.println("Enter ID of Reviewer: ");
		int userId = input.nextInt();
		return userId;
	}

	private static String getName() {
		System.out.println("Enter Name of Reviewer: ");
		String name = input.next();
		return name;
	}

}
