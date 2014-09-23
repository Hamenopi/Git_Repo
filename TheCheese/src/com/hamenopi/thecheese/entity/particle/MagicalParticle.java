package com.hamenopi.thecheese.entity.particle;

import com.hamenopi.thecheese.graphics.Sprite;

public class MagicalParticle extends Particle {
	
	public MagicalParticle(int x, int y, int life) {
		super(x, y, life, Sprite.particle_normal);
	}
	
	public MagicalParticle(int x, int y, int life, Sprite sprite) {
		super(x, y, life, sprite);
		xx = x;
		yy = y;
		this.life = (life >> 1) + random.nextInt(life >> 2);
		
		this.xa = random.nextGaussian() * 2;
		this.ya = random.nextGaussian() * 2;
		this.zz = random.nextGaussian() * 2;
	}
}
