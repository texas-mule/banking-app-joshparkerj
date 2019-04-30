package com.revature.bank.joshparkerj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDBHandler implements IDB.customers {

	private StringBuilder s;
	private Connection con;

	private PreparedStatement getCustomerID;
	private final String getCustomerIDString = "SELECT ssn FROM customer WHERE username = ? AND password = ?;";
	private PreparedStatement addCustomer;
	private final String addCustomerString = "SELECT add_customer(?, ?, ?);";
	private PreparedStatement uniqueCustomerName;
	private final String uniqueCustomerNameString = "SELECT ? NOT IN (SELECT username FROM customer);";
	private PreparedStatement uniqueSSN;
	private final String uniqueSSNString = "SELECT ? NOT IN (SELECT ssn FROM customer);";
	private PreparedStatement getCustomers;
	private final String getCustomersString = "SELECT * FROM customer;";
	private PreparedStatement customerExists;
	private final String customerExistsString = "SELECT ? IN (SELECT ssn FROM customer);";
	private PreparedStatement customerDetails;
	private final String customerDetailsString = "SELECT * FROM customer WHERE ssn = ?;";
	private PreparedStatement removeBySSN;
	private final String removeBySSNString = "DELETE FROM customer WHERE ssn = ?;";
	private PreparedStatement isEmpty;
	private final String isEmptyString = "SELECT COUNT(*) = 0 FROM customer;";

	CustomerDBHandler(Connection c) throws SQLException {
		con = c;
		getCustomerID = con.prepareStatement(getCustomerIDString);
		addCustomer = con.prepareStatement(addCustomerString);
		uniqueCustomerName = con.prepareStatement(uniqueCustomerNameString);
		uniqueSSN = con.prepareStatement(uniqueSSNString);
		getCustomers = con.prepareStatement(getCustomersString);
		customerExists = con.prepareStatement(customerExistsString);
		customerDetails = con.prepareStatement(customerDetailsString);
		removeBySSN = con.prepareStatement(removeBySSNString);
		isEmpty = con.prepareStatement(isEmptyString);
	}

	public String getCustomerID(String name, String pass) {
		try {
			getCustomerID.setString(1, name);
			getCustomerID.setString(2, pass);
			ResultSet rs = getCustomerID.executeQuery();
			if (rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addCustomer(String name, String pass, String id) {
		try {
			addCustomer.setString(1, name);
			addCustomer.setString(2, pass);
			addCustomer.setString(3, id);
			addCustomer.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean uniqueCustomerName(String name) {
		try {
			uniqueCustomerName.setString(1, name);
			ResultSet rs = uniqueCustomerName.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean uniqueSSN(String id) {
		try {
			uniqueSSN.setString(1, id);
			ResultSet rs = uniqueSSN.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getCustomers() {
		s = new StringBuilder();
		try {
			ResultSet rs = getCustomers.executeQuery();
			while (rs.next()) {
				s.append(String.format("%9s%22s%5s%22s\n", "Username:", rs.getString("username"), "SSN:", rs.getString("ssn")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public boolean customerExists(String id) {
		try {
			customerExists.setString(1, id);
			ResultSet rs = customerExists.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String customerDetails(String id) {
		s = new StringBuilder();
		try {
			customerDetails.setString(1, id);
			ResultSet rs = customerDetails.executeQuery();
			if (rs.next()) {
				do {
					s.append(String.format("%10s%22s%5s%22s%11s%22s\n", "Username:", rs.getString("username"), "SSN:", rs.getString("ssn"), "Password:", rs.getString("password")));
				} while (rs.next());
			} else
				return null;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public void removeBySSN(String id) {
		try {
			removeBySSN.setString(1, id);
			removeBySSN.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		try {
			ResultSet rs = isEmpty.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
