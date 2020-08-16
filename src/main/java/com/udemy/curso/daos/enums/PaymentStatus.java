package com.udemy.curso.daos.enums;

public enum PaymentStatus {

	WAITING(1, "Waiting"),
	DONE(2, "Done"),
	CANCELED(3, "Canceled");
	
	private int code;
	private String description;
	
	private PaymentStatus(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer code) {
		
		if(code == null) {
			return null;
		}
		
		for(PaymentStatus ps : PaymentStatus.values()) {
			if(code.equals(ps.getCode())){
				return ps;
			}
		}
		
		throw new IllegalArgumentException("Wrong ID: " + code);
	}
}
