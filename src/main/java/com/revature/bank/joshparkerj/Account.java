package com.revature.bank.joshparkerj;

public class Account {
	
	private String type;
	private String id;
	private String bal;
	
	public Account(String t, String n, String b) {
		type = t;
		id = n;
		bal = b;
	}

	public Account(String st) {
		String[]b = st.split("\t");
		type = b[1];
		id = b[2];
		bal = b[3];
	}

	public String serialize() {
		return "account\t" + type + "\t" + id + "\t" + bal + "\n";
	}
	
	public String prettyPrint() {
		return "Account type: " + type + "\nAccount Number: " + id + "\nBalance: " + bal + "\n\n";
	}

	public String getID() {
		return id;
	}

	public String deposit(String sum) {
		int cents = Integer.parseInt(bal.replaceAll("\\D", "")) + Integer.parseInt(sum.replaceAll("\\D", ""));
		int remainder = cents % 100;
		String r = remainder < 10 ? ("0" + remainder) : ("" + remainder);
		bal = "$" + cents / 100 + "." + r;
		return bal;
	}

	public String withdraw(String sum) {
		int cents = Integer.parseInt(bal.replaceAll("\\D", "")) - Integer.parseInt(sum.replaceAll("\\D", ""));
		if (cents < 0) return null;
		int remainder = cents % 100;
		String r = remainder < 10 ? ("0" + remainder) : ("" + remainder);
		bal = "$" + cents / 100 + "." + r;
		return bal;
	}

}
