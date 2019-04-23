package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
		assertFalse(db.uninitialized());
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
	
	
	@Test
	public void testMissingRecords() {
		IDB db = BankDB.getDB("DefaultData.txt");
		assertNull(db.getCustomerID("Goblin Queen", "PryorClone01!"));
		assertNull(db.getEmployeeID("Douglas K Jones", "8088TheBest!"));
		assertTrue(db.getCustomerAccounts("6869").equals("You have no accounts!\n"));
		assertNull(db.deposit("8884NoSuchThing","$1,000,000.00"));
		assertTrue(db.holdsAccount("8884", "idk"));
		assertFalse(db.holdsAccount("8884", "6869"));
		assertNull(db.withdraw("8884NoSuchAccount","$10.000.000,00"));
		assertNull(db.transfer("8884NoSuchAccount", "88G", "$8.00"));
		assertNull(db.transfer("88G", "ugly", "$3.85"));
		assertNull(db.transfer("88G", "99H", "$51.00"));
		assertNull(db.customerDetails("idk but it\'s wolverine"));
		assertFalse(db.accountApproved("8884NoSuchAccount"));
		db.approveAccount("8884NoSuchAccount");
		assertFalse(db.sufficientFunds("8884NoSuchAccount", "$0.00"));
		assertNull(db.getBalance("8884NoSuchAccount"));
	}
	
	@Test
	public void testUniqueAccountNumber() {
		IDB db = BankDB.getDB("DefaultData.txt");
		assertTrue(db.uniqueAccountNumber("8884Bub"));
		assertFalse(db.uniqueAccountNumber("8884"));
	}
	
}
