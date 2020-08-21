package com.shop.project.daos.enums;

public enum Profile {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private int code;
	private String description;
	
	private Profile(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Profile toEnum(Integer code) {
		
		if(code == null) {
			return null;
		}
		
		for(Profile ps : Profile.values()) {
			if(code.equals(ps.getCode())){
				return ps;
			}
		}
		
		throw new IllegalArgumentException("Wrong ID: " + code);
	}
}
