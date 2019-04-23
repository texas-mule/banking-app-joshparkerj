package com.revature.bank.joshparkerj.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

class TextFile {

	private List<Account> accounts;
	private List<Customer> customers;
	private List<Employee> employees;
	private List<AccountHolder> accountholders;
	private String filename;

	public TextFile(String f) {
		accounts = new LinkedList<Account>();
		customers = new LinkedList<Customer>();
		employees = new LinkedList<Employee>();
		accountholders = new LinkedList<AccountHolder>();
		filename = "./" + f;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
			String st;
			while ((st = br.readLine()) != null) {
				if (st.startsWith("account")) {
					accounts.add(new Account(st));
				} else if (st.startsWith("customer")) {
					customers.add(new Customer(st));
				} else if (st.startsWith("employee")) {
					employees.add(new Employee(st));
				} else if (st.startsWith("ah")) {
					accountholders.add(new AccountHolder(st));
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public List<AccountHolder> getAccountHolders() {
		return accountholders;
	}

	public void writeToDisc(IDB db) {
		try {
			try (BufferedWriter w = Files.newBufferedWriter(Paths.get(filename), Charset.forName("UTF-8"))) {
				String s = db.serialize();
				w.write(s, 0, s.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
