package com.revature.bank.joshparkerj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountDBHandler implements IDB.accounts {

	private StringBuilder s;
	private Connection con;

	private PreparedStatement addAccount;
	private final String addAccountString = "SELECT add_account(?, ?, ?);";
	private PreparedStatement uniqueAccountNumber;
	private final String uniqueAccountNumberString = "SELECT ? NOT IN (SELECT number FROM account);";

	AccountDBHandler(Connection c) throws SQLException {
		con = c;
		addAccount = con.prepareStatement(addAccountString);
		uniqueAccountNumber = con.prepareStatement(uniqueAccountNumberString);
	}

	public String serialize() {
		return null;
	}

	public void addAccount(String type, String number) {
		try {
			addAccount.setString(1, type);
			addAccount.setString(2, number);
			addAccount.setString(3, "$0.00");
			addAccount.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean uniqueAccountNumber(String num) {
		try {
			uniqueAccountNumber.setString(1, num);
			ResultSet rs = 
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		accounts.removeIf(a -> a.getID().equals(num));
	}

	public boolean isEmpty() {
		return accounts.isEmpty();
	}

	public List<Account> getByNum(String num) {
		List<Account> la = new LinkedList<Account>();
		for (Account a : accounts) {
			if (a.getID().equals(num))
				la.add(a);
		}
		return la;
	}

	public List<Account> getUnapproved() {
		List<Account> la = new LinkedList<Account>();
		for (Account a : accounts) {
			if (a.unapproved())
				la.add(a);
		}
		return la;
	}

}
