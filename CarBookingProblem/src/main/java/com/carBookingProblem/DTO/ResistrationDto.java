package com.carBookingProblem.DTO;

public class ResistrationDto {
	
	private String fName;
	private String lName;
	private String contactNum;
	private String emailId;
	private int userType;
	private int seatfillUp;
	
	private boolean isAvailable;
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getSeatfillUp() {
		return seatfillUp;
	}
	public void setSeatfillUp(int seatfillUp) {
		this.seatfillUp = seatfillUp;
	}
	

}
