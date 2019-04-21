package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class ApproveAppMenu extends ABCMenu {

	private String num;

	public ApproveAppMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Employee");
		System.out.println("Please enter the account number:");
		num = s.nextLine();
		if (!mt.getDB().accountExists(num)) {
			System.out.println("That account is not in the system!");
			return;
		}
		if (mt.getDB().accountApproved(num)) {
			System.out.println("That account has already been approved!");
			return;
		}
		mt.getDB().approveAccount(num);
		System.out.println("Account approved!");
	}

}
