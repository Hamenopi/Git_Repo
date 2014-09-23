package com.hamenopi.thecheese.entity.spawner;

import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.level.Level;

public abstract class Spawner extends Entity {
	
	public enum Type {
		PARTICLE, MOB;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.setX(x);
		this.setY(y);
		this.type = type;
		
	}
}
