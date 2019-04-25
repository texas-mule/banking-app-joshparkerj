package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class ApplyJointMenu extends ABCMenu {

	private String num;
	
	public ApplyJointMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Customer2");
		System.out.println("Please enter the account number: ");
		num = s.nextLine();
		if (!mt.getDB().accountExists(num)) {
			System.out.println("That account is not in the system!");
			return;
		}
		if (mt.getDB().holdsAccount(UserSession.getID(), num)) {
			System.out.println("You can\'t create duplicate applications for a joint account!");
			return;
		}
		mt.getDB().addJointAccount(UserSession.getID(), num);
		System.out.println("Application saved!");
	}

}
