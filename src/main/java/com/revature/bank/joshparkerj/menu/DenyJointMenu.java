package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class DenyJointMenu extends ABCMenu {

	private String num;
	private String id;
	
	public DenyJointMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Customer2");
		System.out.println("Please enter the account number: ");
		num = s.nextLine();
		if (!mt.getDB().holdsAccount(UserSession.getID(), num)) {
			System.out.println("Not your account!");
			return;
		}
		System.out.println("Please enter their ssn: ");
		id = s.nextLine();
		if (!mt.getDB().jointAppExists(id, num)) {
			System.out.println("They haven\'t applied for this account!");
			return;
		}
		if (mt.getDB().holdsAccount(id, num)) {
			System.out.println("They are approved already! You can\'t deny this application!");
			return;
		}
		mt.getDB().denyJointApp(id, num);
		System.out.println("Application denied!");
	}

}
