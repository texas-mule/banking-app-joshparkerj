package com.revature.bank.joshparkerj.menu;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import com.revature.bank.joshparkerj.db.IDB;

public class MenuTree {

	private IDB db;
	private Scanner s;
	private boolean finished;
	private String nextMenu = "Splash";

	public MenuTree(IDB d) {
		db = d;
		s = new Scanner(System.in);
	}

	public void menu() {
		try {
			Class<?> c = Class.forName("com.revature.bank.joshparkerj.menu." + nextMenu + "Menu");
			ABCMenu a = (ABCMenu) c.getConstructor(MenuTree.class, Scanner.class).newInstance(this, s);
			a.Run();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Failed to open menu: " + nextMenu);
			System.out.println(e.toString());
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
