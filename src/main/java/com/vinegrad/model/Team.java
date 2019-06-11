package com.vinegrad.model;

public class Team {

	private String name;
	private int played;
	private int won;
	private int drawn;
	private int lost;
	private int scored;
	private int conceded;
	private int pointsDifference;
	private int points;
	private int attack;
	private int defence;
	
	public Team(String name, int attack, int defence) {
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		played = 0;
		won = 0;
		drawn = 0;
		lost = 0;
		scored = 0;
		conceded = 0;
		pointsDifference = scored = conceded;
		points = 2 * won + drawn;
	}

	public String getName() {
		return name;
	}

	public int getPlayed() {
		return played;
	}

	public int getWon() {
		return won;
	}

	public int getDrawn() {
		return drawn;
	}

	public int getLost() {
		return lost;
	}

	public int getScored() {
		return scored;
	}

	public int getConceded() {
		return conceded;
	}

	public int getPointsDifference() {
		return pointsDifference;
	}

	public int getPoints() {
		return points;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}
	
}
