package com.revature.bank.joshparkerj;

public class UserSession {

	private static UserSession uniqueSession = null;
	
	private String userID;
	
	public static void init(String id) {
		if (uniqueSession == null) uniqueSession = new UserSession(id);
	}
	
	public static String getID() {
		return uniqueSession.userID;
	}
	
	private UserSession(String id) {
		userID = id;
	}

	public static void end() {
		uniqueSession = null;
	}
	
}
