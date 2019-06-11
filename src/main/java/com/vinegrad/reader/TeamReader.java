package com.vinegrad.reader;

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
					int attack = Integer.valueOf(components[1].trim());
					int defence = Integer.valueOf(components[2].trim());
					return new Team(name, attack, defence);
				})
				.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return teams;
	}

}
