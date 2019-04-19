package com.revature.bank.joshparkerj;

public interface IDB {

	void write();
	String getCustomerID(String name, String pass);
	String getEmployeeID(String name, String pass);
	void addCustomer(Customer customer);
	void addEmployee(Employee employee);
	void addAccount(Account account);
	String serialize();
	void addAccountHolder(AccountHolder accountHolder);
	boolean uniqueAccountNumber(String num);
	boolean uniqueCustomerName(String name);
	boolean uniqueSSN(String id);
	String getCustomerAccounts(String id);
	boolean employeeExists(String supid);
	String deposit(String num, String sum);
	boolean holdsAccount(String num, String id);

}
