package com.hamenopi.thecheese.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.hamenopi.thecheese.entity.MathEntity;
import com.hamenopi.thecheese.entity.mob.John;
import com.hamenopi.thecheese.graphics.SpriteSheet;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String string) {
		super(string);
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
		add(new John(9, 4));
		//add(new MathEntity("add", 4));
		// add(new House(4, 5, 15, 34));
		// add(new House(169, 3, 9, 4));
		// add(new House(13, 30, 175, 6));
		// add(new House(8, 3, 2, 9, 4)); /// << -- ?? !!
	}
}
