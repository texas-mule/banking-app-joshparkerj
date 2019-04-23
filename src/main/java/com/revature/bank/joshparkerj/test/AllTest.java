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
		bdt.testMissingRecords();
		bdt.testUniqueAccountNumber();
		MenuTreeTest mtt = new MenuTreeTest();
		mtt.testMenu();
		mtt.testBadCustomer();
		mtt.testEmployeeMenu();
		mtt.testBadWithdrawal();
		BadFileTest bft = new BadFileTest();
		bft.testBadFileName();
		AppTest at = new AppTest();
		at.testMain();
	}

}
