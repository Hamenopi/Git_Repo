package com.hamenopi.thecheese.level.tile;

import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public abstract class Tile {
	public int x, y;
	public Sprite sprite;
	protected boolean solid = false;
	
	public static Tile land = new GrassTile(Sprite.land);
	public static Tile water = new RockTile(Sprite.water);
	public static Tile dirt = new GrassTile(Sprite.dirt);
	public static Tile rock = new RockTile(Sprite.rock);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static final int iwater = 0xff0000ff;
	public static final int iland = 0xff00ff00;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int xTile, int yTile, Screen screen) {
	}
	
	public boolean solid() {
		return solid;
	}
}
