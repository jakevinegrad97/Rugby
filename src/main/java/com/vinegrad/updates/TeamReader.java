package com.vinegrad.updates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.vinegrad.model.League;
import com.vinegrad.model.Team;

public class TeamReader implements Reader {

	@Override
	public List<Team> getTeams(League league) {

		List<Team> teams = null;
		
		try {
			teams = 
				Files.lines(Paths.get("src/main/resources/teams/" + league.getInitials() + ".txt")).map(line -> {
					String[] components = line.split(":");
					String name = components[0].trim();
					double attack = Double.valueOf(components[1].trim());
					double defence = Double.valueOf(components[2].trim());
					League initialLeague = League.of(components[3].trim());
					return new Team(name, attack, defence, league, initialLeague);
				})
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return teams;
	}

}
