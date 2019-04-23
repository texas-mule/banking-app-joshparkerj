package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

public class TransferMenu extends TransactionMenu {

	private String dnum;

	public TransferMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Customer");
		System.out.println("Enter the source account number:");
		num = s.nextLine();
		if (!mt.getDB().holdsAccount(num, UserSession.getID())) {
			System.out.println("Not your account!");
			return;
		}
		System.out.println("Enter the destination account number:");
		dnum = s.nextLine();
		if (!mt.getDB().accountExists(dnum)) {
			System.out.println("That account is not in the system!");
			return;
		}
		System.out.println("How much do you wish to transfer, in dollars and cents?");
		verifyAmount();

	}

	void transact() {
		bal = mt.getDB().transfer(num, dnum, sum);
		if (bal != null) {
			System.out.println("Your account balance is now: " + bal);
		} else {
			System.out.println("You are not allowed to transfer that much!");
		}
	}

}
