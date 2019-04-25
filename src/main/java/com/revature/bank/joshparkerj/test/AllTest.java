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
		mtt.testSmallAmounts();
		mtt.testBadTransfer();
		mtt.testAccountNumberTaken();
		mtt.testNoSupervisor();
		mtt.testRefuseTransaction();
		mtt.testNonExistentCustomer();
		mtt.testBadMenuName();
		mtt.testBadLogin();
		mtt.testBadSplash();
		mtt.testBadApproval();
		mtt.testBadUserCreation();
		mtt.testBadDenial();
		mtt.testJointAccount();
		BadFileTest bft = new BadFileTest();
		bft.testBadFileName();
		AppTest at = new AppTest();
		at.testMain();
	}

}
