package com.hamenopi.thecheese.level;

import com.hamenopi.thecheese.Game;

public class TileCoordinate {
	
	private int x, y;
	private final int TILE_SIZE = 32;
	
	public TileCoordinate(int x, int y) {
		this.x = x << Game.getBitShift();
		this.y = y << Game.getBitShift();
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public int[] xy() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}
	
}
