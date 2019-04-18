package com.revature.bank.joshparkerj;

public interface IDB {

	void write();
	String getCustomerID(String name, String pass);
	String getEmployeeID(String name, String pass);
	void addCustomer(Customer customer);
	void addEmployee(Employee employee);
	void addAccount(Account account);
	String serialize();

}
