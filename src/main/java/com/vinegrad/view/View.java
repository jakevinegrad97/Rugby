package com.vinegrad.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.vinegrad.endofseason.EndOfSeason;
import com.vinegrad.endofseason.EndOfSeasonFactory;
import com.vinegrad.fixtures.FixtureGenerator;
import com.vinegrad.fixtures.Generator;
import com.vinegrad.fixtures.LeagueFixtureSimulator;
import com.vinegrad.fixtures.Simulator;
import com.vinegrad.model.Fixture;
import com.vinegrad.model.League;
import com.vinegrad.model.Team;
import com.vinegrad.updates.Reader;
import com.vinegrad.updates.TableWriter;
import com.vinegrad.updates.TeamReader;
import com.vinegrad.updates.TeamWriter;
import com.vinegrad.updates.Writer;

public class View {

	public static void main(String[] args) {
		Reader teamReader = new TeamReader();
		List<Team> superLeague = teamReader.getTeams(League.SUPER_LEAGUE);
		Generator fixtureGenerator = new FixtureGenerator();
		List<Fixture> fixtures = fixtureGenerator.generateFixtures(superLeague);
		Simulator simulator = new LeagueFixtureSimulator();
		Scanner scanner = new Scanner(System.in);
		Writer teamWriter = new TeamWriter();
		Writer tableWriter = new TableWriter();
		
		List<Team> teams = new ArrayList<>();
		
		for(int round = 1; round <= 30; round++) {
			System.out.println("\nSimulate round " + round + "\n");
			String a = scanner.nextLine();
			teams = simulator.simulateFixtures(fixtures, round);
			teamWriter.writeTeams(teams);
			tableWriter.writeTeams(teams);
		}
		EndOfSeason endOfSeason = EndOfSeasonFactory.getEndOfSeasonSimulator(League.SUPER_LEAGUE);
		endOfSeason.simulate(teams);
		scanner.close();
	}

}
