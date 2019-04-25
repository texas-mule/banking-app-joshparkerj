package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class CustomerDetailsMenu extends ABCMenu {

	private String id;

	public CustomerDetailsMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Employee");
		mt.ps.println("Please enter the customer id number:");
		id = s.nextLine();
		if (!mt.getDB().customerExists(id)) {
			mt.ps.println("That customer is not in the system!");
			return;
		}
		mt.ps.println("**** **** **** **** ****");
		mt.ps.println("Customer Details:");
		mt.ps.println(mt.getDB().customerDetails(id));
		mt.ps.println("**** **** **** **** ****");
		mt.ps.println("");
		mt.ps.println("**** **** **** **** ****");
		mt.ps.println("Customer Accounts:");
		mt.ps.println(mt.getDB().getCustomerAccounts(id));
		mt.ps.println("**** **** **** **** ****");
	}

}
