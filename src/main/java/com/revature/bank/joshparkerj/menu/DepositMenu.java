package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class DepositMenu extends TransactionMenu {

	public DepositMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.ps.println("Enter the account number:");
		num = s.nextLine();
		if (mt.getDB().holdsAccount(UserSession.getID(), num)) {
			mt.ps.println("Enter the amount in dollars and cents:");
			verifyAmount();
		} else {
			mt.ps.println("Not your account!");
			mt.queueMenu("Customer");
		}
	}

	void transact() {
		bal = mt.getDB().deposit(num, sum);
		mt.ps.println("The account balance is now: " + bal);
	}

}
