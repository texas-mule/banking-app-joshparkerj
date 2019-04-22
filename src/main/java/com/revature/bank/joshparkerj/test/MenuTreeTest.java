package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import com.revature.bank.joshparkerj.UserSession;
import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;
import com.revature.bank.joshparkerj.menu.MenuTree;

public class MenuTreeTest {

	private final String testInput = "1\n1\nAbraham\nLincoln01!\n123Test\n9\n1\n2\nBernie Sanders\n8088Test\n8099Test\n0\n40000\nBernie\nSanders\nBurlington Coat Factory\n2\nAbraham\nLincoln01!\n1\n1\nChecking\n909Z\n1\nChecking\n808A\n3\n808A\n$100.00\ny\n5\n808A\n909Z\n$45.00\ny\n9\n2\nBernie Sanders\n8088Test\n2\n4\n909Z\n9\n2\nAbraham\nLincoln01!\n1\n0\n";

	@Test
	public void testMenu() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testInput.getBytes()));
		while (!mt.isFinished())
			mt.menu();
		assertTrue(db.customerExists("123Test"));
		assertTrue(db.employeeExists("8099Test"));
		assertEquals("123Test", UserSession.getID());
		assertEquals("$45.00", db.getBalance("909Z"));
		assertEquals("$55.00", db.getBalance("808A"));
		assertTrue(db.accountApproved("909Z"));
		assertFalse(db.accountApproved("808A"));
	}

}
