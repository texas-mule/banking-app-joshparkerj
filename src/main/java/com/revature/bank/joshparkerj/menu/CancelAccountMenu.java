package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

import com.revature.bank.joshparkerj.UserSession;

public class CancelAccountMenu extends ABCMenu {

	private String num;
	
	public CancelAccountMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		if (!mt.getDB().isAdmin(UserSession.getID())) {
			mt.ps.println("Admins only!");
			mt.queueMenu("Splash");
			UserSession.end();
			return;
		}
		mt.queueMenu("Admin");
		mt.ps.println("Please enter the account number: ");
		num = s.nextLine();
		mt.getDB().denyAccount(num);
		mt.ps.println("Account cancelled!");
	}

}
