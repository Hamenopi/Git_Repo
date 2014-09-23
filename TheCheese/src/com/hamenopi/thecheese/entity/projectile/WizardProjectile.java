package com.hamenopi.thecheese.entity.projectile;

import com.hamenopi.thecheese.entity.spawner.ParticleSpawner;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class WizardProjectile extends Projectile {
	
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 6*32;
		damage = 20;
		speed = 5;
		fireRate = 16;
		sprite = Sprite.spell1;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 8, 0, 0)) {// E77
			level.add(new ParticleSpawner((int) x, (int) y, 100, 100, level, (new Sprite(3, (int) (Math.random() * 0x1000000)))));
			remove();
		}
		move();
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int) x, (int) y, this);
	}

	
}
