package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class CustomerMenu extends ABCMenu {

	public CustomerMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Please choose from the following options:");
		System.out.println("1. Create Account");
		System.out.println("2. See your accounts");
		System.out.println("3. Make a deposit");
		System.out.println("4. Make a withdrawal");
		System.out.println("5. Make a transfer");
		System.out.println("9. Log out");
		System.out.println("0. Quit.");
		switch (Integer.parseInt(s.nextLine().substring(0,1))) {
		case 1:
			mt.menu("CreateAccount");
			break;
		case 2:
			System.out.print(mt.getDB().getCustomerAccounts(UserSession.getID()));
			mt.menu("Customer");
			break;
		case 3:
			mt.menu("Deposit");
			break;
		case 4:
			mt.menu("Withdrawal");
			break;
		case 5:
			mt.menu("Transfer");
			break;
		case 9:
			UserSession.end();
			mt.menu("Splash");
			break;
		case 0:
			break;
		default:
			System.out.println("Your input was not understood");
			mt.menu("Customer");
		}
	}

}
