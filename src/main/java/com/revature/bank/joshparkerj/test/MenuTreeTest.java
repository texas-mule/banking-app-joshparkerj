package com.revature.bank.joshparkerj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.bank.joshparkerj.UserSession;
import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;
import com.revature.bank.joshparkerj.menu.MenuTree;

public class MenuTreeTest {

	private final String testMenuInput = "1\n1\nAbraham\nLincoln01!\n123Test\n9\n1\n2\nBernie Sanders\n8088Test\n8099Test\n0\n40000\nBernie\nSanders\nBurlington Coat Factory\n2\nAbraham\nLincoln01!\n1\n1\nChecking\n909Z\n1\nChecking\n808A\n1\nChecking\n707B\n3\n808A\n$100.00\ny\n5\n808A\n909Z\n$45.00\ny\n4\n808A\n$35.00\ny\n9\n2\nBernie Sanders\n8088Test\n2\n2\n123Test\n4\n909Z\n5\n707B\n9\n2\nAbraham\nLincoln01!\n1\n0\n";
	private final String testBadCustomerInput = "1\n1\n\n1\n1\nWolverine\n1\n1\nBernie!!!\n1\n1\nThe Fat and Happy Quick and Deadly Ninja Jumps Over the Lazy Herd of Thirsty Yaks\n1\n1\nJon\nRED01!\n1\n1\nJon\nred01!\n1\n1\nJon\nRed01!\n6869\n1\n1\nJon\nRed01!\n\n1\n1\nJon\nRed01!\n6869R\n0\n";
	private final String testEmployeeMenuInput = "2\nJoe\n1\n2\n1\n3\nX\n6\n0\n";
	private final String testBadWithdrawalInput = "2\nAbe\nLincoln01!\n1\n3\n8884\n4\n8884\n9\n2\nWolverine\nJeanGrey01!\n1\n4\n8884\n$888.00\ny\n0\n";
	private final String testSmallAmountsInput = "1\n1\nCyclops\nSlim01!\n456Test\n1\nChecking\nvladimir putin\n1\nSavings\n205D\n3\n205D\n$0.09\ny\n4\n205D\n$0.01\ny\n5\n205D\nvladimir putin\n$0.02\ny\n1\nOptic Blast\n893HB\n3\n893HB\n$79.99\ny\n4\n893HB\n$2.36\ny\n4\n893HB\n$2.36\ny\n0\n";
	private final String testBadTransferInput = "1\n1\nStorm\nOroro01@\n8882Testing\n1\nChecking\nShadow King\n1\nSavings\nForge\n3\nForge\n$1200.89\ny\n5\nForge\nShadow King\n$24,000.00\ny\n5\nZany\n5\nForge\nZany\n0\n";
	private final String testAccountNumberTakenInput = "2\nWolverine\nJeanGrey01!\n1\n1\nChecking\n8884\n0\n";
	private final String testNoSupervisorInput = "1\n2\nLinus Torvalds\n555Dudes01!\n12525\n99999\n3\n";
	private final String testRefuseTransactionInput = "2\nWolverine\nJeanGrey01!\n1\n2\n6\n3\n99H\n$38,000.00\nn\n0\n";
	private final String testNonExistentCustomerInput = "2\nJoe\n1\n2\n2\nidk!\n0\n";
	private final String testBadMenuNameInput = "";
	private final String testBadLoginInput = "2\nWolverine\nJeanGrey01!!\n3\n2\nWolverine\nJeanGrey01!!\n1\n3\n";
	private final String testBadSplashInput = "4\nZ\n\n3\n";
	private final String testBadApprovalInput = "2\nJoe\n1\n2\n4\n88G\n4\n88GG\n0\n";
	private final String testBadUserCreationInput = "1\n3\nX\n\n1\n\n3\n";
	private final String testBadDenialInput = "2\nJoe\n1\n2\n5\n88G\n5\n88GG\n0\n";
	private final String testJointAccountInput = "\n1\n1\nSuperman\nKrypton01!\nKent\n1\nHeroism\nFortress of Solitude\n9\n1\n1\nJimmy Olsen\nPhotography01!\nDaily Planet Photographer\n8\n1\nFortress of Solitude\n9\n1\n1\nLex Luthor\nKryptonite01!\nEvil Genius\n8\n1\nFortress of Solitude\n9\n2\nSuperman\nKrypton01!\n1\n8\n2\n3\nDaily Planet Photographer\nFortress of Solitude\n2\n4\nFortress of Solitude\nEvil Genius\n2\n0\n";
	
	@Before
	public void initialize() {
		FAKEOS.clearOutput();
	}
	
	@Test
	public void testMenu() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testMenuInput.getBytes()), new PrintStream(new FAKEOS()));
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
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadCustomerInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
		assertTrue(db.customerExists("6869R"));
	}

	@Test
	public void testEmployeeMenu() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testEmployeeMenuInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}

	@Test
	public void testBadWithdrawal() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadWithdrawalInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
		assertTrue(FAKEOS.getOutput().contains("Not your account!"));
		assertTrue(FAKEOS.getOutput().contains("You are not allowed to withdraw that much!"));
	}

	@Test
	public void testSmallAmounts() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testSmallAmountsInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
		assertEquals(db.getBalance("vladimir putin"), "$0.02");
		assertEquals(db.getBalance("205D"), "$0.06");
		assertEquals(db.getBalance("893HB"), "$75.27");
	}

	@Test
	public void testBadTransfer() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadTransferInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
		assertEquals(db.getBalance("Forge"), "$1200.89");
		assertEquals(db.getBalance("Shadow King"), "$0.00");
	}

	@Test
	public void testAccountNumberTaken() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testAccountNumberTakenInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}

	@Test
	public void testNoSupervisor() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testNoSupervisorInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}

	@Test
	public void testRefuseTransaction() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testRefuseTransactionInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}

	@Test
	public void testNonExistentCustomer() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testNonExistentCustomerInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testBadMenuName() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadMenuNameInput.getBytes()), new PrintStream(new FAKEOS()));
		mt.queueMenu("PerfectlyLegitimate");
		mt.menu();
	}
	
	@Test
	public void testBadLogin() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadLoginInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testBadSplash() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadSplashInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testBadApproval() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadApprovalInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testBadUserCreation() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadUserCreationInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testBadDenial() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testBadDenialInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
	}
	
	@Test
	public void testJointAccount() {
		IDB db = BankDB.getDB("DefaultData.txt");
		MenuTree mt = new MenuTree(db, new ByteArrayInputStream(testJointAccountInput.getBytes()), new PrintStream(new FAKEOS()));
		while (!mt.isFinished())
			mt.menu();
		FAKEOS.setEncoding("bad encoding string");
		assertTrue(FAKEOS.getOutput().equals(""));
		FAKEOS.setEncoding("UTF-8");
		initialize();
		assertTrue(FAKEOS.getOutput().equals(""));
	}
	
	private static class FAKEOS extends OutputStream {
		
		public static void clearOutput() {
			output.clear();
		}

		public static String getOutput() {
			byte[] bytes = new byte[output.size()];
			int i = 0;
			for (Byte b : output) {
				bytes[i++] = b.byteValue();
			}
			try {
				return new String(bytes, encoding);
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}
		
		private static String encoding = "UTF-8";
		public static void setEncoding(String e) {
			encoding = e;
		}

		private static List<Byte> output;
		static {
			output = new ArrayList<Byte>();
		}

		public void write(int b) {
			output.add((byte) b);
		}
		
	}
	
}
