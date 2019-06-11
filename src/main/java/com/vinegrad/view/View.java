package com.vinegrad.view;

import com.vinegrad.model.Fixture;
import com.vinegrad.model.Team;

public class View {

	public static void main(String[] args) {
		Team hull = new Team("Hull FC", 80, 60);
		Team rovers = new Team("Hull KR", 75, 65);
		System.out.println(hull);
		System.out.println(rovers);
		
		Fixture hullDerby = new Fixture(hull, rovers);
		System.out.println(hullDerby.beforeMatch());
		hullDerby.addResult(36, 6);
		System.out.println(hullDerby);
	}

}
