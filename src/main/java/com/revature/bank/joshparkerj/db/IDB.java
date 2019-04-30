package com.revature.bank.joshparkerj.db;

public interface IDB {

	void write();
	String serialize();
	String getCustomerAccounts(String id);
	String getPendingApps();
	void close();
	void deleteCustomer(String id);
	void deleteAccount(String num);
	boolean uninitialized();
	accounts account();
	customers customer();
	employees employee();
	accountholders accountholder();

	interface customers {

		String getCustomerID(String name, String pass);
		void addCustomer(String name, String pass, String id);
		boolean uniqueCustomerName(String name);
		String customerDetails(String id);
		boolean customerExists(String id);
		String getCustomers();
		boolean uniqueSSN(String id);
		void removeBySSN(String ssn);

	}

	interface employees {

		boolean employeeExists(String id);
		String getEmployeeID(String name, String pass);
		boolean isAdmin(String id);
		void deleteEmployee(String id);
		void addEmployee(String name, String pass, String id, String supid, String pay, String first, String last, String loc);

	}

	interface accounts {
		
		boolean sufficientFunds(String num, String sum);
		String withdraw(String num, String sum);
		String transfer(String num, String dnum, String sum);
		String deposit(String num, String sum);
		boolean uniqueAccountNumber(String num);
		boolean accountExists(String num);
		void approveAccount(String num);
		String getAccounts();
		boolean accountApproved(String num);
		void addAccount(String type, String number);
		String overwriteBalance(String num, String newValue);
		boolean editAccount(String num, String newValue, String fieldToEdit);
		String getDetailedAccounts();
		String getBalance(String num);
		void removeByNum(String num);

	}

	interface accountholders {

		void addAccountHolder(String id, String number);
		void approveAccountHolder(String id, String num);
		void denyJointApp(String id, String num);
		void addJointAccount(String id, String num);
		String getJointApps(String id);
		boolean holdsAccount(String id, String num);
		boolean jointAppExists(String id, String num);
		void removeBySSN(String ssn);
		void removeByNum(String num);

	}

}
