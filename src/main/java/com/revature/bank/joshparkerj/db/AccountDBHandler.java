package com.revature.bank.joshparkerj.db;

import java.util.LinkedList;
import java.util.List;

public class AccountDBHandler implements IDB.accounts {

	private List<Account> accounts;
	private StringBuilder s;

	public AccountDBHandler(List<Account> a) {
		accounts = a;
	}

	public String serialize() {
		s = new StringBuilder();
		accounts.forEach(account -> this.s.append(account.serialize()));
		return s.toString();
	}

	public void addAccount(String type, String number) {
		accounts.add(new Account(type, number, "$0.00"));
	}

	public boolean uniqueAccountNumber(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return false;
		}
		return true;
	}

	public String deposit(String num, String sum) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return a.deposit(sum);
		}
		return null;
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

	public boolean accountApproved(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return !a.unapproved();
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

	public boolean sufficientFunds(String num, String sum) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return a.sufficientFunds(sum);
		}
		return false;
	}

	public String getBalance(String num) {
		for (Account a : accounts) {
			if (a.getID().equals(num))
				return a.getBalance();
		}
		return null;
	}

	public String getAccounts() {
		s = new StringBuilder();
		accounts.forEach(account -> this.s.append(account.summary()));
		return s.toString();
	}

	public String overwriteBalance(String num, String newValue) {
		for (Account a : accounts) {
			if (a.getID().equals(num)) {
				a.setBalance(newValue);
				return a.getBalance();
			}
		}
		return "";
	}

	public boolean editAccount(String num, String newValue, String fieldToEdit) {
		for (Account a : accounts) {
			if (a.getID().equals(num)) {
				switch (fieldToEdit) {
				case "number":
					a.setNumber(newValue);
					return true;
				case "type":
					a.setType(newValue);
					return true;
				case "approval status":
					a.setApprovalStatus(newValue);
					return true;
				default:
					return false;
				}
			}
		}
		return false;
	}

	public String getDetailedAccounts() {
		s = new StringBuilder();
		accounts.forEach(account -> this.s.append(account.details()));
		return s.toString();
	}

	public void removeByNum(String num) {
		accounts.removeIf(a->a.getID().equals(num));
	}

	public boolean isEmpty() {
		return accounts.isEmpty();
	}

	public List<Account> getByNum(String num) {
		List <Account> la = new LinkedList<Account>();
		for (Account a : accounts) {
			if (a.getID().equals(num)) la.add(a);
		}
		return la;
	}

	public List<Account> getUnapproved() {
		List<Account> la = new LinkedList<Account>();
		for (Account a : accounts) {
			if (a.unapproved()) la.add(a);
		}
		return la;
	}

}
