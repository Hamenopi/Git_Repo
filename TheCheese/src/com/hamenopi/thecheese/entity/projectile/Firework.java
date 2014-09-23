package com.hamenopi.thecheese.entity.projectile;

import com.hamenopi.thecheese.entity.spawner.ParticleSpawner;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class Firework extends Projectile {
	public static final int FIRE_RATE = 55;// Fireworks are slower
	private double zz;
	private int time = 0;
	
	public Firework(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		damage = 0;
		speed = 2;
		sprite = Sprite.particle_normal;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public Firework(int x, int y, double dir, Sprite sprite) {
		this(x, y, dir);
		this.sprite = sprite;
	}
	
	public void update() {
		time = (time + 1) % 500;
		
		if (time * 2 > range) {
			level.add(new ParticleSpawner((int) x, (int) y, 100, 500, level, (new Sprite(3, random.nextInt(0xffffff)))));
			remove();
		}
		move();
	}
	
	protected void move() {
		zz -= 0.02;
		x += nx;
		y += ny;
		y -= zz;
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, this);
	}
}
