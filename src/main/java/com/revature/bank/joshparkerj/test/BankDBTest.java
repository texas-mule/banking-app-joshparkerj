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
		db.customer().addCustomer("Doug Woolford Jones", "IAmaGiantBaby01!", "1231235555123");
		db.write();
		db.close();
		db = BankDB.getDB("DefaultData.txt");
		assertEquals(db.customer().getCustomerID("Doug Woolford Jones", "IAmaGiantBaby01!"), "1231235555123");
		assertTrue(db.customer().customerExists("1231235555123"));
		db.deleteCustomer("1231235555123");
		assertFalse(db.customer().customerExists("1231235555123"));
		db.write();
		db.close();
	}
	
	@Test
	public void testGetEmployeeID() {
		IDB db = BankDB.getDB("DefaultData.txt");
		db.employee().addEmployee("a", "b", "c", "0", "e", "f", "g", "h");
		assertEquals(db.employee().getEmployeeID("a", "b"), "c");
		assertTrue(db.employee().employeeExists("c"));
		db.employee().deleteEmployee("c");
		assertFalse(db.employee().employeeExists("c"));
	}
	
	@Test
	public void testCustomerTransactions() {
		IDB db = BankDB.getDB("DefaultData.txt");
		db.customer().addCustomer("a", "b", "coolguy");
		assertTrue(db.customer().customerExists("coolguy"));
		db.account().addAccount("e", "failaccount");
		assertTrue(db.account().accountExists("failaccount"));
		db.accountholder().addAccountHolder("coolguy", "failaccount");
		assertTrue(db.getCustomerAccounts("coolguy").contains("failaccount"));
		assertFalse(db.getCustomerAccounts("coolguy").contains("hackaccount"));
		db.account().approveAccount("failaccount");
		assertTrue(db.account().accountApproved("failaccount"));
		db.account().deposit("failaccount", "$1000.00");
		assertTrue(db.account().sufficientFunds("failaccount","$1000.00"));
		db.account().withdraw("failaccount", "$900.00");
		assertFalse(db.account().sufficientFunds("failaccount", "$1000.00"));
		assertTrue(db.account().getBalance("failaccount").equals("$100.00"));
		assertTrue(db.account().sufficientFunds("failaccount","$100.00"));
		db.account().addAccount("g", "hackaccount");
		assertTrue(db.account().accountExists("hackaccount"));
		db.accountholder().addAccountHolder("coolguy", "hackaccount");
		assertTrue(db.getCustomerAccounts("coolguy").contains("hackaccount"));
		db.account().transfer("failaccount", "hackaccount", "$90.00");
		assertTrue(db.account().sufficientFunds("hackaccount", "$90.00"));
		db.deleteAccount("hackaccount");
		db.deleteAccount("failaccount");
		db.deleteCustomer("coolguy");
		assertFalse(db.account().accountExists("hackaccount"));
		assertFalse(db.account().accountExists("failaccount"));
		assertFalse(db.customer().customerExists("coolguy"));
	}
	
	
	@Test
	public void testMissingRecords() {
		IDB db = BankDB.getDB("DefaultData.txt");
		assertNull(db.customer().getCustomerID("Goblin Queen", "PryorClone01!"));
		assertNull(db.employee().getEmployeeID("Douglas K Jones", "8088TheBest!"));
		assertTrue(db.getCustomerAccounts("6869").equals("You have no accounts!\n"));
		assertNull(db.account().deposit("8884NoSuchThing","$1,000,000.00"));
		assertTrue(db.accountholder().holdsAccount("idk", "8884"));
		assertFalse(db.accountholder().holdsAccount("6869", "8884"));
		assertNull(db.account().withdraw("8884NoSuchAccount","$10.000.000,00"));
		assertNull(db.account().transfer("8884NoSuchAccount", "88G", "$8.00"));
		assertNull(db.account().transfer("88G", "ugly", "$3.85"));
		assertNull(db.account().transfer("88G", "99H", "$51.00"));
		assertNull(db.customer().customerDetails("idk but it\'s wolverine"));
		assertFalse(db.account().accountApproved("8884NoSuchAccount"));
		db.account().approveAccount("8884NoSuchAccount");
		assertFalse(db.account().sufficientFunds("8884NoSuchAccount", "$0.00"));
		assertNull(db.account().getBalance("8884NoSuchAccount"));
	}
	
	@Test
	public void testUniqueAccountNumber() {
		IDB db = BankDB.getDB("DefaultData.txt");
		assertTrue(db.account().uniqueAccountNumber("8884Bub"));
		assertFalse(db.account().uniqueAccountNumber("8884"));
	}
	
}
