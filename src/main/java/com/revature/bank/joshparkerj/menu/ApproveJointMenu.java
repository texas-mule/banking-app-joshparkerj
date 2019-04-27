package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class ApproveJointMenu extends ABCMenu {

	private String ssn;
	private String num;
	
	public ApproveJointMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.queueMenu("Customer2");
		mt.ps.println("Please enter the user ssn:");
		ssn = s.nextLine();
		mt.ps.println("Please enter the account number:");
		num = s.nextLine();
		if (!mt.getDB().accountholder().holdsAccount(UserSession.getID(), num)) {
			mt.ps.println("Not your account!");
			return;
		}
		if (!mt.getDB().accountholder().jointAppExists(ssn,num)) {
			mt.ps.println("That information doesn\'t match any application!");
			return;
		}
		mt.getDB().accountholder().approveAccountHolder(ssn, num);
		mt.ps.println("The joint application has been approved!");
	}

}
