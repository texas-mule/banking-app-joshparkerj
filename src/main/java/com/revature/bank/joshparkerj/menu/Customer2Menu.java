package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class Customer2Menu extends ABCMenu {

	public Customer2Menu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		mt.ps.println("Please choose from the following options:");
		mt.ps.println("1. Apply for joint account");
		mt.ps.println("2. See joint account applications");
		mt.ps.println("3. Approve joint account application");
		mt.ps.println("4. Deny joint account application");
		mt.ps.println("8. Previous options...");
		mt.ps.println("9. Log out");
		mt.ps.println("0. Quit.");
		switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
		case 1:
			mt.queueMenu("ApplyJoint");
			break;
		case 2:
			mt.ps.print(mt.getDB().getJointApps(UserSession.getID()));
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
			mt.ps.println("Your input was not understood");
		}
	}

}
