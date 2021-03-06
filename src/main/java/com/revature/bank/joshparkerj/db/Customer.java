package com.revature.bank.joshparkerj.db;

class Customer extends ABCUser {

	public Customer(String n, String p, String i) {
		name = n;
		pass = p;
		id = i;
	}

	public Customer(String st) {
		String[] b = st.split("\t");
		name = b[1];
		pass = b[2];
		id = b[3];
	}

	public String serialize() {
		return "customer\t" + name + "\t" + pass + "\t" + id + "\n";
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return id;
	}

	public String summary() {
		return "Username:\t" + name + "\tSSN:\t" + id + "\n";
	}

	public String details() {
		return "Username:\t" + name + "\nSSN:\t" + id + "\nPassword:\t" + pass;
	}

}
