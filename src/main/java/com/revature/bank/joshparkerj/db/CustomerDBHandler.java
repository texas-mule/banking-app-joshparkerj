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
			rs.next();
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
			rs.next();
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
			rs.next();
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
			s.append("ssn\tusername\tpassword\n");
			while(rs.next()) {
				s.append(rs.getString("ssn"));
				s.append("\t");
				s.append(rs.getString("username"));
				s.append("\t");
				s.append(rs.getString("password"));
				s.append("\n");
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
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String customerDetails(String id) {
		s = new StringBuilder();
		try {
			customerDetails.setString(1,id);
			ResultSet rs = customerDetails.executeQuery();
			s.append("ssn\tusername\tpassword\n");
			while(rs.next()) {
				s.append(rs.getString("ssn"));
				s.append("\t");
				s.append(rs.getString("username"));
				s.append("\t");
				s.append(rs.getString("password"));
				s.append("\n");
			}
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
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
