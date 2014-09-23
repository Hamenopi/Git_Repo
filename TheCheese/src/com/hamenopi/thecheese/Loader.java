package com.hamenopi.thecheese;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.entity.mob.Player;
import com.hamenopi.thecheese.input.Keyboard;
import com.hamenopi.thecheese.level.Door;
import com.hamenopi.thecheese.level.House;
import com.hamenopi.thecheese.level.Level;
import com.hamenopi.thecheese.level.tile.Tile;


public class Loader {
	
	public static void init(Game game, Keyboard key) {
	
		try {
			loadLevels(game, key);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	private static void loadLevels(Game game, Keyboard input) throws ParserConfigurationException {
		
		try {
			//XMLFile levelFile = new XMLFile("/level.xml");
			//Document doc = levelFile.getDocument();
			
			// Pixels
			//NodeList pixelList = ((Element) ((Element) doc.getElementsByTagName("levels").item(0)).getElementsByTagName("define").item(0)).getElementsByTagName("pixel");
			List<Color> pixelColor = new ArrayList<Color>();
			List<String> binding = new ArrayList<String>();
			BufferedImage lv = ImageIO.read(Game.class.getResourceAsStream("/maze1.png"));
			
			int xTile = lv.getWidth() / 17;
			int yTile = lv.getHeight() / 17;
			BufferedImage[][] levels = new BufferedImage[xTile][yTile];
			Level[] lvs = new Level[xTile * yTile];
			//System.out.println("Level Array: " + (xTile * yTile));
			int count = 0;
			
			// Init Floors
			count = 0;
			for (int i = 0; i < levels.length; i++) {
				for (int j = 0; j < levels[i].length; j++) {
					levels[i][j] = lv.getSubimage(i * 17, j * 17, 17, 17);
					//lvs[count] = new Level(); // here
					//System.out.println("Loading Level: " + (count + 1));
					//lvs[count].floor = j + 1;
					count++;
				}
			}
			
			
			for (int i = 0; i < lvs.length; i++) {// TODO lvs.length
				//if (i <= 10) Level.level.put(lvs[i].floor, lvs[i]);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
