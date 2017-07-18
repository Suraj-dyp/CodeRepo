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
	private static final String REVIEWEE = "Reviewee";
	private static final String REVIEWER = "Reviewer";
	//private static ArrayList<String> modules = new ArrayList<String>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int userChoice = 0;
		do {
			System.out.println("\n====================================================");
			System.out.println("1. Admin \n2. Reviewee \n3. Reviewer");
			System.out.println("====================================================");
			System.out.print("\nEnter your choice:");
			userChoice = input.nextInt();
			if(userChoice==1) {
				System.out.println("\n==============");
				System.out.println("ADMIN");
				System.out.println("==============");
				System.out.print("\n1. Do u want to add Reviewer? \n2. Exit \nEnter your choice: ");
				int choice = input.nextInt();
				if(choice==1) {
					addReviewer();
				}
				else if(choice==2) {
					continue;
				}
					
			}
			else if(userChoice==2) {
				System.out.println("\n==============");
				System.out.println("REVIEWEE");
				System.out.println("==============");
				System.out.print("\n1. Do u want to place review? \n2. Exit \nEnter your choice: ");
				int choice = input.nextInt();
				if(choice==1) {
					System.out.println("\nYoy are placing a review..");
					requestPlacing();
				}
				else if(choice==2) {
					continue;
				}
				
				
			}
			else if (userChoice==3) {
				System.out.println("\n==============");
				System.out.println("REVIEWER");
				System.out.println("==============");
				System.out.print("\n1. Do u want to see review request? \n2. Exit \nEnter your choice: ");
				int choice = input.nextInt();
				if(choice==1) {
					System.out.println("\nYoy are seeing review request..");
					reviewTime();
				}
				else if(choice==2) {
					continue;
				}
			}
			else {
				System.out.println("Incorrect choice!!");
			}
		}while(true);
		
		
		/*//Admin Role
		addReviewer();
		
		//Reviewee placed a request
		requestPlacing();
		
		//Reviewer retrieving his requests
		reviewTime();*/
		
		
		
	}

	private static void reviewTime() {
		Reviewer reviewer = new Reviewer(getName(REVIEWER), getUserId(REVIEWER), getEmail(REVIEWER), getModules());
		reviewer.accumulateRequest();
		
		long timeReview=0;
		for(ReviewRequest requestReview : Reviewee.getReviewRequests()) {
			timeReview = requestReview.getReviewTime();
			System.out.println("Review Time: " + timeReview);
			//System.out.println(requestReview.getClosedTime());
			//System.out.println(requestReview.getRequestTime());
		}
	}

	private static void requestPlacing() {
		System.out.println("\nEnter your details:\n");
		String revieweeName = getName(REVIEWEE);
		int userId = getUserId(REVIEWEE);
		String email = getEmail(REVIEWEE);
		Reviewee reviewee = new Reviewee(revieweeName, userId, email);
		
		System.out.println("\nEnter reviewer details:\n");
		String reviewerName = getName(REVIEWER);
		String module = getModule();
		String codeChanges = getCodeChangesDesc();
		String status = getStatus();
		long requestTime = getRequestTime();
		reviewee.requestCodeReviews(reviewerName, module, codeChanges, status, requestTime);
	}

	private static void addReviewer() {
		Admin admin = new Admin("Tarun", 10, "tarun.kumar@iongroup.com");
	 	String choice = "N";
		do {
			System.out.println("Adding a reviewer");
			Reviewer reviewer = getReviewer();
			admin.addCodeReviewers(reviewer);
			System.out.println("Do you want to continue adding Reviewers(Y/N) ?");
			choice = input.next();
		}while(choice.equalsIgnoreCase("Y"));
	}
	
	private static long getRequestTime() {
		System.out.println("Enter request time: ");
		long requestTime = input.nextLong();
		return requestTime;
	}

	public static String getStatus() {
		System.out.println("Enter Status: ");
		String status = input.next();
		return status;
	}

	private static String getCodeChangesDesc() {
		System.out.println("Enter description of code changes: ");
		String codeChanges = input.next();

		return codeChanges;
	}


	private static String getModule() {
		System.out.println("Enter Module for the Reviewer: ");
		String module = input.next();

		return module;
	}


	private static Reviewer getReviewer() {
		
		return new Reviewer(getName(REVIEWER), getUserId(REVIEWER), getEmail(REVIEWER), getModules());
	}

		
	private static ArrayList<String> getModules() {
		ArrayList<String> modules = new ArrayList<String>();
		String choice = "Y";
		do {
			System.out.println("Enter Module for the Reviewer: ");
			String module = input.next();
			modules.add(module);
			System.out.println("Do you want to continue adding Module?");
			choice = input.next();
		}while(choice.equalsIgnoreCase("Y"));
		
		return modules;
	}

	private static String getEmail(String role) {
		System.out.println("Enter Email for "+role+" :");
		String emailId = input.next();
		return emailId;
	}

	private static int getUserId(String role) {
		System.out.println("Enter ID for "+role+" :");
		int userId = input.nextInt();
		return userId;
	}

	private static String getName(String role) {
		System.out.println("\nEnter Name of "+role+" :");
		String name = input.next();
		return name;
	}

	public static String getComments() {
		System.out.println("Enter the comments about the review: ");
		String comments = input.next();
		return comments;
	}

	public static long getClosedTime() {
		System.out.println("Enter the closing time of review: ");
		long closedTime = input.nextLong();
		return closedTime;
	}

}
