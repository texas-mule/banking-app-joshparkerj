package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;

class CreateAccountMenu extends ABCMenu {

	private String type;
	private String num;

	public CreateAccountMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Please enter the account type:");
		type = s.nextLine();
		System.out.println("Please enter the account number:");
		num = s.nextLine();
		if (mt.getDB().uniqueAccountNumber(num)) {
			mt.getDB().addAccount(type, num);
			mt.getDB().addAccountHolder(UserSession.getID(), num);
			System.out.println("Account added");
			mt.queueMenu("Customer");
		} else {
			System.out.println("That account number is taken!");
			mt.queueMenu("CreateAccount");
		}
	}

}
