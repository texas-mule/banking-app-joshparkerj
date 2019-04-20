package com.revature.bank.joshparkerj.db;

class AccountHolder {
	
	private String ssn;
	private String num;
	
	public AccountHolder(String st) {
		String[] b = st.split("\t");
		ssn = b[1];
		num = b[2];
	}

	public AccountHolder(String i, String n) {
		ssn = i;
		num = n;
	}

	public String serialize() {
		return "ah\t" + ssn + "\t" + num + "\n";
	}

	public String getNum() {
		return num;
	}

	public String getSSN() {
		return ssn;
	}

	public boolean holdsAccount(String n, String i) {
		return n.equals(num) && i.equals(ssn);
	}

}
