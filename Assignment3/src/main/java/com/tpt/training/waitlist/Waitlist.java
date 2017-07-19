/**
 * 
 */
package com.tpt.training.waitlist;

import java.util.LinkedList;

/**
 * @author bwbss
 *
 */
public class Waitlist<T extends Family> implements IWaitlist<T> {

	
	LinkedList<T> queue = new LinkedList<>();

	@Override
	public boolean addUnique(T e) {
		if(!queue.contains(e)) {
			queue.add(e);
			return true;
		}
		return false;
	}

	@Override
	public int getPosition(T e) {
		for(int i=0; i<queue.size(); i++) {
			if((queue.get(i)).equals(e)) {
				return i+1;
			}
		}
		return -1;
	}

	
	@Override
	public boolean demandRemove(T e) {
		
		int index = getPosition(e);
		if(index!=-1) {
			queue.remove(index);
			return true;
		}
		return false;
		
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Waitlist other = (Waitlist) obj;
		if (queue == null) {
			if (other.queue != null)
				return false;
		} else if (!queue.equals(other.queue))
			return false;
		return true;
	}


	@Override
	public T removeLast(T e) {
		
		return queue.removeLast();
	}
	
	

}
