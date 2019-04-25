package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

class CreateUserMenu extends ABCMenu {

	public CreateUserMenu(MenuTree mt, Scanner s) {
		super(mt, s);
	}

	public void Run() {
		mt.ps.println("Select a user type: ");
		mt.ps.println("1. Customer");
		mt.ps.println("2. Employee");
		try {
			switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
			case 1:
				mt.queueMenu("CreateCustomer");
				break;
			case 2:
				mt.queueMenu("CreateEmployee");
				break;
			default:
				mt.ps.println("Your input was not understood");
			}
		} catch (StringIndexOutOfBoundsException | NumberFormatException e) {
			mt.ps.println("You have to enter a number!");
		}
	}

}
