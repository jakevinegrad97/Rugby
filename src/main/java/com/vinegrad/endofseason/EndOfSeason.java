package com.vinegrad.endofseason;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.random;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;

public abstract class EndOfSeason {

	public abstract void simulate(List<Team> teams);

	protected Scanner scanner = new Scanner(System.in);
	
	protected Team determineWinner(Fixture fixture) {
		final Team homeTeam = fixture.getHomeTeam();
		final Team awayTeam = fixture.getAwayTeam();
		
		double homeChance = ((homeTeam.getAttack() + (140 - awayTeam.getDefence())) / 2
				+ 2 * homeTeam.getForm() + (20 - awayTeam.getForm())
				+ 2 * ((31 - homeTeam.getPlace()) + awayTeam.getPlace())
				+ 100 * random()) / 2;
				
		double awayHit = fixture.getRound() == 4 ? 1 : 0.9;
		
		double awayChance = awayHit * ((awayTeam.getAttack() + (140 - homeTeam.getDefence())) / 2
				+ 2 * awayTeam.getForm() + (20 - homeTeam.getForm())
				+ 2 * ((31 - awayTeam.getPlace()) + homeTeam.getPlace())
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
			if(random <= homeTeam.getAttack())
				homeScore++;
			else
				awayScore++;
		}
		fixture.addResult(homeScore, awayScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		System.out.println(fixture);
		if(homeScore > awayScore)
			return homeTeam;
		else
			return awayTeam;
	}
	
	protected Map<String, Team> determineWinnerAndLoser(Fixture fixture) {
		final Team homeTeam = fixture.getHomeTeam();
		final Team awayTeam = fixture.getAwayTeam();
		
		final double homeLeagueWeight = 10 * (homeTeam.getLeague().getTier() - homeTeam.getInitialLeague().getTier());
		final double awayLeagueWeight = 10 * (awayTeam.getLeague().getTier() - awayTeam.getInitialLeague().getTier());
		
		double homeChance = ((homeTeam.getAttack() + (100 - awayTeam.getDefence())) / 2
				+ 2 * homeTeam.getForm() + (20 - awayTeam.getForm())
				+ 2 * ((31 - homeTeam.getPlace()) + awayTeam.getPlace())
				+ homeLeagueWeight
				+ 100 * random()) / 2;
				
		double awayHit = fixture.getRound() == 4 ? 1 : 0.9;
		
		double awayChance = awayHit * ((awayTeam.getAttack() + (100 - homeTeam.getDefence())) / 2
				+ 2 * awayTeam.getForm() + (20 - homeTeam.getForm())
				+ 2 * ((31 - awayTeam.getPlace()) + homeTeam.getPlace())
				+ awayLeagueWeight
				+ 100 * random()) / 2;

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
			double random = sum * random();
			if(random <= homeTeam.getAttack())
				homeScore++;
			else
				awayScore++;
		}
		fixture.addResult(homeScore, awayScore);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		System.out.println(fixture);
		
		Map<String, Team> result = new HashMap<>();
		Team winner = homeScore > awayScore ? homeTeam : awayTeam;
		Team loser = homeScore < awayScore ? homeTeam : awayTeam;
		result.put("winner", winner);
		result.put("loser", loser);
		return result;
	}
	
	protected int getTries(double weight) {
		int result = (int) floor(random() * 2);
		if (weight > 50)
			result += (int) floor(random() * 2);
		if(weight > 65)
			result += (int) floor(random() * 2);
		if (weight > 75)
			result += (int) floor(random() * 3);
		if (weight > 85)
			result += (int) floor(random() * 3);
		if (weight > 95)
			result += (int) floor(1 + random() * 3);
		return result;
	}

	protected int getKicks(Team team, int tries) {
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
	
	protected void print(String message) {
		try {
			Thread.sleep(1000);
			System.out.println("\n" + message + "\n");
			String a = scanner.nextLine();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
