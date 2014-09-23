package com.hamenopi.thecheese.graphics;

public class Font {
	private static int size = 16;
	private static SpriteSheet font = new SpriteSheet("/Anno_16x16.png", size);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String charIndex = //
	
	" !\"#$%&'()*+,-./" + //
			"0123456789:;<=>?" + //
			"@ABCDEFGHIJKLMNO" + //
			"PQRSTUVWXYZ[\\]^_" + //
			"`abcdefghijklmno" + //
			"pqrstuvwxyz{|}~ ";
	
	public Font() {
	}
	
	public void render(int x, int y, String text, Screen screen) {
		render(x, y, 0xffffff, 0, text, screen, false);
	}
	
	public void render(int x, int y, int color, String text, Screen screen) {
		render(x, y, color, 0, text, screen, false);
	}
	
	public void render(int x, int y, int color, int spacing, String text, Screen screen) {
		render(x, y, color, spacing, text, screen, false);
	}
	
	public void render(int x, int y, int color, int spacing, String text, Screen screen, boolean fixed) {
		int xOffset = 0;
		int line = 0;
		int yOffset = 0;
		char currentChar;
		int index;
		
		for (int i = 0; i < text.length(); i++) {
			
			xOffset += size;
			currentChar = text.charAt(i);
			
			if (currentChar == '\n') {
				line++;
				xOffset = 0;
			}
			
			index = charIndex.indexOf(currentChar);
			
			if (index == -1) continue;
			screen.renderSprite(x + xOffset, y + (line * 16 + yOffset), characters[index], color, fixed);
		}
		
	}
	
}
