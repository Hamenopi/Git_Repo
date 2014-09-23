package com.hamenopi.thecheese.level.tile;

import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.graphics.SpriteSheet;

public class WaterTile extends AnimatedTile {
	
	private static final AnimatedSprite water = new AnimatedSprite(32, SpriteSheet.waterAnim, 3);
	
	public WaterTile() {
		super(water);
		
	}
	
}
