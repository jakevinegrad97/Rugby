package com.vinegrad.endofseason;

import com.vinegrad.model.League;

public abstract class EndOfSeasonFactory {

	public static EndOfSeason getEndOfSeasonSimulator(League league) {
		switch(league) {
		case SUPER_LEAGUE :
			return new SLEndOfSeason();
		default :
			return null;
		}
	}
}
