package com.revature.bank.joshparkerj.db;

class Account {

	private String type;
	private String id;
	private String bal;
	private boolean approved;

	public Account(String t, String n, String b, String a) {
		type = t;
		id = n;
		bal = b;
		approved = (a.equals("t")) ? true : false;
	}

	public Account(String st) {
		String[] b = st.split("\t");
		type = b[1];
		id = b[2];
		bal = b[3];
		approved = (b[4].toLowerCase().startsWith("t")) ? true : false;
	}

	public String serialize() {
		return "account\t" + type + "\t" + id + "\t" + bal + "\t" + (approved ? "t" : "f") + "\n";
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
		if (cents < 0)
			return null;
		int remainder = cents % 100;
		String r = remainder < 10 ? ("0" + remainder) : ("" + remainder);
		bal = "$" + cents / 100 + "." + r;
		return bal;
	}

	public boolean unapproved() {
		return !this.approved;
	}

	public String summary() {
		return "Account number: " + id + "\tType: " + type + "\n";
	}

	public void approve() {
		approved = true;
	}

	public boolean sufficientFunds(String sum) {
		return (Integer.parseInt(bal.replaceAll("\\D", "")) - Integer.parseInt(sum.replaceAll("\\D", ""))) >= 0;
	}

	public String getBalance() {
		return bal;
	}

}
