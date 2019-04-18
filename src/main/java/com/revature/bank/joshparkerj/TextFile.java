package com.revature.bank.joshparkerj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class TextFile {

	private List<Account> accounts;
	private List<Customer> customers;
	private List<Employee> employees;
	private String filename;
	private Path filepath;
	private File file;

	public TextFile(String f) {
		accounts = new LinkedList<Account>();
		customers = new LinkedList<Customer>();
		employees = new LinkedList<Employee>();
		filename = "./" + f;
		filepath = Paths.get(filename);
		file = new File(filename);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String st;
			while((st = br.readLine()) != null) {
				if (st.startsWith("account")) {
					accounts.add(new Account(st));
				} else if (st.startsWith("customer")) {
					customers.add(new Customer(st));
				} else if (st.startsWith("employee")) {
					employees.add(new Employee(st));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
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

	public void writeToDisc(IDB db) {
		try (BufferedWriter w = Files.newBufferedWriter(filepath, Charset.forName("UTF-8"))){
			String s = db.serialize();
			w.write(s, 0, s.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
