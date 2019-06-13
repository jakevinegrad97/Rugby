package com.vinegrad.table;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.vinegrad.model.League;
import com.vinegrad.model.Team;

public class TablePrinter implements Printer {

	@Override
	public void print(League league) {
		try {
			System.out.println("\n" + league.getDisplayName() + " table");
			Files.lines(Paths.get("src/main/resources/tables/" + league.getInitials() + ".txt"))
				.skip(1)
				.forEach(Printer::read);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
