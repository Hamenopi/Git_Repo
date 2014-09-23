package com.hamenopi.thecheese.graphics;

public class AnimatedSprite extends Sprite {
	private int frame;
	private Sprite sprite;
	private int rate = 25;
	private int time = 0;
	private int animationLength = -1;
	
	public AnimatedSprite(int size, SpriteSheet sheet, int length) {
		this(size, size, sheet, length);
	}
	
	public AnimatedSprite(int width, int height, SpriteSheet sheet, int length) {
		super(width, height, sheet);
		sprite = sheet.getSprites()[0];
		animationLength = length;
		if (length - 1 >= sheet.getSprites().length) System.err.println("Error Animation Length");
	}
	
	public void update() {
		
		time++;
		if (time % rate == 0) {
			frame = (frame + 1) % animationLength;
			sprite = sheet.getSprites()[frame];
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int rate) {
		this.rate = rate;
	}
	
	public void setFrame(int i) {
		if (i > sheet.getSprites().length - 1) {
			System.err.println("Index oob" + this);
		}
		sprite = sheet.getSprites()[i];
	}
}
