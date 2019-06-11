package com.vinegrad.generator;

import java.util.List;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;

public interface Generator {

	List<Fixture> generateFixtures(List<Team> teams);
}
