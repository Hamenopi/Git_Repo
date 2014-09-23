package com.hamenopi.thecheese.graphics;

import java.util.Arrays;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.projectile.Projectile;
import com.hamenopi.thecheese.level.tile.Tile;

public class Screen {
	public int width, height;
	public int xOffset, yOffset;
	public int[] pixels;
	
	public final int MAP_SIZE = 64;
	public final int MAP_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	public final int TRANSPARENT_COLOR = 0xffff00ff;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void clear(int color) {
		Arrays.fill(pixels, color);
	}
	
	public void renderSheet(int xPos, int yPos, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		for (int y = 0, sHeight = sheet.SPRITE_HEIGHT; y < sHeight; y++) {
			int yA = y + yPos;
			for (int x = 0, sWidth = sheet.SPRITE_WIDTH; x < sWidth; x++) {
				int xA = x + xPos;
				if (yA < -sHeight || yA >= height) continue;
				if (xA < -sWidth || xA >= width) continue;
				if (xA < 0) xA = 0;
				if (yA < 0) yA = 0;
				int col = sheet.pixels[x + y * sWidth];
				if (TRANSPARENT_COLOR != col) pixels[xA + yA * width] = col;
			}
		}
	}
	
	public void renderSprite(int xPos, int yPos, Sprite sprite) {
		renderSprite(xPos, yPos, sprite, true);
	}
	
	public void renderSprite(int xPos, int yPos, Sprite sprite, boolean fixed) {
		if (fixed) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		for (int y = 0, spriteHeight = sprite.getHeight(); y < spriteHeight; y++) {
			int yA = y + yPos;
			for (int x = 0, spriteWidth = sprite.getWidth(); x < spriteWidth; x++) {
				int xA = x + xPos;
				if (yA < 0 || yA >= height || xA < 0 || xA >= width) continue;
				// if (xA < 0) xA = 0;
				// if (yA < 0) yA = 0;
				int col = sprite.pixels[x + y * spriteWidth];
				if (col != TRANSPARENT_COLOR) pixels[xA + yA * width] = col;
			}
		}
	}
	
	public void renderSprite(int xPos, int yPos, Sprite sprite, int color, boolean fixed) {
		if (fixed) {
			xPos -= xOffset;
			yPos -= yOffset;
		}
		for (int y = 0, spriteHeight = sprite.getHeight(); y < spriteHeight; y++) {
			int yA = y + yPos;
			for (int x = 0, spriteWidth = sprite.getWidth(); x < spriteWidth; x++) {
				int xA = x + xPos;
				if (yA < 0 || yA >= height || xA < 0 || xA >= width) continue;
				// if (xA < 0) xA = 0;
				// if (yA < 0) yA = 0;
				int col = sprite.pixels[x + y * spriteWidth];
				if (col != TRANSPARENT_COLOR) pixels[xA + yA * width] = color;
			}
		}
	}
	
	public void renderTile(int xPos, int yPos, Tile tile) {
		xPos -= xOffset;
		yPos -= yOffset;
		for (int y = 0, tileHeight = tile.sprite.getHeight(); y < tileHeight; y++) {
			int yA = y + yPos;
			for (int x = 0, tileWidth = tile.sprite.getWidth(); x < tileWidth; x++) {
				int xA = x + xPos;
				if (yA < -tileHeight || yA >= height || xA < -tileWidth || xA >= width) continue;
				if (xA < 0) xA = 0;
				if (yA < 0) yA = 0;
				int col = tile.sprite.pixels[x + y * tileWidth];
				if (col != TRANSPARENT_COLOR) pixels[xA + yA * width] = col;
			}
		}
	}
	
