package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class CustomerMenu extends ABCMenu {

	public CustomerMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.ps.println("Please choose from the following options:");
		mt.ps.println("1. Create Account");
		mt.ps.println("2. See your accounts");
		mt.ps.println("3. Make a deposit");
		mt.ps.println("4. Make a withdrawal");
		mt.ps.println("5. Make a transfer");
		mt.ps.println("8. More options...");
		mt.ps.println("9. Log out");
		mt.ps.println("0. Quit.");
		switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
		case 1:
			mt.queueMenu("CreateAccount");
			break;
		case 2:
			mt.ps.print(mt.getDB().getCustomerAccounts(UserSession.getID()));
			break;
		case 3:
			mt.queueMenu("Deposit");
			break;
		case 4:
			mt.queueMenu("Withdrawal");
			break;
		case 5:
			mt.queueMenu("Transfer");
			break;
		case 8:
			mt.queueMenu("Customer2");
			break;
		case 9:
			UserSession.end();
			mt.queueMenu("Splash");
			break;
		case 0:
			mt.quit();
			break;
		default:
			mt.ps.println("Your input was not understood");
		}
	}

}
