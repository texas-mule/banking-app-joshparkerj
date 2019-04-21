package com.revature.bank.joshparkerj.db;

import java.util.List;

public class BankDB implements IDB {

	private static BankDB uniqueInstance = null;

	private String s;
	private TextFile t;
	private List<Account> accounts;
	private List<Customer> customers;
	private List<Employee> employees;
	private List<AccountHolder> accountholders;

	public static BankDB getDB(String filename) {
		if (uniqueInstance == null)
			uniqueInstance = new BankDB(filename);
		return uniqueInstance;
	}

	private BankDB(String f) {
		t = new TextFile(f);
		accounts = t.getAccounts();
		customers = t.getCustomers();
		employees = t.getEmployees();
		accountholders = t.getAccountHolders();
	}

	public String serialize() {
		s = "";
		accounts.forEach(account -> this.s += account.serialize());
		customers.forEach(customer -> this.s += customer.serialize());
		employees.forEach(employee -> this.s += employee.serialize());
		accountholders.forEach(ah -> this.s += ah.serialize());
		return s;
	}

	public void write() {
		t.writeToDisc(this);
	}

	public String getCustomerID(String name, String pass) {
		for (Customer c : customers) {
			String id = c.auth(name, pass);
			if (id != null)
				return id;
		}
		return null;
	}

	public String getEmployeeID(String name, String pass) {
		for (Employee e : employees) {
			String id = e.auth(name, pass);
			if (id != null)
				return id;
		}
		return null;
	}

	public void addCustomer(String name, String pass, String id) {
		customers.add(new Customer(name, pass, id));
	}

	public void addEmployee(String name, String pass, String id, String supid, String pay, String first, String last,
			String loc) {
		employees.add(new Employee(name, pass, id, supid, pay, first, last, loc, "f"));
	}

	public void addAccount(String type, String number) {
		accounts.add(new Account(type, number, "$0.00", "f"));
	}

	public void addAccountHolder(String id, String num) {
		accountholders.add(new AccountHolder(id, num));
	}

	public boolean uniqueAccountNumber(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return false;
		}
		return true;
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

	public String getCustomerAccounts(String id) {
		s = "";
		for (AccountHolder ah : accountholders) {
			if (ah.getSSN().equals(id)) {
				for (Account a : accounts) {
					if (ah.getNum().equals(a.getID())) {
						s += a.prettyPrint();
					}
				}
			}
		}
		if (s.length() == 0)
			s = "You have no accounts!\n";
		return s;
	}

	public boolean employeeExists(String supid) {
		for (Employee e : employees) {
			if (e.getID().equals(supid))
				return true;
		}
		return false;
	}

	public String deposit(String num, String sum) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return a.deposit(sum);
		}
		return null;
	}

	public boolean holdsAccount(String num, String id) {
		return accountholders.stream().anyMatch(ah -> ah.getNum().equals(num) && ah.getSSN().equals(id));
	}

	public String withdraw(String num, String sum) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return a.withdraw(sum);
		}
		return null;
	}

	public String transfer(String num, String dnum, String sum) {
		String finalSum = null;
		for (Account a : accounts) {
			for (Account b : accounts) {
				if (a.getID().equals(num) && b.getID().equals(dnum)) {
					finalSum = a.withdraw(sum);
					if (finalSum != null)
						b.deposit(sum);
					return finalSum;
				}
			}
		}
		return finalSum;
	}

	public boolean accountExists(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return true;
		}
		return false;
	}

	public String getCustomers() {
		s = "";
		customers.forEach(customer -> this.s += customer.summary());
		return s;
	}

	public String getPendingApps() {
		s = "";
		for (Account a : accounts) {
			if (a.unapproved()) {
				for (AccountHolder ah : accountholders) {
					if (ah.getNum().equals(a.getID())) {
						for (Customer c : customers) {
							if (c.getID().equals(ah.getSSN())) {
								s += c.summary() + a.summary();
							}
						}
					}
				}
			}
		}
		return s;
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

	public boolean accountApproved(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num)) return !a.unapproved();
		}
		return false;
	}

	public void approveAccount(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num)) {
				a.approve();
				return;
			}
		}
	}

	public void denyAccount(String num) {
		accounts.removeIf(a->a.getID().equals(num));
		accountholders.removeIf(ah->ah.getNum().equals(num));
	}

}