	public void renderMob(int xPos, int yPos, Sprite sprite) {
		renderSprite(xPos, yPos, sprite, true);
		/*
		 * xPos -= xOffset; yPos -= yOffset; for (int y = 0, spriteHeight = sprite.SPRITE_HEIGHT; y < spriteHeight; y++) { int yA = y + yPos; for (int x = 0, spriteWidth = sprite.SPRITE_WIDTH; x < spriteWidth; x++) { int xA = x + xPos; if (yA < -spriteHeight || yA >= height||xA < -spriteWidth || xA >= width) continue; if (xA < 0) xA = 0; if (yA < 0) yA = 0; int col = sprite.pixels[x + y * spriteWidth]; if (col != 0xffff00ff) pixels[xA + yA * width] = col; } }
		 */
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		int spriteSize = p.getSpriteSize();
		for (int y = 0; y < spriteSize; y++) {
			int ya = yp + y;
			for (int x = 0; x < spriteSize; x++) {
				int xa = xp + x;
				if (ya < 0 || ya >= height || xa < -spriteSize || xa >= width) break;
				if (xa < 0) xa = 0;
				if (ya < 0) ya = 0;
				int col = p.getSprite().pixels[x + y * spriteSize];
				if (col != TRANSPARENT_COLOR) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void drawRect(int xp, int yp, int width, int height, int color, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		int x1 = xp + width;
		for (int x = xp; x < x1; x++) {
			if (x < 0 || x >= this.width || yp >= this.height) continue;
			if (yp > 0) pixels[x + yp * this.width] = color;
			if (yp + height >= this.height) continue;
			if (yp + height > 0) pixels[x + (yp + height) * this.width] = color;
		}
		int y1 = yp + height;
		for (int y = yp; y <= y1; y++) {
			if (xp >= this.width || y < 0 || y >= this.height) continue;
			if (xp > 0) pixels[xp + y * this.width] = color;
			if (xp + width >= this.width) continue;
			if (xp + width > 0) pixels[(xp + width) + y * this.width] = color;
		}
	}
	
	/*
	 * public void drawUIBorder(int left, int top, int width, int height) { int right = left + width; // Let's test x int bottom = top + height; for (int x = left; x < right; x++) { if (x < 1 || x >= Game.getWindowWidth() - 1) continue; pixels[x + top * this.width] = 0xffffffff; pixels[x + bottom * this.width] = 0xffffffff; } // Oh! for (int y = top; y < bottom; y++) { if (y < 1 || y >= Game.getWindowHeight() - 1) continue; pixels[left + y * this.width] = 0xffffffff; pixels[right + y * this.width] = 0xffffffff; } } public void drawUIInside(int left, int top, int width, int height) { int right = left + width; // Let's test x int bottom = top + height; for (int x = left; x < right; x++) { if (x < 1 || x >= Game.getWindowWidth() - 1) continue; for (int y = top; y < bottom; y++) { if (y < 1 ||
	 * y >= Game.getWindowHeight() - 1) continue; pixels[x+y*this.width]= 0x0; } } // Oh! for (int y = top; y < bottom; y++) { if (y < 1 || y >= Game.getWindowHeight() - 1) continue; pixels[left + y * this.width] = 0xffffffff; pixels[right + y * this.width] = 0xffffffff; } }
	 */
	
	public void uirender(String string) {
		Font font = new Font();
		int xl = (Game.getWindowWidth() >> 1) - 16;
		int y = 250;// /TODO Y loop
		
		renderSprite(0, y, Sprite.bUItl, false);
		renderSprite(0, y + 16, Sprite.bUIml, false);
		renderSprite(0, y + 32, Sprite.bUIml, false);
		renderSprite(0, y + 48, Sprite.bUIml, false);
		renderSprite(0, y + 64, Sprite.bUIbl, false);
		
		renderSprite(xl, y, Sprite.bUItr, false);
		renderSprite(xl, y + 16, Sprite.bUImr, false);
		renderSprite(xl, y + 32, Sprite.bUImr, false);
		renderSprite(xl, y + 48, Sprite.bUImr, false);
		renderSprite(xl, y + 64, Sprite.bUIbr, false);
		
		for (int i = 16; i < xl; i += 16) {
			renderSprite(i, y, Sprite.bUItc, false);
			renderSprite(i, y + 16, Sprite.bUImc, false);
			renderSprite(i, y + 32, Sprite.bUImc, false);
			renderSprite(i, y + 48, Sprite.bUImc, false);
			renderSprite(i, y + 64, Sprite.bUIbc, false);
			
		}
		font.render(0 - 8, y + 8, string, this);
	}
}
