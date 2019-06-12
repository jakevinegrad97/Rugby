package com.vinegrad.model;

public class Team {

	private String name;
	private int played, won, drawn, lost, scored, conceded, pointsDifference, points, place;
	private double attack, defence;
	private League league;

	public Team(String name, double attack, double defence, League league) {
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		this.league = league;
		played = 0;
		won = 0;
		drawn = 0;
		lost = 0;
		scored = 0;
		conceded = 0;
		pointsDifference = scored = conceded;
		points = 2 * won + drawn;
		place = 0;
	}
	
	public int getPlace() {
		return place;
	}
	
	public void setPlace(int place) {
		this.place = place;
	}
	
	public League getLeague() {
		return league;
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
		return name + " : " + (int) Math.round(attack) + " : " + (int) Math.round(defence);
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

	public void addResult(int pointsScored, int pointsConceded) {
		double weight = Math.random() * 0.2;
		played++;
		scored += pointsScored;
		conceded += pointsConceded;
		pointsDifference = pointsDifference + pointsScored - pointsConceded;
		defence += pointsConceded < 18 ? 3 * Math.random() * (18 - pointsConceded) / 100 : - 3 * Math.random() * (pointsConceded - 18) / 100;
		attack += pointsScored >= 18 ? 3 * Math.random() * (pointsScored - 18) / 100 : -3 * Math.random() * (18 - pointsScored) / 100;
		if(pointsScored > pointsConceded) {
			won++;
			points += 2;
			attack += weight;
			defence += weight;
		}
		else if (pointsScored < pointsConceded) {
			lost++;
			attack -= weight;
			defence -= weight;
		}
		else {
			drawn++;
			points++;
		}	
	}
	
	

}
