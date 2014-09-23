package com.hamenopi.thecheese.level.tile;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class RockTile extends Tile {
	
	public RockTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void render(int xTile, int yTile, Screen screen) {
		screen.renderTile((xTile << Game.getBitShift()), (yTile << Game.getBitShift()), this);
	}
	
	public boolean solid() {
		return true;
	}
}
