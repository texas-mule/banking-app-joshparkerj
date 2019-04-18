package com.revature.bank.joshparkerj;

import java.util.Scanner;

public class CreateAccountMenu extends ABCMenu {

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
			mt.getDB().addAccount(new Account(type, num, "$0.00"));
			mt.getDB().addAccountHolder(new AccountHolder(UserSession.getID(), num));
			System.out.println("Account added");
			mt.menu("Customer");
		} else {
			System.out.println("That account number is taken!");
			mt.menu("CreateAccount");
		}
	}

}
