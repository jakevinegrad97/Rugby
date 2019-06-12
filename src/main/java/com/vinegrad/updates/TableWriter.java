package com.vinegrad.updates;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.vinegrad.model.Team;

public class TableWriter implements Writer {

	@Override
	public void writeTeams(List<Team> teams) {
		String league = teams.get(0).getLeague().getInitials();
		try(var fw = new FileWriter("src/main/resources/tables/" + league + ".txt"); var bw = new BufferedWriter(fw)) {
			bw.write("#									    P   W   D   L   F   A   D   P\n");
			teams = teams.stream()
					.sorted(Comparator.comparing(Team::getPoints).reversed()
							.thenComparing(Comparator.comparing(Team::getPointsDifference).reversed())
							.thenComparing(Comparator.comparing(Team::getScored).reversed())
							.thenComparing(Comparator.comparing(Team::getWon).reversed())
							.thenComparing(Comparator.comparing(Team::getName)))
					.collect(Collectors.toList());
			for (int place = 1; place <= teams.size(); place++) {
				Team team = teams.get(place - 1);
				team.setPlace(place);
				bw.write(team.tableFormat());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
