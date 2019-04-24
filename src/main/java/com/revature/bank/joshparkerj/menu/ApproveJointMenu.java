package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

public class ApproveJointMenu extends ABCMenu {

	private String ssn;
	private String num;
	
	public ApproveJointMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Please enter the user ssn:");
		ssn = s.nextLine();
		System.out.println("Please enter the account number:");
		num = s.nextLine();
	}

}
