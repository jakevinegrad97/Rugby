package com.vinegrad.view;

import java.util.List;

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
		System.out.println(fixtures.size());
	}

}
