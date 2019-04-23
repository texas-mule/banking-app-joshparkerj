package com.revature.bank.joshparkerj.test;

import org.junit.Test;

import com.revature.bank.joshparkerj.App;
import com.revature.bank.joshparkerj.db.BankDB;

public class AppTest {
	
	private final String[] args1 = {"DefaultData.txt", "TEST"};
	private final String[] args2 = {"~*~*~Bad#:#:#File%:%:%Name&*&*&"};
	private final String[] args3 = {"SomeKindaData.txt", "IDK WHAT IS GOING ON"};
	private final String[] args4 = {};
	
	@Test
	public void testMain(){
		App a = new App();
		App.main(args1);
		BankDB.getDB("").close();
		App.main(args2);
		BankDB.getDB("").close();
		App.main(args3);
		System.out.println(a.toString());
		App.main(args4);
	}

}
