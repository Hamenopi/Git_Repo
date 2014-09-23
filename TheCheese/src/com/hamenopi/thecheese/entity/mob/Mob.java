package com.hamenopi.thecheese.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.entity.EntityIDList;
import com.hamenopi.thecheese.entity.projectile.Projectile;
import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.Font;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.graphics.SpriteSheet;

public abstract class Mob extends Entity {
	
	protected Font font;
	protected int direction = 0;
	protected boolean walking = false;
	protected boolean hostile = false;
	protected double speed = 1;
	protected int anim = 0;
	protected boolean collision;
	protected int hp, attack, defense, exp, gold;
	protected int time = 0;
	protected int fireRate = 0;
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	};
	
	protected Direction dir;
	
	protected AnimatedSprite down;
	protected AnimatedSprite up;
	protected AnimatedSprite left;
	protected AnimatedSprite right;
	protected AnimatedSprite animSprite;
	
	protected List<Projectile> projectiles = new ArrayList<>();
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	protected void initMob(double speed, Sprite sprite, int hp, int attack, int defense, int exp, int gold, EntityIDList id) {
		initMob(speed, sprite, hp, attack, defense, exp, gold, id, false);
	}
	
	protected void initMob(double speed, Sprite sprite, int hp, int attack, int defense, int exp, int gold, EntityIDList id, boolean hostile) {
		this.speed = speed;
		this.sprite = sprite;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.exp = exp;
		this.gold = gold;
		this.id = id;
		this.hostile = hostile;
	}
	
	protected void move(double xa, double ya) {
		if (xa != 0 || ya != 0) {
			Entity ent = entityCollsision(xa, ya);
			if (ent != null) {
				
				double mod = interact(ent);
				xa *= mod;
				ya *= mod;
			}
		}
		
		if (xa != 0 && ya != 0) {
			
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!tileCollision(convert(xa), 0)) {
					setX(getX() + convert(xa));
				}
				xa -= convert(xa);
			} else {
				if (!tileCollision(convert(xa), 0)) {
					setX(getX() + xa);
				}
				xa = 0;
			}
		}
		
		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!tileCollision(0, convert(ya))) {
					this.setY(this.getY() + convert(ya));
				}
				ya -= convert(ya);
			} else {
				if (!tileCollision(0, convert(ya))) {
					this.setY(this.getY() + ya);
				}
				ya = 0;
			}
		}
	}
	
	
	
	protected int convert(double value) {
		return value < 0 ? -1 : 1;
	}
	
	protected void wander(int oftStop, int randStop) {
		time++;
		if (time % (60) == 0) {
			xa = (random.nextInt(3) - 1) * speed;
			ya = (random.nextInt(3) - 1) * speed;
			
			if (random.nextInt(oftStop) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		if (random.nextInt(randStop) == 0) {
			xa = 0;
			ya = 0;
		}
	}
	
	protected void checkProjectiles() {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isRemoved()) projectiles.remove(i);
		}
	}
	
	/*
	 * protected void shootWP(int x, int y, double dir) { Projectile p = new WizardProjectile(x, y, dir); level.add(p); } protected void shootFW(int x, int y, double dir) { Projectile p = new Firework(x, y, dir); level.add(p); }
	 */
	
	/*
	 * protected void updateShooting() { if (Mouse.getButton() == 1 && fireRate <= 0) { double dx = Mouse.getX() - 32 - Game.getWindowWidth() / 2; double dy = Mouse.getY() - 32 - Game.getWindowHeight() / 2; double dir = Math.atan2(dy, dx); shootFW((int) (x + 16), (int) (y + 16), dir); fireRate = Firework.FIRE_RATE; } }
	 */
	
	public int getHP() {
		return hp;
	}
	
	public int getAtk() {
		return attack;
	}
	
	public int getDef() {
		return defense;
	}
	
	public boolean getHostile() {
		return hostile;
	}
	
	public void setHP(int hp) {
		this.hp = hp;
	}
	
	protected void updateAnimation() {
		if (xa != 0 || ya != 0) {
			anim();
			
		} else animSprite.setFrame(0);
	}
	
	protected void updateAction() {
		if (action > 0) action--;
		
	}
	
	protected void resetMove() {
		xa = 0;
		ya = 0;
	}
	
	protected void anim() {
		if (ya < 0) {
			animSprite = up;
		} else if (ya > 0) {
			animSprite = down;
		}
		
		if (xa < 0) {
			animSprite = left;
		} else if (xa > 0) {
			animSprite = right;
		}
		animSprite.update();
	}
	
	protected void setFrameRate() {
		animSprite.setFrameRate((int) (20 / speed));
	}
}
