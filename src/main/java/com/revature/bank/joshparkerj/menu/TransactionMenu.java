package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

abstract class TransactionMenu extends ABCMenu {

	protected String num;
	protected String sum;
	protected String bal;
	protected int amount;
	protected int remainder;
	protected String r;
	protected String choice;

	public TransactionMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	protected void verifyAmount() {
		mt.queueMenu("Customer");
		sum = s.nextLine();
		sum = sum.replaceAll("\\D", "");
		if (sum.length() < 1) {
			mt.ps.println("Please enter the amount using numerals!");
			return;
		}
		amount = Integer.parseInt(sum);
		remainder = amount % 100;
		r = remainder < 10 ? ("0" + remainder) : ("" + remainder);
		sum = "$" + amount / 100 + "." + r;
		mt.ps.println("I got: " + sum + ". Is this the correct amount? Y/n");
		choice = s.nextLine();
		if (choice.toLowerCase().startsWith("y")) {
			transact();
		} else
			mt.ps.println("No transaction made");
	}

	abstract void transact();

}
