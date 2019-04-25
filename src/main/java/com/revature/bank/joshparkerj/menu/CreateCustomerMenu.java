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
		mt.ps.println("Your username must be unique.");
		mt.ps.println("Special characters aren't allowed.");
		mt.ps.println("Max 30 characters");
		mt.ps.println("This field must not be blank.");
		mt.ps.println("Please enter your username:");
		mt.queueMenu("Splash");
		name = s.nextLine();
		if (!mt.getDB().uniqueCustomerName(name)) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable username: " + name);
			mt.ps.println("That username is already in use!");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		if (Pattern.compile("[^\\w\\s]").matcher(name).find()) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable username: " + name);
			mt.ps.println("Special characters not allowed");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		if (name.length() < 1) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable username: " + name);
			mt.ps.println("You can\'t leave this field blank!");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		if (name.length() > 30) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable username: " + name);
			mt.ps.println("Please choose a shorter username!");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		mt.ps.println("your password must include the following:");
		mt.ps.println("at least one lower case letter");
		mt.ps.println("at least one upper case letter");
		mt.ps.println("at least one numeral");
		mt.ps.println("at least one of the following: + - . * / , ; \' [ ] ! @ # $ % ^ & ( )");
		mt.ps.println("Please enter your password:");
		pass = s.nextLine();
		if (!Pattern.compile("[a-z]").matcher(pass).find()) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable password: " + pass);
			mt.ps.println("you have to include at least one lower case letter");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		if (!Pattern.compile("[A-Z]").matcher(pass).find()) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable password: " + pass);
			mt.ps.println("You have to include at least one upper case letter");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		mt.ps.println("Please enter your ssn:");
		id = s.nextLine();
		if (!mt.getDB().uniqueSSN(id)) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable ssn: " + id);
			mt.ps.println("That ssn is already in our system!");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		if (id.length() == 0) {
			mt.ps.println("***** *****     ***** *****");
			mt.ps.println("Unacceptable ssn: " + id);
			mt.ps.println("You can\'t leave this field blank!");
			mt.ps.println("***** *****     ***** *****");
			return;
		}
		mt.getDB().addCustomer(name, pass, id);
		mt.ps.println("Customer added.");
		UserSession.init(id);
		mt.queueMenu("Customer");
	}

}
