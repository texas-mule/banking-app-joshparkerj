package com.revature.bank.joshparkerj;

public class App {

	private static String filename;
	
	public static void main(String[] args) {
		filename = args.length > 0 ? args[0] : "DefaultData.txt";
		IDB db = BankDB.getDB(filename);
		MenuTree mt = new MenuTree(db);
		mt.menu("Splash");
		db.write();
	}

}
