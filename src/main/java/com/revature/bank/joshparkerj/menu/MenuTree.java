package com.revature.bank.joshparkerj.menu;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.revature.bank.joshparkerj.db.IDB;

public class MenuTree {

	PrintStream ps;
	private IDB db;
	private Scanner s;
	private boolean finished;
	private String nextMenu = "Splash";

	public MenuTree(IDB d, InputStream i_s) {
		db = d;
		s = new Scanner(i_s);
		ps = System.out;
	}
	
	public MenuTree(IDB d, InputStream i_s, PrintStream p) {
		db = d;
		s = new Scanner(i_s);
		ps = p;
	}

	public void menu() {
		try {
			Class<?> c = Class.forName("com.revature.bank.joshparkerj.menu." + nextMenu + "Menu");
			ABCMenu a = (ABCMenu) c.getConstructor(MenuTree.class, Scanner.class).newInstance(this, s);
			a.Run();
		} catch (Exception e) {
			ps.println("Failed to open menu: " + nextMenu);
			ps.println(e.toString());
		}
	}

	public IDB getDB() {
		return db;
	}

	public boolean isFinished() {
		return this.finished;
	}

	public void quit() {
		this.finished = true;
	}

	public void queueMenu(String m) {
		this.nextMenu = m;
	}

}
