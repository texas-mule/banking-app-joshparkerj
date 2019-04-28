package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

public class WithdrawalMenu extends TransactionMenu {

	public WithdrawalMenu(MenuTree m, Scanner ss) {
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
		mt.ps.println("How much do you wish to withdraw, in dollars and cents?");
		verifyAmount();
	}

	void transact() {
		LOGGER.info("Transaction type: WITHDRAWAL");
		bal = mt.getDB().account().withdraw(num, sum);
		if (bal != null) {
			mt.ps.println("The account balance is now: " + bal);
		} else {
			mt.ps.println("You are not allowed to withdraw that much!");
		}
	}

}
