package com.vinegrad.model;

public class Fixture {

	private Team homeTeam;
	private Team awayTeam;
	private int homeScore, awayScore;
	private int round;
	
	public Fixture(Team homeTeam, Team awayTeam) {
		homeScore = 0;
		awayScore = 0;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public Fixture(Team homeTeam, Team awayTeam, int round) {
		homeScore = 0;
		awayScore = 0;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.round = round;
	}
	
	public void addResult(int homeScore, int awayScore) {
		homeTeam.addResult(homeScore, awayScore);
		awayTeam.addResult(awayScore, homeScore);
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	public Team getHomeTeam() {
		return homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public int getRound() {
		return round;
	}
	
	public String toString() {
		return homeTeam.getName() + " " + homeScore + " : " + awayScore + " " + awayTeam.getName();
	}
	
	public String beforeMatch() {
		return homeTeam.getName() + " vs. " + awayTeam.getName();	
	}
	
	public void reverse() {
		Team temp = homeTeam;
		homeTeam = awayTeam;
		awayTeam = temp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fixture other = (Fixture) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		return true;
	}
	
	
	
}
