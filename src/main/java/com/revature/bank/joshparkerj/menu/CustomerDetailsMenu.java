package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class CustomerDetailsMenu extends ABCMenu {

	private String id;

	public CustomerDetailsMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Employee");
		System.out.println("Please enter the customer id number:");
		id = s.nextLine();
		if (!mt.getDB().customerExists(id)) {
			System.out.println("That customer is not in the system!");
			return;
		}
		System.out.println("**** **** **** **** ****");
		System.out.println("Customer Details:");
		System.out.println(mt.getDB().customerDetails(id));
		System.out.println("**** **** **** **** ****");
		System.out.println("");
		System.out.println("**** **** **** **** ****");
		System.out.println("Customer Accounts:");
		System.out.println(mt.getDB().getCustomerAccounts(id));
		System.out.println("**** **** **** **** ****");
	}

}
