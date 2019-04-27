package com.revature.bank.joshparkerj.db;

import java.util.LinkedList;
import java.util.List;

public class CustomerDBHandler implements IDB.customers {

	private StringBuilder s;
	private List<Customer> customers;

	CustomerDBHandler(List<Customer> c) {
		customers = c;
	}

	public String serialize() {
		s = new StringBuilder();
		customers.forEach(customer -> this.s.append(customer.serialize()));
		return s.toString();
	}

	public String getCustomerID(String name, String pass) {
		for (Customer c : customers) {
			String id = c.auth(name, pass);
			if (id != null)
				return id;
		}
		return null;
	}

	public void addCustomer(String name, String pass, String id) {
		customers.add(new Customer(name, pass, id));
	}

	public boolean uniqueCustomerName(String name) {
		for (Customer c : customers) {
			if (c.getName().equals(name))
				return false;
		}
		return true;
	}

	public boolean uniqueSSN(String id) {
		for (Customer c : customers) {
			if (c.getID().equals(id))
				return false;
		}
		return true;
	}

	public String getCustomers() {
		s = new StringBuilder();
		customers.forEach(customer -> this.s.append(customer.summary()));
		return s.toString();
	}

	public boolean customerExists(String id) {
		for (Customer c : customers) {
			if (c.getID().equals(id))
				return true;
		}
		return false;
	}

	public String customerDetails(String id) {
		for (Customer c : customers) {
			if (c.getID().equals(id))
				return c.details();
		}
		return null;
	}

	public void removeBySSN(String id) {
		customers.removeIf(c->c.getID().equals(id));
	}

	public boolean isEmpty() {
		return customers.isEmpty();
	}

	public List<Customer> getByID(String ssn) {
		List<Customer> lc = new LinkedList<Customer>();
		for (Customer c : customers) {
			if (c.getID().equals(ssn)) lc.add(c);
		}
		return lc;
	}

}
