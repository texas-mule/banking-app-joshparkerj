package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class DepositMenu extends TransactionMenu {

	public DepositMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Enter the account number:");
		num = s.nextLine();
		if (mt.getDB().holdsAccount(num, UserSession.getID())) {
			System.out.println("Enter the amount in dollars and cents:");
			verifyAmount();
		} else {
			System.out.println("Not your account!");
			mt.menu("Customer");
		}
	}
	
	void transact() {
		bal = mt.getDB().deposit(num, sum);
		System.out.println("The account balance is now: " + bal);
	}

}
