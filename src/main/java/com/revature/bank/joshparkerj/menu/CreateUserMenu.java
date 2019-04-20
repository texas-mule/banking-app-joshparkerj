package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;

class CreateUserMenu extends ABCMenu {

	public CreateUserMenu(MenuTree mt, Scanner s) {
		super(mt, s);
	}

	public void Run() {
		System.out.println("Select a user type: ");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		try {
			switch (Integer.parseInt(s.nextLine().substring(0, 1))) {
			case 1:
				mt.queueMenu("CreateCustomer");
				break;
			case 2:
				mt.queueMenu("CreateEmployee");
				break;
			default:
				System.out.println("Your input was not understood");
				mt.queueMenu("CreateUser");
			}
		} catch (StringIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("You have to enter a number!");
			mt.queueMenu("CreateUser");
		}
	}

}
