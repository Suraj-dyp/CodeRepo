package com.tpt.training.waitlist;

public interface IWaitlist<T extends Family> {
	
	public boolean addUnique(T e);
	public int getPosition(T e);
	public boolean demandRemove(T e);
	public T removeLast(T e);
	

}
