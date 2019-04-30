package com.revature.bank.joshparkerj.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankDB implements IDB {

	private static BankDB uniqueInstance = null;

	Connection con;
	private StringBuilder s;
	private AccountDBHandler accounts;
	private CustomerDBHandler customers;
	private EmployeeDBHandler employees;
	private AccountHolderDBHandler accountholders;
	private final String url = "jdbc:postgresql://127.0.0.1:5432/banking_app";

	private PreparedStatement deleteAccount;
	private final String deleteAccountString = "SELECT delete_account(?);";
	private PreparedStatement deleteCustomer;
	private final String deleteCustomerString = "SELECT delete_customer(?);";
	private PreparedStatement getPendingApps;
	private final String getPendingAppsString = "SELECT * FROM pending_apps;";
	private PreparedStatement getCustomerAccounts;
	private final String getCustomerAccountsString = "SELECT * FROM get_customer_accounts(?);";

	public static BankDB getDB(String filename) throws SQLException {
		if (uniqueInstance == null)
			uniqueInstance = new BankDB(filename);
		return uniqueInstance;
	}

	private BankDB(String f) throws SQLException {
		con = DriverManager.getConnection(url);
		deleteAccount = con.prepareStatement(deleteAccountString);
		deleteCustomer = con.prepareStatement(deleteCustomerString);
		getPendingApps = con.prepareStatement(getPendingAppsString);
		getCustomerAccounts = con.prepareStatement(getCustomerAccountsString);
		accounts = new AccountDBHandler(con);
		customers = new CustomerDBHandler(con);
		employees = new EmployeeDBHandler(con);
		accountholders = new AccountHolderDBHandler(con);
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

	public String getCustomerAccounts(String id) {
		try {
			s = new StringBuilder();
			s.append("account_type\taccount_number\taccount_balance\taccount_pending\n");
			getCustomerAccounts.setString(1, id);
			ResultSet rs = getCustomerAccounts.executeQuery();
			while (rs.next()) {
				s.append(rs.getString("account_type"));
				s.append("\t");
				s.append(rs.getString("account_number"));
				s.append("\t");
				s.append(rs.getString("account_balance"));
				s.append("\t");
				s.append(rs.getString("account_pending"));
				s.append("\n");
			}
			return s.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String getPendingApps() {
		try {
			s = new StringBuilder();
			s.append("customer_name\tcustomer_ssn\taccount_type\taccount_number\taccount_balance\n");
			ResultSet rs = getPendingApps.executeQuery();
			while (rs.next()) {
				s.append(rs.getString("customer_name"));
				s.append("\t");
				s.append(rs.getString("customer_ssn"));
				s.append("\t");
				s.append(rs.getString("account_type"));
				s.append("\t");
				s.append(rs.getString("account_number"));
				s.append("\t");
				s.append(rs.getString("account_balance"));
				s.append("\n");
			}
			return s.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void close() {
		uniqueInstance = null;
	}

	public void deleteCustomer(String id) {
		try {
			deleteCustomer.setString(1, id);
			deleteCustomer.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteAccount(String id) {
		try {
			deleteAccount.setString(1, id);
			deleteAccount.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void write() {

	}

	public String serialize() {
		return null;
	}

	public boolean uninitialized() {
		return false;
	}

}
