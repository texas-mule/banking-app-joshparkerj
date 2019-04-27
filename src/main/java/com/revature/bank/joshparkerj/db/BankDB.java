package com.revature.bank.joshparkerj.db;

public class BankDB implements IDB {

	private static BankDB uniqueInstance = null;

	private StringBuilder s;
	private TextFile t;
	private AccountDBHandler accounts;
	private CustomerDBHandler customers;
	private EmployeeDBHandler employees;
	private AccountHolderDBHandler accountholders;

	public static BankDB getDB(String filename) {
		if (uniqueInstance == null)
			uniqueInstance = new BankDB(filename);
		return uniqueInstance;
	}

	private BankDB(String f) {
		t = new TextFile(f);
		accounts = new AccountDBHandler(t.getAccounts());
		customers = new CustomerDBHandler(t.getCustomers());
		employees = new EmployeeDBHandler(t.getEmployees());
		accountholders = new AccountHolderDBHandler(t.getAccountHolders());
	}
	
	public accounts account() {
		return accounts;
	}
	
	public employees employee() {
		return employees;
	}
	
	public accountholders accountholder() {
		return accountholders;
	}
	
	public customers customer() {
		return customers;
	}

	public String serialize() {
		s = new StringBuilder();
		s.append(accounts.serialize());
		s.append(customers.serialize());
		s.append(employees.serialize());
		s.append(accountholders.serialize());
		return s.toString();
	}

	public void write() {
		t.writeToDisc(this);
	}

	public String getCustomerAccounts(String id) {
		s = new StringBuilder();
		for (AccountHolder ah : accountholders.getBySSN(id)) {
			for (Account a : accounts.getByNum(ah.getNum())){
				if (!ah.isApproved()) s.append("APPLICATION PENDING: \n");
				s.append(a.prettyPrint());
			}
		}
		if (s.length() == 0)
			s.append("You have no accounts!\n");
		return s.toString();
	}

	public String getPendingApps() {
		s = new StringBuilder();
		for (Account a : accounts.getUnapproved()) {
			for (AccountHolder ah : accountholders.getByNum(a.getID())) {
				for (Customer c : customers.getByID(ah.getSSN())) {
					s.append(c.summary() + a.summary());
				}
			}
		}
		return s.toString();
	}

	public void close() {
		uniqueInstance = null;
	}

	public void deleteCustomer(String id) {
		customers.removeBySSN(id);
		accountholders.removeBySSN(id);
	}

	public void deleteAccount(String id) {
		accounts.removeByNum(id);
		accountholders.removeByNum(id);
	}

	public boolean uninitialized() {
		return (accounts.isEmpty() && customers.isEmpty() && accountholders.isEmpty() && employees.isEmpty());
	}

}
