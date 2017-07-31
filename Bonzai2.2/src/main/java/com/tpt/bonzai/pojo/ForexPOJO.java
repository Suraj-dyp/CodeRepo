package com.tpt.bonzai.pojo;

public class ForexPOJO {
	
	private String currency;
	private double inrFactor;
	
	public ForexPOJO(String currency, double inrFactor) {
		super();
		this.currency = currency;
		this.inrFactor = inrFactor;
	}

	@Override
	public String toString() {
		return "ForexPOJO [currency=" + currency + ", inrFactor=" + inrFactor + "]";
	}

	public String getCurrency() {
		return currency;
	}

	public double getInrFactor() {
		return inrFactor;
	}
	
	

}
