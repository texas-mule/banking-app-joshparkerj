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
		if (!mt.getDB().accountholder().holdsAccount(UserSession.getID(), num)) {
			mt.ps.println("Not your account!");
			return;
		}
		mt.ps.println("Please enter their ssn: ");
		id = s.nextLine();
		if (!mt.getDB().accountholder().jointAppExists(id, num)) {
			mt.ps.println("They haven\'t applied for this account!");
			return;
		}
		if (mt.getDB().accountholder().holdsAccount(id, num)) {
			mt.ps.println("They are approved already! You can\'t deny this application!");
			return;
		}
		mt.getDB().accountholder().denyJointApp(id, num);
		mt.ps.println("Application denied!");
	}

}
