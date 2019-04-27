package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;

public class BadFileTest {

	@Test
	public void testBadFileName() {
		IDB db = BankDB.getDB("DefaultData.txt");
		assertTrue(db.accountholder().holdsAccount("idk", "88G"));
		db = BankDB.getDB("~*~*~Bad#:#:#File%:%:%Name&*&*&");
		assertTrue(db.account().accountExists("88G"));
		db.close();
		db = BankDB.getDB("~*~*~Bad#:#:#File%:%:%Name&*&*&");
		assertTrue(db.uninitialized());
		db.write();
		db.close();
		db = BankDB.getDB("LockedData.txt");
		db.write();
		db.close();
	}

}
