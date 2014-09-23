package com.hamenopi.thecheese.level;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.util.Debug;

public class TestDoor extends Entity {
	//private AnimatedSprite down = new AnimatedSprite(32, SpriteSheet.jSouth, 1);
	//private AnimatedSprite animSprite = down;
	private Sprite sprite = Sprite.doorClosed;
	
	public TestDoor(int x, int y) {
		this.setX(x << Game.getBitShift());
		this.setY(y << Game.getBitShift());
		
	}
	
public void update() {
		
	}
	
	public void render(Screen screen) {
		Debug.drawRect(screen, (int) getX(), (int) getY(), 32, 32, true);
		
		//sprite = animSprite.getSprite();
		screen.renderMob((int) getX(), (int) getY(), sprite);
	}


}
