package com.api.constant;

public enum Problem {

	SMARTPHONE_IS_RUNNING_SLOW(1),
	POOR_BATTERY_LIFE(2), 
	PHONE_OR_APP_CRASHES(3), 
	SYNC_ISSUE(4),
	MICROSD_CARD_IS_NOT_WORKING_ON_YOUR_PHONE(5), 
	OVERHEATING(6);
//	Connecting_problem_with_Bluetooth, Wifi, Cellular network
//	Cracked_screen, 
//	Other,
//	Camera_issue, 
//	Charger_Not_Working, 
//	Software_Booting_Issue, 
	
	int code;
	private Problem(int code)
	{
		this.code=code;
	}
	
	public int getCode()
	{
		return code;
	}
	
	
	
	
}
