package com.revature.bank.joshparkerj;

import java.util.List;

public class BankDB implements IDB {

	private static BankDB uniqueInstance = null;
	
	private String s;
	private TextFile t;
	private List<Account> accounts;
	private List<Customer> customers;
	private List<Employee> employees;
	
	public static BankDB getDB(String filename) {
		if (uniqueInstance == null) uniqueInstance = new BankDB(filename);
		return uniqueInstance;
	}
	
	private BankDB(String f) {
		t = new TextFile(f);
		accounts = t.getAccounts();
		customers = t.getCustomers();
		employees = t.getEmployees();
	}
	
	public String serialize() {
		s = "";
		accounts.forEach(account->this.s+=account.serialize());
		customers.forEach(customer->this.s+=customer.serialize());
		employees.forEach(employee->this.s+=employee.serialize());
		return s;
	}
	
	public void write() {
		t.writeToDisc(this);
	}

	public String getCustomerID(String name, String pass) {
		for (Customer c : customers) {
			String id = c.auth(name, pass);
			if (id != null) return id;
		}
		return null;
	}

	public String getEmployeeID(String name, String pass) {
		for (Employee e : employees) {
			String id = e.auth(name,pass);
			if (id != null) return id;
		}
		return null;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}
	
}
