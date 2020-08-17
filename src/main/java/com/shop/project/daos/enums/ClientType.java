package com.shop.project.daos.enums;

public enum ClientType {

	INDIVIDUALPERSON(1, "Individual Person"),
	COMPANYPERSON(2, "Company Person");

	private int code;
	private String description;
	
	private ClientType(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer code) {
		
		if(code == null) {
			return null;
		}
		
		for(ClientType ct : ClientType.values()) {
			if(code.equals(ct.getCode())){
				return ct;
			}
		}
		
		throw new IllegalArgumentException("Wrong ID: " + code);
	}
}
