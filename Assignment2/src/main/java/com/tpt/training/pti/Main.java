/**
 * 
 */
package com.tpt.training.pti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Suraj.Kumar
 *
 */
public class Main {
	
	static ArrayList<String> empFirstName = new ArrayList<>();
	static ArrayList<String> empLastName = new ArrayList<>();
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		addEmployees();
		
		PTI pti = new PTI(empFirstName, empLastName);
		
		int choice = 0;
		do {
			
			System.out.println("1. Lucky Draw \n2. List of unique Toy names \n3. Most popular Toy name \n4. WaitList for Tickets \n5. Enter any another integer to exit \nEnter your choice:");
			choice = input.nextInt();
			if(choice==1) {
				luckyDraw(pti);
				System.out.println("==================================================================");
			}
			
			else if(choice==2) {
				listToyName(pti);
				System.out.println("==================================================================");
			}
			
			else if(choice==3) {
				popularToyName(pti);
				System.out.println("==================================================================");
			}
			
			else if(choice==4) {
				waitList(pti);
				System.out.println("==================================================================");
			}
		}while(choice>=1 && choice<=4);	
		
	}

	private static void popularToyName(PTI pti) {
		System.out.println("Most popular name for a toy: is \"" + pti.maxCountForFirstName() + "\" which is based on max count of first names");
	}

	private static void luckyDraw(PTI pti) {
		System.out.println("Lucky Draw Winner to receive a free toy: " + pti.monthlyLuckyDraw());
	}

	private static void listToyName(PTI pti) {
		System.out.println("Unique Product Names:");
		ArrayList<String> uniqueNames = pti.newToyNames();
		for(String uniqueName : uniqueNames)
			System.out.println(uniqueName);
	}

	private static void waitList(PTI pti) {
		addEmployeesForTickets(pti);
		
		int index = 0;
		for(String waitName : pti.getEmpFirstNamesForTickets()) {
			index++;
			System.out.println(index + "th person in Waitlist is: " + waitName + " ");
		}
	}

	private static void addEmployeesForTickets(PTI pti) {
		Employee emp1 = new Employee("Suraj", "Kumar");
		Employee emp2 = new Employee("Ravi", "Kumar");
		Employee emp3 = new Employee("Aniket", "Gupta");
		
		Queue<String> empFirstNamesForTickets = new LinkedList<>();
		empFirstNamesForTickets.add(emp1.getFirstName());
		empFirstNamesForTickets.add(emp2.getFirstName());
		empFirstNamesForTickets.add(emp3.getFirstName());
		pti.setEmpFirstNamesForTickets(empFirstNamesForTickets);
		
	}

	private static void addEmployees() {
		Employee emp1 = new Employee("Suraj", "Kumar");
		Employee emp2 = new Employee("Ravi", "Kumar");
		Employee emp3 = new Employee("Suraj", "Gupta");
		Employee emp4 = new Employee("Abhishek", "Kumar");
		Employee emp5 = new Employee("Sonu", "Kumar");
		
		
		empFirstName.add(emp1.getFirstName());
		empFirstName.add(emp2.getFirstName());
		empFirstName.add(emp3.getFirstName());
		empFirstName.add(emp4.getFirstName());
		empFirstName.add(emp5.getFirstName());
		
		
		empLastName.add(emp1.getLastName());
		empLastName.add(emp2.getLastName());
		empLastName.add(emp3.getLastName());
		empLastName.add(emp4.getLastName());
		empLastName.add(emp5.getLastName());
	}
	
	



}
