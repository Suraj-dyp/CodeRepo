/**
 * 
 */
package com.tpt.training.evaluation;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Suraj.Kumar
 *
 */
public class Admin extends User {
	
	ArrayList<Reviewer> reviewers = new ArrayList<Reviewer>();
	
	public Admin(String name, int userId, String emailId) {
		super(name,userId, emailId);
	}
	
	public void addCodeReviewers(Reviewer reviewer) {
		
		reviewers.add(reviewer);
		
	}

	/*public void getReviewers() {
		for(Reviewer r : reviewers) {
			System.out.println(r);
		}
		
	}*/
	
	

}
