package com.revature.bank.joshparkerj;

public class UserSession {

	private static UserSession uniqueSession = null;
	
	private String userType;
	private String userID;
	
	public static void init(String type, String id) {
		if (uniqueSession == null) uniqueSession = new UserSession(type,id);
	}
	
	public static String getID() {
		return uniqueSession.userID;
	}
	
	private UserSession(String type, String id) {
		userType = type;
		userID = id;
	}
	
}
