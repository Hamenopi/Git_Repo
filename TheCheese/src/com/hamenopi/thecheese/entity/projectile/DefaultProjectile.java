package com.hamenopi.thecheese.entity.projectile;

import com.hamenopi.thecheese.entity.spawner.ParticleSpawner;
import com.hamenopi.thecheese.graphics.Sprite;

public class DefaultProjectile extends Projectile{

	public DefaultProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 5* 32;
		damage = 5;
		speed = 5;
		fireRate = 20;
		sprite = Sprite.particle_normal;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);

	}
	
	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 8, 0, 0)) {// E77
			level.add(new ParticleSpawner((int) x, (int) y, 100, 100, level, (new Sprite(3, 0x999999))));
			remove();
		}
		move();
	}
	
}
