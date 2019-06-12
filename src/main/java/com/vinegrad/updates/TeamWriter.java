package com.vinegrad.updates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.vinegrad.model.Team;

public class TeamWriter implements Writer {

	@Override
	public void writeTeams(List<Team> teams) {
		String league = teams.get(0).getLeague().getInitials();
		try(var fw = new FileWriter("src/main/resources/teams/" + league + ".txt"); var bw = new BufferedWriter(fw)){
			teams = teams.stream()
				.sorted(Comparator.comparing(Team::getName))
				.collect(Collectors.toList());
			for(Team team : teams) {
				bw.write(team.toString() + "\n");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
