package com.hamenopi.thecheese.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.hamenopi.thecheese.Game;

public class SpriteSheet {
	private String path;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	private int sheetWidth, sheetHeight;
	public int[] pixels;
	
	public static SpriteSheet master = new SpriteSheet("/master.png", 320);
	public static SpriteSheet mouse = new SpriteSheet("/mousess.png", 256,32);
	public static SpriteSheet simple = new SpriteSheet("/island_simple 32 x 32.png", 64);
	public static SpriteSheet ground = new SpriteSheet("/ground.png", 320);
	public static SpriteSheet brownTileUI = new SpriteSheet("/tilebrown.png", 16 * 3);
	
	public static SpriteSheet spells = new SpriteSheet("/spells.png", 48);
	public static SpriteSheet playButton = new SpriteSheet("/playbutton.png", 96, 32);
	public static SpriteSheet quitButton = new SpriteSheet("/quitbutton.png", 96, 32);
	public static SpriteSheet backgroundImage = new SpriteSheet("/backgroundImage.png", Game.getWindowWidth(), Game.getWindowHeight());
	
	public static SpriteSheet waterAnim = new SpriteSheet(master, 2, 5, 3, 1, 32);
	
	public static SpriteSheet pSouth = new SpriteSheet(master, 0, 0, 3, 1, 32);
	public static SpriteSheet pEast = new SpriteSheet(master, 0, 1, 3, 1, 32);
	public static SpriteSheet pWest = new SpriteSheet(master, 0, 2, 3, 1, 32);
	public static SpriteSheet pNorth = new SpriteSheet(master, 0, 3, 3, 1, 32);
	
	public static SpriteSheet jSouth = new SpriteSheet(master, 5, 2, 1, 1, 32);
	public static SpriteSheet jEast = new SpriteSheet(master, 5, 2, 1, 1, 32);
	public static SpriteSheet jWest = new SpriteSheet(master, 5, 2, 1, 1, 32);
	public static SpriteSheet jNorth = new SpriteSheet(master, 5, 2, 1, 1, 32);
	
	public static SpriteSheet cSouth = new SpriteSheet(master, 5, 1, 1, 1, 32);
	public static SpriteSheet cEast = new SpriteSheet(master, 5, 1, 1, 1, 32);
	public static SpriteSheet cWest = new SpriteSheet(master, 5, 1, 1, 1, 32);
	public static SpriteSheet cNorth = new SpriteSheet(master, 5, 1, 1, 1, 32);
	
	public static SpriteSheet eSouth = new SpriteSheet(master, 5, 0, 1, 1, 32);
	public static SpriteSheet eEast = new SpriteSheet(master, 5, 0, 1, 1, 32);
	public static SpriteSheet eWest = new SpriteSheet(master, 5, 0, 1, 1, 32);
	public static SpriteSheet eNorth = new SpriteSheet(master, 5, 0, 1, 1, 32);
	
	public static SpriteSheet aSouth = new SpriteSheet(master, 5, 3, 1, 1, 32);
	public static SpriteSheet blizzSouth = new SpriteSheet(master, 5, 4, 1, 1, 32);
	public static SpriteSheet appleSouth = new SpriteSheet(master, 5, 5, 1, 1, 32);
	public static SpriteSheet mSouth= new SpriteSheet(mouse, 0, 0, 4, 1, 32);
	
	private Sprite[] sprites;
	
	//
	public SpriteSheet(String path, int size) {
		this(path, size, size);
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}
	
	//
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		this(sheet, x, y, width, height, spriteSize, spriteSize);
	}
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteWidth, int spriteHeight) {
		int xx = x * spriteWidth;
		int yy = y * spriteHeight;
		int w = width * spriteWidth;
		int h = height * spriteHeight;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				
				int[] spritePixels = new int[spriteHeight * spriteWidth];
				for (int y0 = 0; y0 < spriteHeight; y0++) {
					for (int x0 = 0; x0 < spriteWidth; x0++) {
						spritePixels[x0 + y0 * spriteWidth] = pixels[(x0 + xa * spriteWidth) + (y0 + ya * spriteHeight) * SPRITE_WIDTH];
						
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteWidth, spriteHeight);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	private void load() {
		try {
			System.out.println("Loading: " + path);
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			sheetWidth = image.getWidth();
			sheetHeight = image.getHeight();
			pixels = new int[sheetWidth * sheetHeight];
			image.getRGB(0, 0, sheetWidth, sheetHeight, pixels, 0, sheetWidth);
		} catch (IOException e) {
			
			e.printStackTrace();
			System.err.println("Error loading " + path);
		}
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	public int getWidth() {
		return sheetWidth;
	}
	
	public int getHeight() {
		return sheetHeight;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
}
