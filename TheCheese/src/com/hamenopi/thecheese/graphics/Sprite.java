package com.hamenopi.thecheese.graphics;

public class Sprite {
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite land = new Sprite(32, 0, 0, SpriteSheet.simple);
	public static Sprite water = new Sprite(32, 0, 1, SpriteSheet.simple);
	public static Sprite dirt = new Sprite(32, 1, 0, SpriteSheet.simple);
	public static Sprite rock = new Sprite(32, 1, 1, SpriteSheet.simple);
	
	public static Sprite bUItl = new Sprite(16, 0, 0, SpriteSheet.brownTileUI);
	public static Sprite bUItc = new Sprite(16, 1, 0, SpriteSheet.brownTileUI);
	public static Sprite bUItr = new Sprite(16, 2, 0, SpriteSheet.brownTileUI);
	public static Sprite bUIml = new Sprite(16, 0, 1, SpriteSheet.brownTileUI);
	public static Sprite bUImc = new Sprite(16, 1, 1, SpriteSheet.brownTileUI);
	public static Sprite bUImr = new Sprite(16, 2, 1, SpriteSheet.brownTileUI);
	public static Sprite bUIbl = new Sprite(16, 0, 2, SpriteSheet.brownTileUI);
	public static Sprite bUIbc = new Sprite(16, 1, 2, SpriteSheet.brownTileUI);
	public static Sprite bUIbr = new Sprite(16, 2, 2, SpriteSheet.brownTileUI);
	//Yes Ugly
	public static Sprite spell1 = new Sprite(8, 0, 0, SpriteSheet.spells);
	
	public static Sprite house1 = new Sprite(32 * 3, 2, 1, SpriteSheet.master);
	
	public static Sprite doorClosed = new Sprite(32, 2, 6, SpriteSheet.master);
	public static Sprite doorOpened = new Sprite(32, 3, 6, SpriteSheet.master);
	
	public static Sprite andy = new Sprite(32, 5, 3, SpriteSheet.master);
	public static Sprite particle_normal = new Sprite(3, 0xffaaaaaa);
	public static Sprite voidSprite = new Sprite(32, 0);
	
	// public static Sprite playButton = new Sprite(96,32,0,0, SpriteSheet.playButton);
	
	protected Sprite(int size, SpriteSheet sheet) {
		this(size, size, sheet);
	}
	
	protected Sprite(int width, int height, SpriteSheet sheet) {
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		this.sheet = sheet;
	}
	
	protected Sprite(int size, int x, int y, SpriteSheet sheet) {
		this(size, size, x, y, sheet);
	}
	
	protected Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int color) {
		this(size, size, color);
	}
	
	public Sprite(int width, int height, int color) {
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int size) {
		this(pixels, size, size);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
		// this.pixels = pixels;
	}
	
	// Set Color
	private void setColor(int color) {
		for (int i = 0, len = pixels.length; i < len; i++)
			pixels[i] = color;
	}
	
	private void load() {
		int i, j;
		for (int yP = 0; yP < SPRITE_HEIGHT; yP++) {
			for (int xP = 0; xP < SPRITE_WIDTH; xP++) {
				i = xP + yP * SPRITE_WIDTH;
				j = (xP + this.x) + (yP + this.y) * sheet.SPRITE_WIDTH;
				pixels[i] = sheet.pixels[j];
			}
		}
	}
	
	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT;
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
		
		for (int yp = 0; yp < (sheet.getHeight() / sheet.SPRITE_HEIGHT); yp++) {
			for (int xp = 0; xp < (sheet.getWidth() / sheet.SPRITE_WIDTH); xp++) {
				
				for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
					for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
			}
		}
		return sprites;
	}
	
	public int getWidth() {
		return SPRITE_WIDTH;
	}
	
	public int getHeight() {
		return SPRITE_HEIGHT;
	}
	
}
