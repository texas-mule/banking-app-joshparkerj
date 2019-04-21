package com.revature.bank.joshparkerj;

import com.revature.bank.joshparkerj.db.BankDB;
import com.revature.bank.joshparkerj.db.IDB;
import com.revature.bank.joshparkerj.menu.MenuTree;

public class App {

	private static String filename;

	public static void main(String[] args) {
		filename = args.length > 0 ? args[0] : "DefaultData.txt";
		IDB db = BankDB.getDB(filename);
		MenuTree mt = new MenuTree(db);
		while (!mt.isFinished())
			mt.menu();
		db.write();
	}

}
