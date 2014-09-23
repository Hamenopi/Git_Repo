package com.hamenopi.thecheese.level.tile;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class GrassTile extends Tile {
	
	public GrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int xTile, int yTile, Screen screen) {
		screen.renderTile((xTile<< Game.getBitShift()), (yTile<< Game.getBitShift()), this);
	}
}
