package com.revature.bank.joshparkerj.menu;

import java.util.Scanner;
import com.revature.bank.joshparkerj.UserSession;
import java.util.regex.Pattern;

class CreateCustomerMenu extends ABCMenu {

	private String name;
	private String pass;
	private String id;

	public CreateCustomerMenu(MenuTree m, Scanner ss) {
		super(m, ss);
	}

	public void Run() {
		System.out.println("Your username must be unique.");
		System.out.println("Special characters aren't allowed.");
		System.out.println("Max 30 characters");
		System.out.println("This field must not be blank.");
		System.out.println("Please enter your username:");
		mt.queueMenu("Splash");
		name = s.nextLine();
		if (!mt.getDB().uniqueCustomerName(name)) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable username: " + name);
			System.out.println("That username is already in use!");
			System.out.println("***** *****     ***** *****");
			return;
		}
		if (Pattern.compile("[^\\w\\s]").matcher(name).find()) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable username: " + name);
			System.out.println("Special characters not allowed");
			System.out.println("***** *****     ***** *****");
			return;
		}
		if (name.length() < 1) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable username: " + name);
			System.out.println("You can\'t leave this field blank!");
			System.out.println("***** *****     ***** *****");
			return;
		}
		if (name.length() > 30) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable username: " + name);
			System.out.println("Please choose a shorter username!");
			System.out.println("***** *****     ***** *****");
			return;
		}
		System.out.println("your password must include the following:");
		System.out.println("at least one lower case letter");
		System.out.println("at least one upper case letter");
		System.out.println("at least one numeral");
		System.out.println("at least one of the following: + - . * / , ; \' [ ] ! @ # $ % ^ & ( )");
		System.out.println("Please enter your password:");
		pass = s.nextLine();
		if (!Pattern.compile("[a-z]").matcher(pass).find()) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable password: " + pass);
			System.out.println("you have to include at least one lower case letter");
			System.out.println("***** *****     ***** *****");
			return;
		}
		if (!Pattern.compile("[A-Z]").matcher(pass).find()) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable password: " + pass);
			System.out.println("You have to include at least one upper case letter");
			System.out.println("***** *****     ***** *****");
			return;
		}
		System.out.println("Please enter your ssn:");
		id = s.nextLine();
		System.out.println("ID length: " + id.length()); //testing
		if (!mt.getDB().uniqueSSN(id)) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable ssn: " + id);
			System.out.println("That ssn is already in our system!");
			System.out.println("***** *****     ***** *****");
			return;
		}
		if (id.length() == 0) {
			System.out.println("***** *****     ***** *****");
			System.out.println("Unacceptable ssn: " + id);
			System.out.println("You can\'t leave this field blank!");
			System.out.println("***** *****     ***** *****");
			return;
		}
		mt.getDB().addCustomer(name, pass, id);
		System.out.println("Customer added.");
		UserSession.init(id);
		mt.queueMenu("Customer");
	}

}
