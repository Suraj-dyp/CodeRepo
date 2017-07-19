package com.tpt.training.waitlist;

public class Family {
	
	private String familyName;
	private String address;
	private long phoneNo;
	
	public Family(String familyName, String address, long phoneNo) {
		super();
		this.familyName = familyName;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getAddress() {
		return address;
	}

	public long getPhoneNo() {
		return phoneNo;
	}
	
	


}
