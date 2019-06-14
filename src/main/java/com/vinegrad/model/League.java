package com.vinegrad.model;

public enum League {
	
	SUPER_LEAGUE("Super League", "SL", 1), CHAMPIONSHIP("Championship", "CH", 2);
	
	String displayName, initials;
	int tier;
	
	League(String displayName, String initials, int tier) {
		this.displayName = displayName;
		this.initials = initials;
		this.tier = tier;
	}
	
	public static League of(String initials) {
		switch(initials) {
		case "SL" :
			return SUPER_LEAGUE;
		case "CH" :
			return CHAMPIONSHIP;
		default :
			return CHAMPIONSHIP;
		}
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getInitials() {
		return initials;
	}
	
	public int getTier() {
		return tier;
	}
}
