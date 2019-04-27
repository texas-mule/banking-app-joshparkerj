package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class AdminMenu extends ABCMenu {

	public AdminMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.ps.println("Please choose from the following options:");
		mt.ps.println("1. Deposit");
		mt.ps.println("2. Transfer");
		mt.ps.println("3. Withdraw");
		mt.ps.println("4. Cancel account");
		mt.ps.println("5. View all accounts");
		mt.ps.println("6. Edit an account");
		mt.ps.println("8. Return to employee options...");
		mt.ps.println("9. Log out");
		mt.ps.println("0. Quit.");
		try {
			switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
			case 1:
				mt.queueMenu("Deposit");
				break;
			case 2:
				mt.queueMenu("Transfer");
				break;
			case 3:
				mt.queueMenu("Withdrawal");
				break;
			case 4:
				mt.queueMenu("CancelAccount");
				break;
			case 5:
				viewAccounts();
				break;
			case 6:
				mt.queueMenu("EditAccount");
				break;
			case 8:
				mt.queueMenu("Employee");
				break;
			case 9:
				UserSession.end();
				mt.queueMenu("Splash");
				break;
			case 0:
				UserSession.end();
				mt.quit();
				break;
			default:
				mt.ps.println("Your input was not understood");
			}
		} catch (NumberFormatException e) {
			mt.ps.println("You have to enter a number!");
		}
	}

	private void viewAccounts() {
		mt.ps.print(mt.getDB().getDetailedAccounts());
	}
	
}
