package com.hand.hrms4android.listable.item;

public class TodoListItem extends TextListItem {

	private String errorMessage;
	private String status;
	private int verificationId;
	private boolean isLate;

	public TodoListItem() {
		this(0, null, null, null, null, null, null,0, false);
	}

	public TodoListItem(int id, String title, String date, String subTitle, String information, String errorMessage,
	        String status,int verificationId ,boolean isLate) {
		super();
		this.id = id;
		this.title = title;
		this.titleRight = date;
		this.subTitle = subTitle;
		this.information = information;
		this.errorMessage = errorMessage;
		this.status = status;
		this.verificationId = verificationId;
		this.isLate = isLate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getVerificationId(){
		return verificationId;
	} 
	
	public void setVerificationId(int verificationId){
		this.verificationId = verificationId;
	}
	
	public boolean isLate() {
		return isLate;
	}

	public void setLate(boolean isLate) {
		this.isLate = isLate;
	}
}
