package com.vinegrad.fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;
import static java.lang.Math.*;

public class LeagueFixtureSimulator implements Simulator {

	public static final Logger LOGGER = Logger.getLogger(LeagueFixtureSimulator.class);
	
	@Override
	public List<Team> simulateFixtures(List<Fixture> fixtures, int round) {
		System.out.println("\n" + fixtures.get(0).getHomeTeam().getLeague().getDisplayName() + "\n");
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
		
		final double homeLeagueWeight = 10 * (homeTeam.getLeague().getTier() - homeTeam.getInitialLeague().getTier());
		final double awayLeagueWeight = 10 * (awayTeam.getLeague().getTier() - awayTeam.getInitialLeague().getTier());
		
		double homeChance = ((homeTeam.getAttack() + (140 - awayTeam.getDefence())) / 2
				+ 3 * homeTeam.getForm() + (30 - awayTeam.getForm())
				+ (31 - homeTeam.getPlace()) + awayTeam.getPlace()
				+ homeLeagueWeight
				+ 100 * random()) / 2;
				
		double awayChance = 0.9 * ((awayTeam.getAttack() + (140 - homeTeam.getDefence())) / 2
				+ 3 * awayTeam.getForm() + (30 - homeTeam.getForm())
				+ (31 - awayTeam.getPlace()) + homeTeam.getPlace()
				+ awayLeagueWeight
				+ 100 * random()) / 2;

		int homeTries = getTries(homeChance);
		int awayTries = getTries(awayChance);

		int homeGoals = getKicks(homeTeam, homeTries);
		int awayGoals = getKicks(awayTeam, awayTries);
		
		int homeScore = 4 * homeTries + 2 * homeGoals;
		int awayScore = 4 * awayTries + 2 * awayGoals;
		
		if(homeScore + awayScore > 80) {
			int losingScore = homeScore > awayScore ? awayScore : homeScore;
			if(losingScore > 30) {
				homeScore -= 20;
				awayScore -= 20;
			}
		}
		
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
			double random = sum * random();
			if(random <= homeTeam.getAttack() && random > 10)
				homeScore++;
			else if(random > homeTeam.getAttack() && random < sum - 10)
				awayScore++;
		}
		fixture.addResult(homeScore, awayScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		System.out.println(fixture);
	}

	private int getTries(double weight) {
		int result = (int) floor(random() * 2);
		if (weight > 50)
			result += (int) floor(random() * 2);
		if(weight > 65)
			result += (int) floor(random() * 2);
		if (weight > 75)
			result += (int) floor(random() * 3);
		if (weight > 85)
			result += (int) floor(1 + random() * 3);
		if (weight > 95)
			result += (int) floor(2 + random() * 3);
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
