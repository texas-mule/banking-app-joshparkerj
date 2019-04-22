package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;

public class BankDBTest {
	
	@Test
	public void testGetDB() {
		IDB db = BankDB.getDB("DefaultData.txt");
		assertTrue(db instanceof BankDB);
		assertEquals(db, BankDB.getDB(""));
	}
	
	@Test
	public void testWrite() {
		IDB db = BankDB.getDB("DefaultData.txt");
		db.addCustomer("Doug Woolford Jones", "IAmaGiantBaby01!", "1231235555123");
		db.write();
		db.close();
		db = BankDB.getDB("DefaultData.txt");
		assertEquals(db.getCustomerID("Doug Woolford Jones", "IAmaGiantBaby01!"), "1231235555123");
		assertTrue(db.customerExists("1231235555123"));
		db.deleteCustomer("1231235555123");
		assertFalse(db.customerExists("1231235555123"));
		db.write();
		db.close();
	}
	
	@Test
	public void testGetEmployeeID() {
		IDB db = BankDB.getDB("DefaultData.txt");
		db.addEmployee("a", "b", "c", "0", "e", "f", "g", "h");
		assertEquals(db.getEmployeeID("a", "b"), "c");
		assertTrue(db.employeeExists("c"));
		db.deleteEmployee("c");
		assertFalse(db.employeeExists("c"));
	}
	
	@Test
	public void testCustomerTransactions() {
		IDB db = BankDB.getDB("DefaultData.txt");
		db.addCustomer("a", "b", "coolguy");
		assertTrue(db.customerExists("coolguy"));
		db.addAccount("e", "failaccount");
		assertTrue(db.accountExists("failaccount"));
		db.addAccountHolder("coolguy", "failaccount");
		assertTrue(db.getCustomerAccounts("coolguy").contains("failaccount"));
		assertFalse(db.getCustomerAccounts("coolguy").contains("hackaccount"));
		db.approveAccount("failaccount");
		assertTrue(db.accountApproved("failaccount"));
		db.deposit("failaccount", "$1000.00");
		assertTrue(db.sufficientFunds("failaccount","$1000.00"));
		db.withdraw("failaccount", "$900.00");
		assertFalse(db.sufficientFunds("failaccount", "$1000.00"));
		assertTrue(db.getBalance("failaccount").equals("$100.00"));
		assertTrue(db.sufficientFunds("failaccount","$100.00"));
		db.addAccount("g", "hackaccount");
		assertTrue(db.accountExists("hackaccount"));
		db.addAccountHolder("coolguy", "hackaccount");
		assertTrue(db.getCustomerAccounts("coolguy").contains("hackaccount"));
		db.transfer("failaccount", "hackaccount", "$90.00");
		assertTrue(db.sufficientFunds("hackaccount", "$90.00"));
		db.deleteAccount("hackaccount");
		db.deleteAccount("failaccount");
		db.deleteCustomer("coolguy");
		assertFalse(db.accountExists("hackaccount"));
		assertFalse(db.accountExists("failaccount"));
		assertFalse(db.customerExists("coolguy"));
	}
	
}
