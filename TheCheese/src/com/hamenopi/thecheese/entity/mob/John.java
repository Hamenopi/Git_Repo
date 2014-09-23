package com.hamenopi.thecheese.entity.mob;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.EntityIDList;
import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.graphics.SpriteSheet;
import com.hamenopi.thecheese.util.Debug;

public class John extends Mob {
	private AnimatedSprite down = new AnimatedSprite(32, SpriteSheet.jSouth, 1);
	
	@SuppressWarnings("unused")
	private AnimatedSprite up = new AnimatedSprite(32, SpriteSheet.jNorth, 1);
	@SuppressWarnings("unused")
	private AnimatedSprite left = new AnimatedSprite(32, SpriteSheet.jWest, 1);
	@SuppressWarnings("unused")
	private AnimatedSprite right = new AnimatedSprite(32, SpriteSheet.jEast, 1);
	
	private AnimatedSprite animSprite = down;
	
	// Constructor
	public John(int x, int y) {
		this.setX(x << Game.getBitShift());
		this.setY(y << Game.getBitShift());
		this.width = animSprite.SPRITE_WIDTH;
		this.height = animSprite.SPRITE_HEIGHT;
		initMob(0.5, down, 10, 1, 1, 1, 1, EntityIDList.MOB);
	}
	
	public John(int x, int y, Sprite andy) {
		this(x,y);
		sprite = andy;
		
	}
	
	@Override
	public void update() {
		updateAction();
		//resetMove();
		wander(200, 200);
		//updateAnimation();
		move(xa,ya);
	}
	
	@Override
	public void render(Screen screen) {
		Debug.drawRect(screen, (int) getX(), (int) getY(), width, height, true);
		
		sprite = animSprite.getSprite();
		screen.renderMob((int) getX(), (int) getY(), sprite);
	}
	
}
