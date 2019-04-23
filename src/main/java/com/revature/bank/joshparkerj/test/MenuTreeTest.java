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

	private final String testMenuInput = "1\n1\nAbraham\nLincoln01!\n123Test\n9\n1\n2\nBernie Sanders\n8088Test\n8099Test\n0\n40000\nBernie\nSanders\nBurlington Coat Factory\n2\nAbraham\nLincoln01!\n1\n1\nChecking\n909Z\n1\nChecking\n808A\n1\nChecking\n707B\n3\n808A\n$100.00\ny\n5\n808A\n909Z\n$45.00\ny\n4\n808A\n$35.00\ny\n9\n2\nBernie Sanders\n8088Test\n2\n2\n123Test\n4\n909Z\n5\n707B\n9\n2\nAbraham\nLincoln01!\n1\n0\n";
	private final String testBadCustomerInput = "1\n1\n\n1\n1\nWolverine\n1\n1\nBernie!!!\n1\n1\nThe Fat and Happy Quick and Deadly Ninja Jumps Over the Lazy Herd of Thirsty Yaks\n1\n1\nJon\nRED01!\n1\n1\nJon\nred01!\n1\n1\nJon\nRed01!\n6869\n1\n1\nJon\nRed01!\n\n1\n1\nJon\nRed01!\n6869R\n0\n";
	private final String testEmployeeMenuInput = "2\nJoe\n1\n2\n1\n3\nX\n6\n0\n";
	private final String testBadWithdrawalInput = "2\nAbe\nLincoln01!\n1\n3\n8884\n0\n";

	@Test
	public void testMenu() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testMenuInput.getBytes()));
		while (!mt.isFinished())
			mt.menu();
		assertTrue(db.customerExists("123Test"));
		assertTrue(db.employeeExists("8099Test"));
		assertEquals("123Test", UserSession.getID());
		assertEquals("$45.00", db.getBalance("909Z"));
		assertEquals("$20.00", db.getBalance("808A"));
		assertTrue(db.accountApproved("909Z"));
		assertFalse(db.accountApproved("808A"));
		assertFalse(db.accountExists("707B"));
		assertTrue(db.getPendingApps().contains("808A"));
	}
	
	@Test
	public void testBadCustomer() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadCustomerInput.getBytes()));
		while (!mt.isFinished())
			mt.menu();
		assertTrue(db.customerExists("6869R"));
	}
	
	@Test
	public void testEmployeeMenu() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testEmployeeMenuInput.getBytes()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testBadWithdrawal() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadWithdrawalInput.getBytes()));
		while (!mt.isFinished())
			mt.menu();
		
	}

}
