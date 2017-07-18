/**
 * 
 */
package com.tpt.training.pti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Suraj.Kumar
 *
 */
public class PTI {
	
	ArrayList<String> empFirstNames = new ArrayList<String>();
	ArrayList<String> empLastNames = new ArrayList<String>();
	
	Queue<String> empFirstNamesForTickets = new LinkedList<>();



	public PTI(ArrayList<String> empFirstNames, ArrayList<String> empLastNames) {
		this.empFirstNames = empFirstNames;
		this.empLastNames = empLastNames;
	
	}
	

	public void setEmpFirstNamesForTickets(Queue<String> empFirstNamesForTickets) {
		this.empFirstNamesForTickets = empFirstNamesForTickets;
	}
	
	public Queue<String> getEmpFirstNamesForTickets() {
		return empFirstNamesForTickets;
	}

	public String monthlyLuckyDraw() {
		
		Random rnd = new Random();
		int randomInt = rnd.nextInt(empFirstNames.size());
		//System.out.println(randomInt);
		return empFirstNames.get(randomInt) + " " + empLastNames.get(randomInt);
	}
	
	


	public ArrayList<String> newToyNames() {
		
		ArrayList<String> uniqueNames = new ArrayList<>();
		for(String empFirstName : empFirstNames) {
			if(uniqueNames.contains(empFirstName)) 
				continue;
			else 
				uniqueNames.add(empFirstName);
		}
		
		return uniqueNames;
	}
	
	public String maxCountForFirstName() {
		
		int tempCount = 0, count = 0, index = 0;
		for(int i=0; i<empFirstNames.size(); i++) {
			tempCount++;
			for(int j=i+1; j<empFirstNames.size(); j++) {
				if((empFirstNames.get(i)).equalsIgnoreCase(empFirstNames.get(j)))
					tempCount++;
			}
			if(tempCount>=count) {
				count = tempCount;
				index = i;
			}
			tempCount = 0;
				
		}
		
		return empFirstNames.get(index);
		
		
	}

	/*public void setEmpFirstName(ArrayList<String> empFirstName) {
		this.empFirstName = empFirstName;
	}

	public void setEmpLastName(ArrayList<String> empLastName) {
		this.empLastName = empLastName;
	}*/
	
	/*public void addEmpFirstName(String empFirstName) {
		this.empFirstNames.add(empFirstName);
	}

	public void addEmpLastName(String empLastName) {
		this.empLastNames.add(empLastName);
	}*/
	
	
	
	
	
	
	

}
