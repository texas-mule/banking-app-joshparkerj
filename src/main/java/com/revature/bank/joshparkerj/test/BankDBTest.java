package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;

public class BankDBTest {

	private IDB db;
	
	@Test
	public void testGetDB() {
		db = BankDB.getDB("DefaultData.txt");
		assertTrue(db instanceof BankDB);
		assertEquals(db, BankDB.getDB(""));
	}
	
	@Test
	public void testWrite() {
		db = BankDB.getDB("DefaultData.txt");
		db.addCustomer("Doug Woolford Jones", "IAmaGiantBaby01!", "1231235555123");
		db.write();
		db.close();
		db = BankDB.getDB("DefaultData.txt");
		assertEquals(db.getCustomerID("Doug Woolford Jones", "IAmaGiantBaby01!"), "1231235555123");
		assertTrue(db.customerExists("1231235555123"));
		db.deleteCustomer("1231235555123");
		assertTrue(!db.customerExists("1231235555123"));
	}
	
}
