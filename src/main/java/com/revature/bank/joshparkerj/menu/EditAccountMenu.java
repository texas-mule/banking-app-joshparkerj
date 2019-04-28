package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class EditAccountMenu extends TransactionMenu {
	
	private String newValue;
	private String fieldToEdit;
	
	public EditAccountMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.ps.println("Please choose from the following options:");
		mt.ps.println("1. View all accounts");
		mt.ps.println("2. Change Account Type");
		mt.ps.println("3. Change Account Number");
		mt.ps.println("4. Change Account Balance");
		mt.ps.println("5. Change Account Approval Status");
		mt.ps.println("8. Return to admin options...");
		mt.ps.println("9. Log out");
		mt.ps.println("0. Quit.");
		try {
			switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
			case 1:
				viewAccounts();
				return;
			case 2:
				fieldToEdit = "type";
				mt.ps.println("Please enter the new account type: ");
				newValue = s.nextLine();
				break;
			case 3:
				fieldToEdit = "number";
				mt.ps.println("Please enter the new account number: ");
				newValue = s.nextLine();
				break;
			case 4:
				mt.ps.println("What\'s the account number?");
				num = s.nextLine();
				mt.ps.println("Please enter the new account balance in dollars and cents: ");
				verifyAmount();
				return;
			case 5:
				fieldToEdit = "approval status";
				mt.ps.println("Please enter t or f for the new approval status: ");
				newValue = s.nextLine(); 
				break;
			case 8:
				mt.queueMenu("Admin");
				return;
			case 9:
				UserSession.end();
				mt.queueMenu("Splash");
				return;
			case 0:
				UserSession.end();
				mt.quit();
				return;
			default:
				mt.ps.println("Your input was not understood");
				return;
			}
			mt.ps.println("What\'s the account number?");
			num = s.nextLine();
			if (mt.getDB().account().editAccount(num, newValue, fieldToEdit)) {
				mt.ps.println("account edited!");
			} else mt.ps.println("The account number was incorrect");
		} catch (NumberFormatException e) {
			mt.ps.println("You have to enter a number!");
		}
	}

	private void viewAccounts() {
		mt.ps.print(mt.getDB().account().getDetailedAccounts());
	}

	void transact() {
		LOGGER.info("\nTransaction type: EDIT BALANCE");
		bal = mt.getDB().account().overwriteBalance(num, sum);
		if (bal.length() > 0) mt.ps.println("The account balance is now: " + bal);
		else mt.ps.println("The account number was incorrect");
	}

}
