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
		mt.ps.println("Please enter the account number: ");
		num = s.nextLine();
		if (!mt.getDB().account().accountExists(num)) {
			mt.ps.println("That account is not in the system!");
			return;
		}
		if (mt.getDB().accountholder().jointAppExists(UserSession.getID(), num)) {
			mt.ps.println("You can\'t create duplicate applications for a joint account!");
			return;
		}
		mt.getDB().accountholder().addJointAccount(UserSession.getID(), num);
		mt.ps.println("Application saved!");
	}

}
