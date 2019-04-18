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

}
