package com.vinegrad.fixtures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import static java.lang.Math.floor;
import static java.lang.Math.random;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;

public class FixtureGenerator implements Generator {

	public static final Logger LOGGER = Logger.getLogger(FixtureGenerator.class);

	@Override
	public List<Fixture> generateFixtures(List<Team> teams) {
		List<Fixture> result = new ArrayList<>();
		for(int round = 1; round <= 30; round++) {
			if(round == 12)
				round = 13;
			List<Fixture> possibleFixtures = getFixturesWithoutRounds(teams);
			List<Fixture> thisRound = new ArrayList<>();
			possibleFixtures.removeAll(result);
			while(thisRound.size() < teams.size() / 2) {
				Fixture fixture = possibleFixtures.get((int) floor(possibleFixtures.size() * random()));
				if(isValidFixture(fixture, thisRound)) {
					if(result.stream().filter(f -> f.getHomeTeam().equals(fixture.getHomeTeam()) && f.getAwayTeam().equals(fixture.getAwayTeam())).count() < 2) {
					possibleFixtures.remove(fixture);
					fixture.setRound(round);
					thisRound.add(fixture);
					}
				} else {
					possibleFixtures.remove(fixture);
				}
				if(possibleFixtures.size() == 0) {
					possibleFixtures = getFixturesWithoutRounds(teams);
					thisRound = new ArrayList<>();
				}
			}
			result.addAll(thisRound);
		}
		List<Fixture> magicWeekend = new ArrayList<>();
		List<Fixture> possibleFixtures = getFixturesWithoutRounds(teams);
		while(magicWeekend.size() < teams.size() / 2) {
			Fixture fixture = possibleFixtures.get((int) floor(possibleFixtures.size() * random()));
			while(!isValidFixture(fixture, magicWeekend)) {
				possibleFixtures.remove(fixture);
				fixture = possibleFixtures.get((int) floor(possibleFixtures.size() * random()));
			}
			possibleFixtures.remove(fixture);
			fixture.setRound(12);
			magicWeekend.add(fixture);
		}
		result.addAll(magicWeekend);
		return result;
	}

	private boolean isValidFixture(Fixture fixture, List<Fixture> thisRound) {
		List<Team> teams = thisRound.stream()
			.map(f -> f.getHomeTeam())
			.collect(Collectors.toList());
		List<Team> aways = thisRound.stream()
				.map(f -> f.getAwayTeam())
				.collect(Collectors.toList());
		teams.addAll(aways);
		if(teams.contains(fixture.getHomeTeam()) || teams.contains(fixture.getAwayTeam()))
			return false;
		else
			return true;
	}

	private List<Fixture> getFixturesWithoutRounds(List<Team> teams) {
		List<Fixture> fixtures = new ArrayList<>();
		for (Team homeTeam : teams) {
			for (Team awayTeam : teams) {
				if (!homeTeam.getName().equals(awayTeam.getName())) {
					Fixture fixture = new Fixture(homeTeam, awayTeam);
					fixtures.add(fixture);
				}
			}
		}
		return fixtures;
	}

}
