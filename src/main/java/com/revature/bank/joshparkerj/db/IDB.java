package com.revature.bank.joshparkerj.db;

public interface IDB {

	void write();

	String getCustomerID(String name, String pass);

	String getEmployeeID(String name, String pass);

	void addCustomer(String name, String pass, String id);

	void addEmployee(String name, String pass, String id, String supid, String pay, String first, String last,
			String loc);

	void addAccount(String type, String number);

	String serialize();

	void addAccountHolder(String id, String number);

	boolean uniqueAccountNumber(String num);

	boolean uniqueCustomerName(String name);

	boolean uniqueSSN(String id);

	String getCustomerAccounts(String id);

	boolean employeeExists(String id);

	String deposit(String num, String sum);

	boolean holdsAccount(String id, String num);

	String withdraw(String num, String sum);

	String transfer(String num, String dnum, String sum);

	boolean accountExists(String num);

	String getCustomers();

	String getPendingApps();

	boolean customerExists(String id);

	String customerDetails(String id);

	boolean accountApproved(String num);

	void approveAccount(String num);

	void denyAccount(String num);
	
	void close();
	
	void deleteCustomer(String id);

	void deleteEmployee(String id);
	
	void deleteAccount(String id);

	boolean sufficientFunds(String num, String sum);

	String getBalance(String num);

	boolean uninitialized();

	String getJointApps(String id);

	void addJointAccount(String id, String num);
	
	void approveAccountHolder(String id, String num);

	boolean jointAppExists(String id, String num);

	void denyJointApp(String id, String num);

}
