package com.vinegrad.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.vinegrad.endofseason.EndOfSeason;
import com.vinegrad.endofseason.EndOfSeasonFactory;
import com.vinegrad.endofseason.FinalWriteTeams;
import com.vinegrad.fixtures.FixtureGenerator;
import com.vinegrad.fixtures.Generator;
import com.vinegrad.fixtures.LeagueFixtureSimulator;
import com.vinegrad.fixtures.Simulator;
import com.vinegrad.model.Fixture;
import com.vinegrad.model.League;
import com.vinegrad.model.Team;
import com.vinegrad.table.Printer;
import com.vinegrad.table.TablePrinter;
import com.vinegrad.updates.Reader;
import com.vinegrad.updates.TableWriter;
import com.vinegrad.updates.TeamReader;
import com.vinegrad.updates.TeamWriter;
import com.vinegrad.updates.Writer;

public class View {

	public static void main(String[] args) {
		Reader teamReader = new TeamReader();
		List<Team> superLeague = teamReader.getTeams(League.SUPER_LEAGUE);
		List<Team> championship = teamReader.getTeams(League.CHAMPIONSHIP);
		
		Generator fixtureGenerator = new FixtureGenerator();
		List<Fixture> fixturesSL = fixtureGenerator.generateFixtures(superLeague);
		List<Fixture> fixturesCH = fixtureGenerator.generateFixtures(championship);
		
		Simulator simulator = new LeagueFixtureSimulator();
		Scanner scanner = new Scanner(System.in);
		Writer teamWriter = new TeamWriter();
		Writer tableWriter = new TableWriter();
		Printer tablePrinter = new TablePrinter();
		
		List<Team> teamsSL = new ArrayList<>();
		List<Team> teamsCH = new ArrayList<>();
		
		for(int round = 1; round <= 30; round++) {
			System.out.println("\nSimulate round " + round + "\n");
			String a = scanner.nextLine();
			
			teamsSL = simulator.simulateFixtures(fixturesSL, round);
			teamsCH = simulator.simulateFixtures(fixturesCH, round);
			
			teamWriter.writeTeams(teamsSL);
			teamWriter.writeTeams(teamsCH);
			
			tableWriter.writeTeams(teamsSL);
			tableWriter.writeTeams(teamsCH);
			
			tablePrinter.print(League.SUPER_LEAGUE);
			tablePrinter.print(League.CHAMPIONSHIP);
		}
		EndOfSeason endOfSeasonSL = EndOfSeasonFactory.getEndOfSeasonSimulator(League.SUPER_LEAGUE);
		EndOfSeason endOfSeasonCH = EndOfSeasonFactory.getEndOfSeasonSimulator(League.CHAMPIONSHIP);
		
		endOfSeasonSL.simulate(teamsSL);
		endOfSeasonCH.simulate(teamsCH);
		
		List<Team> allTeams = new ArrayList<>();
		allTeams.addAll(teamsSL);
		allTeams.addAll(teamsCH);
		
		FinalWriteTeams finalWriteTeams = new FinalWriteTeams();
		finalWriteTeams.writeAllTeams(allTeams);
		
		scanner.close();
	}

}
