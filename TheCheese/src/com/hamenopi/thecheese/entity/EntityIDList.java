package com.hamenopi.thecheese.entity;

public enum EntityIDList {
	PLAYER,
	MATHENTITY,
	MOB,
	HOUSE,
	DOOR,
	WINDOW,
	WALL;
	
	// More WIP, using simple method atm.
	private String name;
	private int id;
	
	private EntityIDList(){
		
	}
	private EntityIDList(String name, int id){
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	
}
