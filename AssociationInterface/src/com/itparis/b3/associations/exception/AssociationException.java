package com.itparis.b3.associations.exception;

public class AssociationException extends Exception {

	private static final long serialVersionUID = 662124285746975281L;
	
	private String occuredAt = "";
	private int httpCode = -1;
	private String message = "";

	
	public AssociationException (String message){
		super (message);
		this.message = message;
	}
	
	public AssociationException (){
		super ();
	}
	
	public AssociationException (int errorCode){
		super ();
		this.httpCode = errorCode;
	}
	
	public AssociationException (String message, String occuredAt, int errorCode){
		super (message);
		this.occuredAt = occuredAt;
		this.httpCode = errorCode;
		this.message = message;
	}

	public String getOccuredAt() {
		return occuredAt;
	}

	public void setOccuredAt(String occuredAt) {
		this.occuredAt = occuredAt;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
