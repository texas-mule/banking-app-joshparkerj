package com.revature.bank.joshparkerj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bank.joshparkerj.UserSession;

public class AccountDBHandler implements IDB.accounts {

	private StringBuilder s;
	private Connection con;

	private PreparedStatement addAccount;
	private final String addAccountString = "SELECT add_account(?, ?, ?);";
	private PreparedStatement uniqueAccountNumber;
	private final String uniqueAccountNumberString = "SELECT ? NOT IN (SELECT number FROM account);";
	private PreparedStatement deposit;
	private final String depositString = "SELECT deposit_funds(?, ?, ?);";
	private PreparedStatement withdraw;
	private final String withdrawString = "SELECT withdraw_funds(?, ?, ?);";
	private PreparedStatement transfer;
	private final String transferString = "SELECT transfer_funds(?, ?, ?, ?);";
	private PreparedStatement accountExists;
	private final String accountExistsString = "SELECT ? IN (SELECT number FROM account);";
	private PreparedStatement accountApproved;
	private final String accountApprovedString = "SELECT approved FROM account WHERE number = ?;";
	private PreparedStatement approveAccount;
	private final String approveAccountString = "UPDATE account SET approved = TRUE WHERE number = ?;";
	private PreparedStatement sufficientFunds;
	private final String sufficientFundsString = "SELECT balance >= ?::MONEY FROM account WHERE number = ?;";
	private PreparedStatement getBalance;
	private final String getBalanceString = "SELECT balance::TEXT FROM account WHERE number = ?;";
	private PreparedStatement getAccounts;
	private final String getAccountsString = "SELECT * FROM account;";
	private PreparedStatement overwriteBalance;
	private final String overwriteBalanceString = "UPDATE account SET balance = ?::MONEY WHERE number = ?;";
	private PreparedStatement editAccount;
	private final String editAccountString = "SELECT edit_account(?, ?, ?);";
	private PreparedStatement removeByNum;
	private final String removeByNumString = "SELECT delete_account(?);";
	private PreparedStatement isEmpty;
	private final String isEmptyString = "SELECT COUNT(*) = 0 FROM account;";

	AccountDBHandler(Connection c) throws SQLException {
		con = c;
		addAccount = con.prepareStatement(addAccountString);
		uniqueAccountNumber = con.prepareStatement(uniqueAccountNumberString);
		deposit = con.prepareStatement(depositString);
		withdraw = con.prepareStatement(withdrawString);
		transfer = con.prepareStatement(transferString);
		accountExists = con.prepareStatement(accountExistsString);
		accountApproved = con.prepareStatement(accountApprovedString);
		approveAccount = con.prepareStatement(approveAccountString);
		sufficientFunds = con.prepareStatement(sufficientFundsString);
		getBalance = con.prepareStatement(getBalanceString);
		getAccounts = con.prepareStatement(getAccountsString);
		overwriteBalance = con.prepareStatement(overwriteBalanceString);
		editAccount = con.prepareStatement(editAccountString);
		removeByNum = con.prepareStatement(removeByNumString);
		isEmpty = con.prepareStatement(isEmptyString);
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
			ResultSet rs = uniqueAccountNumber.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String deposit(String num, String sum) {
		try {
			deposit.setString(1, num);
			deposit.setString(2, UserSession.getID());
			deposit.setString(3, sum);
			ResultSet rs = deposit.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String withdraw(String num, String sum) {
		try {
			withdraw.setString(1, num);
			withdraw.setString(2, UserSession.getID());
			withdraw.setString(3, sum);
			ResultSet rs = deposit.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String transfer(String num, String dnum, String sum) {
		try {
			transfer.setString(1, num);
			transfer.setString(2, dnum);
			transfer.setString(3, UserSession.getID());
			transfer.setString(4, sum);
			ResultSet rs = deposit.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public boolean accountExists(String num) {
		try {
			accountExists.setString(1, num);
			ResultSet rs = accountExists.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean accountApproved(String num) {
		try {
			accountApproved.setString(1, num);
			ResultSet rs = accountApproved.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void approveAccount(String num) {
		try {
			approveAccount.setString(1, num);
			approveAccount.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean sufficientFunds(String num, String sum) {
		try {
			sufficientFunds.setString(1, sum);
			sufficientFunds.setString(2, num);
			ResultSet rs = sufficientFunds.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getBalance(String num) {
		try {
			getBalance.setString(1, num);
			ResultSet rs = getBalance.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getAccounts() {
		s = new StringBuilder();
		try {
			ResultSet rs = getAccounts.executeQuery();
			s.append("type\tnumber\tbalance\tapproved\n");
			while (rs.next()) {
				s.append(rs.getString("type"));
				s.append("\t");
				s.append(rs.getString("number"));
				s.append("\t");
				s.append(rs.getString("balance"));
				s.append("\t");
				s.append(rs.getString("approved"));
				s.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public String overwriteBalance(String num, String newValue) {
		try {
			overwriteBalance.setString(1,newValue);
			overwriteBalance.setString(2, num);
			overwriteBalance.execute();
			return newValue;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public boolean editAccount(String num, String newValue, String fieldToEdit) {
		try {
			editAccount.setString(1, num);
			editAccount.setString(2, newValue);
			editAccount.setString(3, fieldToEdit);
			ResultSet rs = editAccount.executeQuery();
			rs.next();
			return rs.getString(1).contains("edited account");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getDetailedAccounts() {
		return getAccounts();
	}

	public void removeByNum(String num) {
		try {
			removeByNum.setString(1, num);
			removeByNum.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		try {
			ResultSet rs = isEmpty.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
