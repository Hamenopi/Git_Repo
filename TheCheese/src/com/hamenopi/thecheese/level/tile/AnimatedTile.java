package com.hamenopi.thecheese.level.tile;

import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.level.Level;

public class AnimatedTile extends Tile{
	
	private Sprite sprite;
	private AnimatedSprite animSprite;
	private Level level;

	public AnimatedTile(AnimatedSprite sprite) {
		super(sprite);
		animSprite=sprite;
		//level().addTile(this);
		

	}
	
	public void update(){
		animSprite.update();
	}
	
	public void render(int x, int y, Screen screen){
		sprite = animSprite.getSprite();
		screen.renderSprite(x<<5, y<<5, sprite);
	}
	
}
