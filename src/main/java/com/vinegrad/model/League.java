package com.vinegrad.model;

public enum League {
	
	SUPER_LEAGUE("Super League", "SL", 1);
	
	String displayName, initials;
	int tier;
	
	League(String displayName, String initials, int tier) {
		this.displayName = displayName;
		this.initials = initials;
		this.tier = tier;
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
