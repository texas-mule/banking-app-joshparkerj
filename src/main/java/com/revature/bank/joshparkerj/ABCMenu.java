package com.revature.bank.joshparkerj;

import java.util.Scanner;

public abstract class ABCMenu {

	protected MenuTree mt;
	protected Scanner s;

	public ABCMenu(MenuTree m, Scanner ss) {
		mt = m;
		s = ss;
	}

	public abstract void Run();

}
