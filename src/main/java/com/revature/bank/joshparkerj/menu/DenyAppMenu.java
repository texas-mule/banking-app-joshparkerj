package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class DenyAppMenu extends ABCMenu {

	private String num;

	public DenyAppMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Employee");
		mt.ps.println("Please enter the account number:");
		num = s.nextLine();
		if (!mt.getDB().accountExists(num)) {
			mt.ps.println("That account isn\'t in the system!");
			return;
		}
		if (mt.getDB().accountApproved(num)) {
			mt.ps.println("You can\'t deny an application that was already approved!");
			return;
		}
		mt.getDB().denyAccount(num);
		mt.ps.println("Application denied!");
	}

}
