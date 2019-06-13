package com.vinegrad.table;

import com.vinegrad.model.League;

public interface Printer {

	void print(League league);
	
	private static int time() {
		return 200;
	};
	
	static void read(String input) {
		try {
			Thread.sleep(time());
			System.out.println(input);
			Thread.sleep(time());
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
