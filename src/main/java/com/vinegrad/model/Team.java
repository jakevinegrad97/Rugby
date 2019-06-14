package com.vinegrad.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Team {

	private String name;
	private int played, won, drawn, lost, scored, conceded, pointsDifference, points, place;
	private double attack, defence;
	private League league;
	private List<Integer> form;
	private List<Integer> last8;
	private boolean backOnRightTrack;
	private League initialLeague;

	{
		backOnRightTrack = true;
		form = new ArrayList<>();
		last8 = new ArrayList<>();
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
	
	public Team(String name, double attack, double defence, League league, League initialLeague) {
		this.name = name;
		this.attack = attack;
		this.defence = defence;
		this.league = league;
		this.initialLeague = initialLeague;
	}
	
	public League getInitialLeague() {
		return initialLeague;
	}
	
	public int getForm() {
		return form.stream().mapToInt(i -> i).sum();
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
		return name + " : " + (int) Math.round(attack) + " : " + (int) Math.round(defence) + " : " + initialLeague.getInitials();
	}

	public String tableFormat() {
		String tab = "";
		for (int i = 0; i < 32 - name.length(); i++) {
			tab += " ";
		}
		return place + ". " + name + "  " + tab + played + "   " + won + "   " + drawn + "   " + lost + "   " + scored + "   "
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
		defence += pointsConceded <= 24 ? 2 * Math.random() * (24 - pointsConceded) / 100 : - 2 * Math.random() * (pointsConceded - 24) / 100;
		attack += pointsScored >= 24 ? 2 * Math.random() * (pointsScored - 24) / 100 : -2 * Math.random() * (24 - pointsScored) / 100;
		
		int pointsForResult = 0;
		if(pointsScored > pointsConceded) {
			won++;
			pointsForResult = 2;
			attack += weight;
			defence += weight;
		}
		else if (pointsScored < pointsConceded) {
			lost++;
			pointsForResult = 0;
			attack -= weight;
			defence -= weight;
		}
		else {
			drawn++;
			pointsForResult = 1;
		}
		points += pointsForResult;
		
		if(form.size() < 5)
			form.add(pointsForResult);
		else {
			form.remove(0);
			form.add(pointsForResult);
		}
		if(last8.size() < 8)
			last8.add(pointsForResult);
		else {
			last8.remove(0);
			last8.add(pointsForResult);
		}
		
		if(played >= 8)
			checkTerribleForm();
		
		attack = check(attack);
		defence = check(defence);
		
	}
	
	public void promote() {
		switch(league) {
		case SUPER_LEAGUE :
			break;
		case CHAMPIONSHIP :
			league = League.SUPER_LEAGUE;
			break;
		}
	}
	
	public void relegate() {
		switch(league) {
		case SUPER_LEAGUE :
			league = League.CHAMPIONSHIP;
			break;
		case CHAMPIONSHIP :
			break;
		}
	}
	
	private double check(double input) {
		double leagueWeightHigh = 10 * (2 - initialLeague.getTier());
		double leagueWeightLow = 10 * (initialLeague.getTier() - 1);
		switch(league) {
		case SUPER_LEAGUE :
			if(input > 100 - leagueWeightLow)
				input = 100 - leagueWeightLow;
			if(input < 60 - leagueWeightLow)
				input = 60 - leagueWeightLow;
			break;
		case CHAMPIONSHIP :
			if(input > 70 + leagueWeightHigh)
				input = 70 + leagueWeightHigh;
			if(input < 40 + leagueWeightHigh)
				input = 40 + leagueWeightHigh;
			break;
		}
		return input;
	}
	
	public static final Logger LOGGER = Logger.getLogger(Team.class);

	private void checkTerribleForm() {
		if(last8.stream().mapToInt(i -> i).sum() == 0) {
			LOGGER.info("Lost last 8: " + name);
			backOnRightTrack = false;
			form.add(3);
		} 
		if(!backOnRightTrack) {
			if(last8.stream().mapToInt(i -> i).sum() > 0) {
				LOGGER.info("Finally won: " + name);
				form = new ArrayList<>();
				form.add(3);
				backOnRightTrack = true;
			}
		}
	}
	
	

}
