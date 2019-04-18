package com.revature.bank.joshparkerj;

import java.util.Scanner;

public class CustomerMenu extends ABCMenu {

	public CustomerMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Please choose from the following options:");
		System.out.println("1. Create Account");
		switch (Integer.parseInt(s.nextLine().substring(0,1))) {
		case 1:
			mt.menu("CreateAccount");
			break;
		default:
			System.out.println("Your input was not understood");
			mt.menu("Customer");
		}
	}

}
