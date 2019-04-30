package com.revature.bank.joshparkerj.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountHolderDBHandler implements IDB.accountholders {

	private StringBuilder s;
	private Connection con;

	private PreparedStatement addAccountHolder;
	private final String addAccountHolderString = "SELECT add_account_holder(?, ?, ?);";
	private PreparedStatement approveAccountHolder;
	private final String approveAccountHolderString = "SELECT approve_account_holder(?, ?);";
	private PreparedStatement denyJointApp;
	private final String denyJointAppString = "SELECT deny_joint_app(?, ?);";
	private PreparedStatement jointAppExists;
	private final String jointAppExistsString = "SELECT joint_app_exists(?, ?);";
	private PreparedStatement getJointApps;
	private final String getJointAppsString = "SELECT get_joint_apps(?);";
	private PreparedStatement holdsAccount;
	private final String holdsAccountString = "SELECT holds_account(?, ?);";
	private PreparedStatement removeBySSN;
	private final String removeBySSNString = "DELETE FROM account_holder WHERE customer_ssn = ?;";
	private PreparedStatement removeByNum;
	private final String removeByNumString = "DELETE FROM account_holder WHERE account_number = ?;";
	private PreparedStatement isEmpty;
	private final String isEmptyString = "SELECT COUNT(*) = 0 FROM account_holder;";

	AccountHolderDBHandler(Connection c) throws SQLException {
		con = c;
		addAccountHolder = con.prepareStatement(addAccountHolderString);
		approveAccountHolder = con.prepareStatement(approveAccountHolderString);
		denyJointApp = con.prepareStatement(denyJointAppString);
		jointAppExists = con.prepareStatement(jointAppExistsString);
		getJointApps = con.prepareStatement(getJointAppsString);
		holdsAccount = con.prepareStatement(holdsAccountString);
		removeBySSN = con.prepareStatement(removeBySSNString);
		removeByNum = con.prepareStatement(removeByNumString);
		isEmpty = con.prepareStatement(isEmptyString);
	}

	public void addAccountHolder(String id, String num) {
		try {
			addAccountHolder.setString(1, id);
			addAccountHolder.setString(2, num);
			addAccountHolder.setBoolean(3, true);
			addAccountHolder.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addJointAccount(String id, String num) {
		try {
			addAccountHolder.setString(1, id);
			addAccountHolder.setString(2, num);
			addAccountHolder.setBoolean(3, false);
			addAccountHolder.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void approveAccountHolder(String id, String num) {
		try {
			approveAccountHolder.setString(1, id);
			approveAccountHolder.setString(2, num);
			approveAccountHolder.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void denyJointApp(String id, String num) {
		try {
			denyJointApp.setString(1, id);
			denyJointApp.setString(2, num);
			denyJointApp.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean jointAppExists(String id, String num) {
		try {
			jointAppExists.setString(1, id);
			jointAppExists.setString(2, num);
			ResultSet rs = jointAppExists.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getJointApps(String id) {
		s = new StringBuilder();
		try {
			getJointApps.setString(1, id);
			ResultSet rs = getJointApps.executeQuery();
			s.append("customer_ssn\taccount_number\n;");
			while (rs.next()) {
				s.append(rs.getString("customer_ssn"));
				s.append("\t");
				s.append(rs.getString("account_number"));
				s.append("\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public boolean holdsAccount(String id, String num) {
		try {
			holdsAccount.setString(1, id);
			holdsAccount.setString(2, num);
			ResultSet rs = holdsAccount.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void removeByNum(String num) {
		try {
			removeByNum.setString(1, num);
			removeByNum.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeBySSN(String id) {
		try {
			removeBySSN.setString(1, id);
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
