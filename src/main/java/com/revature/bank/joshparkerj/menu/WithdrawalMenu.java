package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

public class WithdrawalMenu extends TransactionMenu {

	public WithdrawalMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Enter the account number:");
		num = s.nextLine();
		if (mt.getDB().holdsAccount(UserSession.getID(), num)) {
			System.out.println("How much do you wish to withdraw, in dollars and cents?");
			verifyAmount();
		} else {
			System.out.println("Not your account!");
			mt.queueMenu("Customer");
		}
	}

	void transact() {
		bal = mt.getDB().withdraw(num, sum);
		if (bal != null) {
			System.out.println("The account balance is now: " + bal);
		} else {
			System.out.println("You are not allowed to withdraw that much!");
		}
	}

}
