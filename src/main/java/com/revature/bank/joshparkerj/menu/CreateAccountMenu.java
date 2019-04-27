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
		mt.queueMenu("Customer");
		mt.ps.println("Please enter the account type:");
		type = s.nextLine();
		mt.ps.println("Please enter the account number:");
		num = s.nextLine();
		if (mt.getDB().account().uniqueAccountNumber(num)) {
			mt.getDB().account().addAccount(type, num);
			mt.getDB().accountholder().addAccountHolder(UserSession.getID(), num);
			mt.ps.println("Account added");
		} else {
			mt.ps.println("That account number is taken!");
		}
	}

}
