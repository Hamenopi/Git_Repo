package com.hamenopi.thecheese.entity.mob;

import com.hamenopi.thecheese.entity.EntityIDList;
import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.graphics.SpriteSheet;
import com.hamenopi.thecheese.input.Keyboard;
import com.hamenopi.thecheese.util.Debug;

public class Player extends Mob {
	
	private Keyboard input;
	
	public Player(Keyboard input) {
		down = new AnimatedSprite(32, SpriteSheet.eSouth, 1);
		up = new AnimatedSprite(32, SpriteSheet.eSouth, 1);
		right = new AnimatedSprite(32, SpriteSheet.eSouth, 1);
		left = new AnimatedSprite(32, SpriteSheet.eSouth, 1);
		animSprite = down;
		
		this.input = input;
		initMob(1, (Sprite) down, 10, 1, 1, 0, 0, EntityIDList.PLAYER, false);
		height = animSprite.SPRITE_HEIGHT;
		width = animSprite.SPRITE_WIDTH;
		setFrameRate();
	}
	
	public Player(int x, int y, Keyboard input) {
		this(input);
		this.setX(x);
		this.setY(y);
	}
	
	public void update() {
		
		updateAction();
		// anim = (anim + 1) % 7500;
		resetMove();
		getInput();
		updateAnimation();
		move(xa, ya);
		checkProjectiles();
	}
	
	
	
	

	private void getInput() {
		if (input.up) {
			ya -= speed;
		} else if (input.down) {
			ya += speed;
		}
		if (input.left) {
			xa -= speed;
		} else if (input.right) {
			xa += speed;
		}
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		Debug.drawRect(screen, (int) getX(), (int) getY(), width, height, true);
		screen.renderMob((int) getX(), (int) getY(), sprite);
		// screen.uirender("UI");
	}
}
