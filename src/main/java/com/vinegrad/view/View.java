package com.vinegrad.view;

import java.util.List;

import com.vinegrad.model.League;
import com.vinegrad.model.Team;
import com.vinegrad.reader.Reader;
import com.vinegrad.reader.TeamReader;

public class View {

	public static void main(String[] args) {
		Reader teamReader = new TeamReader();
		List<Team> superLeague = teamReader.getTeams(League.SUPER_LEAGUE);
		superLeague.forEach(System.out::println);
	}

}
