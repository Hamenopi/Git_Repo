package com.hamenopi.thecheese.entity.particle;

import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class Particle extends Entity {
	protected Sprite sprite;
	protected int life;
	protected int time = 0;
	protected double xa, ya, za;// E77
	protected double xx, yy, zz;// E80
			
	public Particle(int x, int y, int life) {
		this(x, y, life, Sprite.particle_normal);
	}
	
	public Particle(int x, int y, int life, Sprite sprite) {
		this.setX(x);
		this.setY(y);
		xx = x;
		yy = y;
		this.life = (life >> 1) + random.nextInt(life >> 1);
		this.sprite = sprite;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextGaussian();
	}
	
	public void update() {
				time = (time + 1) % 7500;
		if (time > life) remove();
		za -= 0.1;// Gravity
		if (zz < -.5) {// Bounce
			zz = 0;
			za *= -0.8;
			ya *= 0.5;
			xa *= 0.5;
		}
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	public void render(Screen screen) {// E77
		screen.renderSprite((int) xx - 1, (int) yy - (int) zz, sprite, true);
	}

	protected void move(double x, double y) {
		if (collision(x, y)) {
			xa *= -0.5;
			ya *= -0.5;
			za *= -0.5;
		}
		xx += xa;
		yy += ya;
		zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 32) / 32;
			double yt = (y - c / 2 * 32) / 32;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
}
