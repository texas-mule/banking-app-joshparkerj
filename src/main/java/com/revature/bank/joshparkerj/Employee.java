package com.revature.bank.joshparkerj;

public class Employee extends ABCUser {

	private String supid;
	private String pay;
	private String first;
	private String last;
	private String loc;
	private String admin;

	public Employee(String n, String p, String i, String s, String q, String f, String l, String m, String a) {
		name = n;
		pass = p;
		id = i;
		supid = s;
		pay = q;
		first = f;
		last = l;
		loc = m;
		admin = a;
	}

	public Employee(String st) {
		String[] b = st.split("\t");
		name = b[1];
		pass = b[2];
		id = b[3];
		supid = b[4];
		pay = b[5];
		first = b[6];
		last = b[7];
		loc = b[8];
		admin = b[9];
	}

	public String serialize() {
		return "employee\t" + name + "\t" + pass + "\t" + id + "\t" + supid + "\t" + pay + "\t" + first + "\t" + last
				+ "\t" + loc + "\t" + admin + "\n";
	}

}
