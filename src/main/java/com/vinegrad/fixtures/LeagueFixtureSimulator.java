package com.vinegrad.fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;
import static java.lang.Math.*;

public class LeagueFixtureSimulator implements Simulator {

	@Override
	public List<Team> simulateFixtures(List<Fixture> fixtures, int round) {
		List<Team> result = new ArrayList<>();
		List<Fixture> thisRound = fixtures.stream().filter(f -> f.getRound() == round).collect(Collectors.toList());
		for (Fixture fixture : thisRound) {
			determineVictor(fixture);
			result.add(fixture.getHomeTeam());
			result.add(fixture.getAwayTeam());
		}
		return result;
	}

	private void determineVictor(Fixture fixture) {
		final Team homeTeam = fixture.getHomeTeam();
		final Team awayTeam = fixture.getAwayTeam();
		double homeChance = (homeTeam.getAttack() + (100 - awayTeam.getDefence())) / 2 + 100 * random();
		double awayChance = 0.9 * ((awayTeam.getAttack() + (100 - homeTeam.getDefence())) / 2 + 100 * random());

		int homeTries = getTries(homeChance);
		int awayTries = getTries(awayChance);

		int homeGoals = getKicks(homeTeam, homeTries);
		int awayGoals = getKicks(awayTeam, awayTries);
		
		int homeScore = 4 * homeTries + 2 * homeGoals;
		int awayScore = 4 * awayTries + 2 * awayGoals;
		
		if(abs(homeScore - awayScore) <= 6) {
			double random = random();
			if(random < 0.05) {
				if(homeScore > awayScore)
					awayScore++;
				else if(homeScore < awayScore)
					homeScore++;
			} else if (random < 0.2) {
				if(homeScore > awayScore)
					homeScore++;
				else if(homeScore < awayScore)
					awayScore ++;
			}
		}
		
		if(homeScore == awayScore) {
			int sum = (int) (homeTeam.getAttack() + awayTeam.getAttack());
			double random = random();
			if(random <= homeTeam.getAttack())
				homeScore++;
			else if(random > homeTeam.getAttack() && random <= sum)
				awayScore++;
		}
		fixture.addResult(homeScore, awayScore);
		System.out.println(fixture);
	}

	private int getTries(double weight) {
		int result = (int) floor(random() * 2);
		if (weight > 20)
			result += (int) floor(random() * 2);
		if (weight > 50)
			result += (int) floor(random() * 2);
		if (weight > 75)
			result += (int) floor(random() * 3);
		if (weight > 85)
			result += (int) floor(random() * 3);
		if (weight > 95)
			result += (int) floor(random() * 4);
		return result;
	}

	private int getKicks(Team team, int tries) {
		final double attack = team.getAttack();
		int kicks = 0;
		if (tries > 0) {
			for (int i = 1; i < tries; i++) {
				if(random() * 100 < attack)
					kicks++;
			}
		}
		kicks += (int) floor(random() * 5);
		return kicks;
	}

}
