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
		System.out.println("Please enter the user ssn:");
		ssn = s.nextLine();
		System.out.println("Please enter the account number:");
		num = s.nextLine();
		if (!mt.getDB().holdsAccount(UserSession.getID(), num)) {
			System.out.println("Not your account!");
			return;
		}
		if (!mt.getDB().jointAppExists(ssn,num)) {
			System.out.println("That information doesn\'t match any application!");
			return;
		}
		mt.getDB().approveAccountHolder(ssn, num);
		System.out.println("The joint application has been approved!");
	}

}
