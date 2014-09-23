package com.hamenopi.thecheese.entity.spawner;

import com.hamenopi.thecheese.entity.particle.DefaultParticle;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.level.Level;

public class ParticleSpawner extends Spawner {
	
	public ParticleSpawner(int x, int y, int life, int amount, Level level, Sprite sprite) {
		super(x, y, Type.PARTICLE, amount, level);
		for (int i = 0; i < amount; i++) {
			level.add(new DefaultParticle(x, y, life, sprite));
		}
	}
	
	@Override
	public void update() {
		
	}
}
