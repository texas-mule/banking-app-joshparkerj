package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;
import com.revature.bank.joshparkerj.menu.MenuTree;

public class MenuTreeTest {

	private final String testInput = "1\n1\nAbraham\nLincoln01!\n123Test\n9\n1\n2\nBernie Sanders\n8088Test\n8099Test\n0\n40000\nBernie\nSanders\nBurlington Coat Factory\n3\n";

	@Test
	public void testMenu() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testInput.getBytes()));
		while (!mt.isFinished())
			mt.menu();
		assertTrue(db.customerExists("123Test"));
		assertTrue(db.employeeExists("8099Test"));
	}

}
