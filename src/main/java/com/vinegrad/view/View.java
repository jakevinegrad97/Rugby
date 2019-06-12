package com.vinegrad.view;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.vinegrad.generator.FixtureGenerator;
import com.vinegrad.generator.Generator;
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
		for(int i = 1; i <= 22; i++) {
			System.out.println("Round " + i + ":");
			int round = i;
			fixtures.stream().filter(fixture -> fixture.getRound() == round)
					.forEach(fixture -> System.out.println(fixture.beforeMatch()));;
		}
	}

}
