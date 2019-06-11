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
	
	public void addResult(int homeScore, int awayScore) {
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
	
}
