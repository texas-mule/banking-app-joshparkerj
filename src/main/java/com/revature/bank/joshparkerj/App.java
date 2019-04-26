package com.revature.bank.joshparkerj;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;
import com.revature.bank.joshparkerj.menu.MenuTree;

public class App {

	private static InputStream s = System.in;
	private static String filename;

	public static void main(String[] args) {
		filename = args.length > 0 ? args[0] : "DefaultData.txt";
		if (args.length > 1) {
			if (args[1] == "TEST") {
				s = new ByteArrayInputStream("3\n".getBytes());
			}
		}
		IDB db = BankDB.getDB(filename);
		if (db.uninitialized())
			return;
		MenuTree mt = new MenuTree(db, s);
		while (!mt.isFinished())
			mt.menu();
		db.write();
	}

}
