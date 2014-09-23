package com.hamenopi.thecheese.entity.projectile;

import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public abstract class Projectile extends Entity {
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double x, y;
	protected double speed, damage, range, distance, fireRate;
	
	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	public int getSpriteSize() {
		return sprite.SPRITE_WIDTH;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}
}
