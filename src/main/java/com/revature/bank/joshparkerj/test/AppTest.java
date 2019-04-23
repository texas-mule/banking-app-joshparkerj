package com.revature.bank.joshparkerj.test;

import org.junit.Test;

import com.revature.bank.joshparkerj.App;

public class AppTest {
	
	private final String[] args = {"DefaultData.txt", "TEST"};
	
	@Test
	public void testMain(){
		App.main(args);
	}

}
