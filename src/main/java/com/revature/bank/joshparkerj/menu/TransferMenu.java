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
		mt.ps.println("Enter the source account number:");
		num = s.nextLine();
		if (!mt.getDB().accountholder().holdsAccount(UserSession.getID(), num) && !mt.getDB().employee().isAdmin(UserSession.getID())) {
			mt.ps.println("Not your account!");
			return;
		}
		if (!mt.getDB().account().accountApproved(num)) {
			mt.ps.println("That account hasn\'t been approved yet!");
			return;
		}
		mt.ps.println("Enter the destination account number:");
		dnum = s.nextLine();
		if (!mt.getDB().account().accountExists(dnum)) {
			mt.ps.println("That account is not in the system!");
			return;
		}
		if (!mt.getDB().account().accountApproved(dnum)) {
			mt.ps.println("That account hasn\'t been approved yet!");
			return;
		}
		mt.ps.println("How much do you wish to transfer, in dollars and cents?");
		verifyAmount();
	}

	void transact() {
		bal = mt.getDB().account().transfer(num, dnum, sum);
		if (bal != null) {
			mt.ps.println("Your account balance is now: " + bal);
		} else {
			mt.ps.println("You are not allowed to transfer that much!");
		}
	}

}
