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
		/*Admin admin = new Admin("Tarun", 10, "tarun.kumar@iongroup.com");
	 	String choice = "N";
		do {
			System.out.println("Adding a reviewer");
			Reviewer reviewer = getReviewer();
			admin.addCodeReviewers(reviewer);
			System.out.println("Do you want to continue adding Reviewers(Y/N) ?");
			choice = input.next();
		}while(choice);
		
		//admin.getReviewers();*/
		
		//Reviewee placed a request
		System.out.println("You are a reviewee");
		System.out.println("Enter your name: ");
		String revieweeName = input.next();
		System.out.println("Enter your employee id:");
		int userId = input.nextInt();
		System.out.println("Enter your email:");
		String email = input.next();
		Reviewee reviewee = new Reviewee("Bhavik", 20, "bhavik.k@iongroup.com");
		reviewee.requestCodeReviews("Suraj", "Trade", "its small", "open", 10);
		
		//Reviewee placed a request
		Reviewee reviewee1 = new Reviewee("Bhavik", 20, "bhavik.k@iongroup.com");
		reviewee1.requestCodeReviews("Suraj", "Trade", "its small", "open", 5);
		
		//Reviewer retrieve 
		ArrayList<String> modules = new ArrayList<String>();
		modules.add("validation");
		Reviewer reviewer = new Reviewer("Suraj", 30, "suraj.kumar@iongroup.com", modules);
		reviewer.accumulateRequest();
		
		
		long timeReview=0;
		for(ReviewRequest requestReview : Reviewee.getReviewRequests()) {
			timeReview = requestReview.getReviewTime();
			System.out.println("Review Time: " + timeReview);
			//System.out.println(requestReview.getClosedTime());
			//System.out.println(requestReview.getRequestTime());
		}
		
		
		
		

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
