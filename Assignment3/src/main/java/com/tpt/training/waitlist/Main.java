/**
 * 
 */
package com.tpt.training.waitlist;

/**
 * @author bwbss
 *
 */
public class Main {
	
	static Family family1, family2, family3, family4, family5;
	public static void main(String[] args) {
		
		addfamily();
		
		Waitlist<Family> atRestaurant = new Waitlist<>();
		addInWaitList(atRestaurant);
		
		getPosition(atRestaurant);
		
		demandRemove(atRestaurant, family2);
		
		
		
		
		
	}

	private static void demandRemove(Waitlist<Family> atRestaurant, Family family) {
		if(atRestaurant.demandRemove(family))
			System.out.println(family.getFamilyName() + " family removed from waitlist");
		else
			System.out.println(family.getFamilyName() + " family not removed from waitlist because it does not exists in waitlist");
		
	}

	private static void getPosition(Waitlist<Family> atRestaurant) {
		System.out.println("Position of family " + family4.getFamilyName() + " in waitlist is: " + atRestaurant.getPosition(family4));
	}

	private static void addInWaitList(Waitlist<Family> atRestaurant) {
		
		System.out.println("Family Added: " + atRestaurant.addUnique(family1));
		System.out.println("Family Added: " + atRestaurant.addUnique(family2));
		System.out.println("Family Added: " + atRestaurant.addUnique(family3));
		System.out.println("Family Added: " + atRestaurant.addUnique(family4));
		family5 = family1;
		System.out.println("Family Added: " + atRestaurant.addUnique(family5));
	}

	private static void addfamily() {
		family1 = new Family("Agrawal", "Akurdi", 7757889510L);
		family2 = new Family("Kumar", "Pimpri", 7757889508L);
		family3 = new Family("Singh", "Pimpri", 7757889509L);
		family4 = new Family("Badkas", "Pimpri", 7757889908L);
	}

}
