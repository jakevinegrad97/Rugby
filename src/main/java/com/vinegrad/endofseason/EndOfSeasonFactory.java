package com.vinegrad.endofseason;

import com.vinegrad.model.League;

public abstract class EndOfSeasonFactory {

	public static EndOfSeason getEndOfSeasonSimulator(League league) {
		return new EndOfSeasonImpl();
	}
}
