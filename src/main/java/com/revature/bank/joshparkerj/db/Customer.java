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
		return String.format("%9s%22s%5s%22s\n", "Username:", name, "SSN:", id);
	}

	public String details() {
		return String.format("%10s%22s%5s%22s%11s%22s\n", "Username:", name, "SSN:", id, "Password:", pass);
	}

}
