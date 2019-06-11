package com.vinegrad.model;

public class Team {

	private String name;
	private int played, won, drawn, lost, scored, conceded, pointsDifference, points;
	private double attack, defence;

	public Team(String name, double attack, double defence) {
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

	public double getAttack() {
		return attack;
	}

	public double getDefence() {
		return defence;
	}

	public String toString() {
		return name + " : " + (int) attack + " : " + (int) defence;
	}

	public String tableFormat() {
		String tab = "";
		for (int i = 0; i < 32 - name.length(); i++) {
			tab += " ";
		}
		return name + " : " + tab + played + "   " + won + "   " + drawn + "   " + lost + "   " + scored + "   "
				+ conceded + "   " + pointsDifference + "   " + points + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Team other = (Team) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
