package com.revature.bank.joshparkerj.db;

import java.util.List;

public class EmployeeDBHandler implements IDB.employees {

	private StringBuilder s;
	private List<Employee> employees;

	EmployeeDBHandler(List<Employee> e) {
		employees = e;
	}

	public String serialize() {
		s = new StringBuilder();
		employees.forEach(employee -> this.s.append(employee.serialize()));
		return s.toString();
	}

	public String getEmployeeID(String name, String pass) {
		for (Employee e : employees) {
			String id = e.auth(name, pass);
			if (id != null)
				return id;
		}
		return null;
	}

	public void addEmployee(String name, String pass, String id, String supid, String pay, String first, String last,
			String loc) {
		employees.add(new Employee(name, pass, id, supid, pay, first, last, loc, "f"));
	}

	public boolean employeeExists(String id) {
		for (Employee e : employees) {
			if (e.getID().equals(id))
				return true;
		}
		return false;
	}

	public void deleteEmployee(String id) {
		employees.removeIf(e -> e.getID().equals(id));
	}

	public boolean isAdmin(String id) {
		for (Employee e : employees) {
			if (e.getID().equals(id))
				return e.isAdmin();
		}
		return false;
	}

	public boolean isEmpty() {
		return employees.isEmpty();
	}

}
