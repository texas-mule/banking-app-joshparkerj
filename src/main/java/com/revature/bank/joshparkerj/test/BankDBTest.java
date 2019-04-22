package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertEquals;
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
	
}
