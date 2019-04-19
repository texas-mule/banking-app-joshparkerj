package com.revature.bank.joshparkerj;

import java.util.Scanner;

public class DepositMenu extends ABCMenu {

	private String num;
	private String sum;
	private String bal;

	public DepositMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Enter the account number:");
		num = s.nextLine();
		if (mt.getDB().holdsAccount(num, UserSession.getID())) {
			System.out.println("Enter the amount in dollars and cents:");
			sum = s.nextLine();
			int amount = Integer.parseInt(sum.replaceAll("\\D", ""));
			int remainder = amount % 100;
			String r = remainder < 10 ? ("0" + remainder) : ("" + remainder);
			sum = "$" + amount / 100 + "." + r;
			System.out.println("Do you wish to deposit " + sum + "? Y//n");
			String choice = s.nextLine();
			if (choice.toLowerCase().startsWith("y")) {
				bal = mt.getDB().deposit(num, sum);
				System.out.println("The account balance is now: " + bal);
			} else System.out.println("No deposit made");
			mt.menu("Customer");
		} else {
			System.out.println("Not your account!");
			mt.menu("Customer");
		}
	}

}
