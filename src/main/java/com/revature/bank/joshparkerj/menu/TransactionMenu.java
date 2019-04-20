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
		sum = s.nextLine();
		amount = Integer.parseInt(sum.replaceAll("\\D", ""));
		remainder = amount % 100;
		r = remainder < 10 ? ("0" + remainder) : ("" + remainder);
		sum = "$" + amount / 100 + "." + r;
		System.out.println("I got: " + sum + ". Is this the correct amount? Y//n");
		choice = s.nextLine();
		if (choice.toLowerCase().startsWith("y")) {
			transact();
		} else System.out.println("No transaction made");
		mt.menu("Customer");
	}
	
	abstract void transact();

}
