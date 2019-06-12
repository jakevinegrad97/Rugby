package com.vinegrad.view;

import java.util.List;
import java.util.Scanner;

import com.vinegrad.fixtures.FixtureGenerator;
import com.vinegrad.fixtures.Generator;
import com.vinegrad.fixtures.LeagueFixtureSimulator;
import com.vinegrad.fixtures.Simulator;
import com.vinegrad.model.Fixture;
import com.vinegrad.model.League;
import com.vinegrad.model.Team;
import com.vinegrad.reader.Reader;
import com.vinegrad.reader.TeamReader;

public class View {

	public static void main(String[] args) {
		Reader teamReader = new TeamReader();
		List<Team> superLeague = teamReader.getTeams(League.SUPER_LEAGUE);
		Generator fixtureGenerator = new FixtureGenerator();
		List<Fixture> fixtures = fixtureGenerator.generateFixtures(superLeague);
		Simulator simulator = new LeagueFixtureSimulator();
		Scanner scanner = new Scanner(System.in);
		for(int round = 1; round < 2 * (superLeague.size() - 1); round++) {
			System.out.println("\nSimulate round " + round + "\n");
			String a = scanner.nextLine();
			List<Team> teams = simulator.simulateFixtures(fixtures, round);
			System.out.println();
			teams.forEach(team -> System.out.println(team.tableFormat()));
		}
	}

}
