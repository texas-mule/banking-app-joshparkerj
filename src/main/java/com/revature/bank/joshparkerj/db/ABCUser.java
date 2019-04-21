package com.revature.bank.joshparkerj.db;

abstract class ABCUser {

	protected String name;
	protected String pass;
	protected String id;

	public String auth(String n, String p) {
		return n.equals(name) && p.equals(pass) ? id : null;
	}

	abstract String serialize();
}
