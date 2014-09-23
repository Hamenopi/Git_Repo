package com.hamenopi.thecheese.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.hamenopi.thecheese.entity.mob.Andy;
import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.SpriteSheet;

public class RaceLevel extends Level {
	
	public RaceLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			System.out.println("Loading: " + path);
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("! Could not load " + path);
		}
		initLevel();
	}
	
	protected void makeLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y);
			}
		}
	}
	
	protected void initLevel() {
		
		add(new Andy(9, 4));
		
		
	}
}
