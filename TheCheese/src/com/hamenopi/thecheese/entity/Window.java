package com.hamenopi.thecheese.entity;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.util.Debug;

public class Window extends Entity{
	private boolean open = false;
	
	public Window (int x, int y){
		this.setX(x << Game.getBitShift());
		this.setY(y << Game.getBitShift());
		sprite=null;
		this.width = 32;
		this.height = 32;
		id = EntityIDList.WINDOW;
		
	}
	
	@Override
	public void render(Screen screen) {
		Debug.drawRect(screen, (int) getX(), (int) getY(), width, height, true);
		
	}
}
