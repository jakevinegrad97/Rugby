package com.vinegrad.fixtures;

import java.util.List;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;

public interface Simulator {

	List<Team> simulateFixtures(List<Fixture> fixtures, int round);
	
}
