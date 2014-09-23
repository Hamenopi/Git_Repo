package com.hamenopi.thecheese.entity.particle;

import com.hamenopi.thecheese.graphics.Sprite;

public class DefaultParticle extends Particle {
	

	public DefaultParticle(int x, int y, int life) {
		super(x, y, life, Sprite.particle_normal);

	}

	public DefaultParticle(int x, int y, int life, Sprite sprite) {
		super(x, y, life, sprite);
		xx = x;
		yy = y;
		this.life = (life >> 1) + random.nextInt(life >> 1);
		
		this.xa = random.nextGaussian() ;
		this.ya = random.nextGaussian() ;
		this.zz = random.nextGaussian() ;
	}
	
}
