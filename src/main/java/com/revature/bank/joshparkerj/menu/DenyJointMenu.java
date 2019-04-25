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
		mt.ps.println("Please enter the account number: ");
		num = s.nextLine();
		if (!mt.getDB().holdsAccount(UserSession.getID(), num)) {
			mt.ps.println("Not your account!");
			return;
		}
		mt.ps.println("Please enter their ssn: ");
		id = s.nextLine();
		if (!mt.getDB().jointAppExists(id, num)) {
			mt.ps.println("They haven\'t applied for this account!");
			return;
		}
		if (mt.getDB().holdsAccount(id, num)) {
			mt.ps.println("They are approved already! You can\'t deny this application!");
			return;
		}
		mt.getDB().denyJointApp(id, num);
		mt.ps.println("Application denied!");
	}

}
