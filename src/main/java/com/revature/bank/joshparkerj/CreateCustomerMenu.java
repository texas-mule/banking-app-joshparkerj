package com.revature.bank.joshparkerj;

import java.util.Scanner;

public class CreateCustomerMenu extends ABCMenu {

	private String name;
	private String pass;
	private String id;

	public CreateCustomerMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Please enter your username:");
		name = s.nextLine();
		if (mt.getDB().uniqueCustomerName(name)) {
			System.out.println("Please enter your password:");
			pass = s.nextLine();
			System.out.println("Please enter your ssn:");
			id = s.nextLine();
			if (mt.getDB().uniqueSSN(id)) {
			mt.getDB().addCustomer(new Customer(name, pass, id));
			System.out.println("Customer added.");
			UserSession.init(id);
			mt.menu("Customer");
			}else {
				System.out.println("That ssn is already in our system!");
				mt.menu("Splash");
			}
		} else {
			System.out.println("That username is already in use!");
			mt.menu("Splash");
		}
	}

}
