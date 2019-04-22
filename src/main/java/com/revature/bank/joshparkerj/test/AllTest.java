package com.revature.bank.joshparkerj.test;

import org.junit.Test;

public class AllTest {
	
	@Test
	public void testEverything() {
		BankDBTest bdt = new BankDBTest();
		bdt.testGetDB();
		bdt.testWrite();
		bdt.testGetEmployeeID();
		bdt.testCustomerTransactions();
		MenuTreeTest mtt = new MenuTreeTest();
		mtt.testMenu();
	}

}
