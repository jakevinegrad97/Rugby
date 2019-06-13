package com.vinegrad.endofseason;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.League;
import com.vinegrad.model.Team;

public class EndOfSeasonImpl extends EndOfSeason {
	
	private Scanner scanner;

	public void simulate(List<Team> teams) {
		final League league = teams.get(0).getLeague();
		List<Team> top5 = teams.stream()
			.filter(team -> team.getPlace() <= 5)
			.sorted(Comparator.comparing(Team::getPlace))
			.collect(Collectors.toList());
		
		print(league.getDisplayName() + " End of Season!");
		Team bottom = teams.stream()
				.filter(team -> team.getPlace() == teams.size())
				.findFirst()
				.get();
		print(bottom.getName() + " are relegated!");
		bottom.relegate();
		
		final Team first = top5.get(0);
		final Team second = top5.get(1);
		final Team third = top5.get(2);
		final Team fourth = top5.get(3);
		final Team fifth = top5.get(4);

		scanner = new Scanner(System.in);
		
		//week 1
		Fixture qualificationFinal = new Fixture(second, third, 1);
		Fixture eliminationFinal = new Fixture(fourth, fifth, 1);
		
		print("Qualification Final! " + qualificationFinal.beforeMatch());
		Map<String, Team> qualificationFinalOutcome = determineWinnerAndLoser(qualificationFinal);
		Team winnerQualificationFinal = qualificationFinalOutcome.get("winner");
		Team loserQualificationFinal = qualificationFinalOutcome.get("loser");
		
		print("Elimination Final! " + eliminationFinal.beforeMatch());
		Team winnerEliminationFinal = determineWinner(eliminationFinal);
		
		//week 2
		Fixture majorSemiFinal = new Fixture(first, winnerQualificationFinal, 2);
		Fixture minorSemiFinal = new Fixture(loserQualificationFinal, winnerEliminationFinal, 2);
		
		print("Major Semi Final! " + majorSemiFinal.beforeMatch());
		Map<String, Team> majorSemiFinalOutcome = determineWinnerAndLoser(majorSemiFinal);
		Team winnerMajorSemiFinal = majorSemiFinalOutcome.get("winner");
		Team loserMajorSemiFinal = majorSemiFinalOutcome.get("loser");
		
		print("Minor Semi Final! " + minorSemiFinal.beforeMatch());
		Team winnerMinorSemiFinal = determineWinner(minorSemiFinal);
		
		//week 3
		Fixture preliminaryFinal = new Fixture(loserMajorSemiFinal, winnerMinorSemiFinal, 3);
		
		print("Preliminary Final! " + preliminaryFinal.beforeMatch());
		Team winnerPreliminaryFinal = determineWinner(preliminaryFinal);
		
		//week 4
		Fixture grandFinal = new Fixture(winnerMajorSemiFinal, winnerPreliminaryFinal, 4);
		
		print("Grand Final! " + grandFinal.beforeMatch());
		Team winnerSuperLeague = determineWinner(grandFinal);
		
		switch(league) {
		case SUPER_LEAGUE :
			print(winnerSuperLeague.getName() + " win Super League!");
			break;
		case CHAMPIONSHIP :
			print(winnerSuperLeague.getName() + " are promoted!");
			winnerSuperLeague.promote();
			break;
		}
		
	}

	
	
}
