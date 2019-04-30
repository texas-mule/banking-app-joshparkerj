package com.revature.bank.joshparkerj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDBHandler implements IDB.employees {

	private Connection con;

	private PreparedStatement getEmployeeID;
	private final String getEmployeeIDString = "SELECT id FROM employee WHERE username = ? AND password = ?;";
	private PreparedStatement addEmployee;
	private final String addEmployeeString = "SELECT add_employee(?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private PreparedStatement employeeExists;
	private final String employeeExistsString = "SELECT ? IN (SELECT id FROM employee);";
	private PreparedStatement deleteEmployee;
	private final String deleteEmployeeString = "DELETE FROM employee WHERE id = ?;";
	private PreparedStatement isAdmin;
	private final String isAdminString = "SELECT admin FROM employee WHERE id = ?;";
	private PreparedStatement isEmpty;
	private final String isEmptyString = "SELECT COUNT(*) = 0 FROM employee;";

	EmployeeDBHandler(Connection c) throws SQLException {
		con = c;
		getEmployeeID = con.prepareStatement(getEmployeeIDString);
		addEmployee = con.prepareStatement(addEmployeeString);
		employeeExists = con.prepareStatement(employeeExistsString);
		deleteEmployee = con.prepareStatement(deleteEmployeeString);
		isAdmin = con.prepareStatement(isAdminString);
		isEmpty = con.prepareStatement(isEmptyString);
	}

	public String getEmployeeID(String name, String pass) {
		try {
			getEmployeeID.setString(1, name);
			getEmployeeID.setString(2, pass);
			ResultSet rs = getEmployeeID.executeQuery();
			rs.next();
			return rs.getString("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addEmployee(String name, String pass, String id, String supid, String pay, String first, String last,
			String loc) {
		try {
			addEmployee.setString(1, name);
			addEmployee.setString(2, pass);
			addEmployee.setString(3, id);
			addEmployee.setString(4, supid);
			addEmployee.setString(5, pay);
			addEmployee.setString(6, first);
			addEmployee.setString(7, last);
			addEmployee.setString(8, loc);
			addEmployee.setBoolean(9, false);
			addEmployee.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean employeeExists(String id) {
		try {
			employeeExists.setString(1, id);
			ResultSet rs = employeeExists.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deleteEmployee(String id) {
		try {
			deleteEmployee.setString(1, id);
			deleteEmployee.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isAdmin(String id) {
		try {
			isAdmin.setString(1, id);
			ResultSet rs = isAdmin.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isEmpty() {
		try {
			ResultSet rs = isEmpty.executeQuery();
			rs.next();
			rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
