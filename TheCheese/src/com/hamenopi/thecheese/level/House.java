package com.hamenopi.thecheese.level;

import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.entity.EntityIDList;
import com.hamenopi.thecheese.entity.Window;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class House extends Entity {
	boolean locked = false;
	boolean open = false;
	private Door door;
	//private Window window;
	
	// Constructors
	public House(int x, int y) {
		initEntity(x - 1, y - 2, Sprite.house1, EntityIDList.HOUSE); // << -- ?? !!
	}
	
	public House(int x, int y, boolean door) {
		this(x, y);
		if (door) this.door = new Door(x, y);
	}
	
	public House(int x, int y, int dx, int dy) {
		this(x, y);
		this.door = new Door(x, y, dx, dy);
	}
	
	public House(int x, int y, int level, int dx, int dy) {
		this(x, y);
		this.door = new Door(x, y, level, dx, dy); // << -- ?? !!
		}
	
	// Methods
	public void update() {
		if (door != null) door.update();
	}
	
	public void render(Screen screen) {
		// Debug.drawRect(screen, (int) x, (int) y, width, height, true);
		screen.renderMob((int) getX(), (int) getY(), sprite);
		if (door != null) door.render(screen);
	}
	
	
	public Door getDoor() {
		return door;
	}
}