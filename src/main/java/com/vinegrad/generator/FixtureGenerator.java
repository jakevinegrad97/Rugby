package com.vinegrad.generator;

import java.util.ArrayList;
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
		List<Fixture> fixtures = getFixturesWithoutRounds(teams);
		List<Fixture> result = new ArrayList<>();
		Map<Integer, List<Team>> teamsByRound = new HashMap<>();
		for(int i = 0; i < 2 * (teams.size() - 1); i++) {
			teamsByRound.put(i, new ArrayList<>());
		}
		List<Integer> rounds = new ArrayList<>();
		for (Fixture fixture : fixtures) {
			for (int round = 0; round < 2 * (teams.size() - 1); round++) {
				rounds.add(round);
			}
			final Team homeTeam = fixture.getHomeTeam();
			final Team awayTeam = fixture.getAwayTeam();
			int round = 0;
			boolean isValidFixture = false;
			while (isValidFixture) {
				round = rounds.get((int) floor(rounds.size() * random()));
				if (!(teamsByRound.get(round).contains(homeTeam) || teamsByRound.get(round).contains(awayTeam))) {
					isValidFixture = true;
				} else {
					rounds.remove(rounds.indexOf(round));
				}
			}
			teamsByRound.get(round).add(homeTeam);
			teamsByRound.get(round).add(awayTeam);
			fixture.setRound(round);
			result.add(fixture);
		}
		return result;
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
