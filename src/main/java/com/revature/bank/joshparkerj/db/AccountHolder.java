package com.revature.bank.joshparkerj.db;

class AccountHolder {

	private String ssn;
	private String num;
	private boolean approved;

	public AccountHolder(String st) {
		String[] b = st.split("\t");
		ssn = b[1];
		num = b[2];
		approved = b[3].toLowerCase().startsWith("t") ? true : false;
	}

	public AccountHolder(String i, String n, boolean a) {
		ssn = i;
		num = n;
		approved = a;
	}

	public String serialize() {
		return "ah\t" + ssn + "\t" + num + "\t" + (approved ? "t" : "n") + "\n";
	}

	public String getNum() {
		return num;
	}

	public String getSSN() {
		return ssn;
	}
	
	public void approve() {
		approved = true;
	}

}
