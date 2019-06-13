package com.vinegrad.endofseason;

import java.util.List;
import java.util.stream.Collectors;

import com.vinegrad.model.League;
import com.vinegrad.model.Team;
import com.vinegrad.updates.TeamWriter;
import com.vinegrad.updates.Writer;

public class FinalWriteTeams {

	public void writeAllTeams(List<Team> allTeams) {
		
		Writer teamWriter = new TeamWriter();
		
		for(League league : League.values()) {
			List<Team> teams = allTeams.stream()
					.filter(team -> team.getLeague().equals(league))
					.collect(Collectors.toList());
			teamWriter.writeTeams(teams);
		}
		
	}
}
