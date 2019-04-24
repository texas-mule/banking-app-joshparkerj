package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class Customer2Menu extends ABCMenu {

	public Customer2Menu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Please choose from the following options:");
		System.out.println("1. Apply for joint account");
		System.out.println("2. See joint account applications");
		System.out.println("3. Approve joint account application");
		System.out.println("4. Deny joint account application");
		System.out.println("8. Previous options...");
		System.out.println("9. Log out");
		System.out.println("0. Quit.");
		switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
		case 1:
			mt.queueMenu("ApplyJoint");
			break;
		case 2:
			System.out.print(mt.getDB().getJointApps(UserSession.getID()));
			break;
		case 3:
			mt.queueMenu("ApproveJoint");
			break;
		case 4:
			mt.queueMenu("DenyJoint");
			break;
		case 8:
			mt.queueMenu("Customer");
		case 9:
			UserSession.end();
			mt.queueMenu("Splash");
			break;
		case 0:
			mt.quit();
			break;
		default:
			System.out.println("Your input was not understood");
		}
	}

}
