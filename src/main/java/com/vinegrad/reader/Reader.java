package com.vinegrad.reader;

import java.util.List;

import com.vinegrad.model.League;
import com.vinegrad.model.Team;

public interface Reader {

	List<Team> getTeams(League league);
}
