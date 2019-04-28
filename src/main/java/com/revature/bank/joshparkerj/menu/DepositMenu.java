package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class DepositMenu extends TransactionMenu {

	public DepositMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Customer");
		mt.ps.println("Enter the account number:");
		num = s.nextLine();
		if (!mt.getDB().accountholder().holdsAccount(UserSession.getID(), num) && !mt.getDB().employee().isAdmin(UserSession.getID())) {
			mt.ps.println("Not your account!");
			return;
		}
		if (!mt.getDB().account().accountApproved(num)) {
			mt.ps.println("That account hasn\'t been approved yet!");
			return;
		}
		mt.ps.println("Enter the amount in dollars and cents:");
		verifyAmount();
	}

	void transact() {
		LOGGER.info("Transaction type: DEPOSIT\n");
		bal = mt.getDB().account().deposit(num, sum);
		mt.ps.println("The account balance is now: " + bal);
	}

}
