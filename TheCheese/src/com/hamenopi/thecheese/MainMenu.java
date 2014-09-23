package com.hamenopi.thecheese;

import com.hamenopi.thecheese.Game.GAMESTATE;
import com.hamenopi.thecheese.graphics.Font;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.SpriteSheet;
import com.hamenopi.thecheese.input.Keyboard;

public class MainMenu {
	
	private Keyboard key;
	private Font font;
	private int action;
	private String title = Game.NAME;
	
	public enum MENUSTATE {
		PLAY, HELP, QUIT, CREDITS
	};
	
	public static MENUSTATE MenuState = MENUSTATE.PLAY;
	
	public MainMenu(Keyboard key) {
		this.key = key;
		this.font = new Font();
	}
	
	public void update() {
		if (action > 0) action--;
		key.update();
		if (action == 0) {
			switch (MenuState) {
				case PLAY:
					playState();
					break;
				case HELP:
					helpState();
					break;
				case QUIT:
					quitState();
					break;
				case CREDITS:
					creditsState();
					break;
				default:
					break;
			}
		}
	}
	
	public void render(Screen screen) {
		int x = (Game.getWindowWidth() >> 1) / Game.getSCALE();
		int swX = SpriteSheet.playButton.SPRITE_WIDTH >> 1;
		int fx = x - (title.length() * 10);
		
		String helpButton = "TODO: HELP BUTTON";
		String creditsButton = "TODO: CREDITS BUTTON";
		
		font.render(fx, 20, 0xffffff, 0, title, screen, false);
		
		screen.renderSheet((x) - (swX), 50, SpriteSheet.playButton, false);
		screen.renderSheet((x) - (swX), 150, SpriteSheet.quitButton, false);
		
		switch (MenuState) {
			case HELP:
				font.render(x - (helpButton.length() * 10), 100, 0xffffff, 0, helpButton, screen, false);
				break;
			case PLAY:
				screen.drawRect(x - swX - 1, 50 - 1, 96 + 1, 32 + 1, 0xffffff, false);
				break;
			case QUIT:
				screen.drawRect(x - swX - 1, 150 - 1, 96 + 1, 32 + 1, 0xffffff, false);
				break;
			case CREDITS:
				font.render(x - (creditsButton.length() * 10), 200, 0xffffff, 0, creditsButton, screen, false);
				break;
			default:
				break;
		
		}
		
	}
	
	private void creditsState() {
		if (key.confirm) {
			System.out.println("Go to Credits Screen");
			action += 30;
			// Game.GameState = GAMESTATE.CREDITS;
			
		} else if (key.down) {
			System.out.println("Go to Quit Option");
			action += 30;
			MenuState = MENUSTATE.CREDITS;
			
		} else if (key.up) {
			System.out.println("Go to QUITp Option");
			action += 30;
			MenuState = MENUSTATE.QUIT;
		}
		
	}
	
	private void quitState() {
		if (key.confirm) {
			System.out.println("Go to Quit Screen");
			action += 30;
			Game.GameState = GAMESTATE.QUIT;
			
		} else if (key.down) {
			System.out.println("Go to Quit Option");
			action += 30;
			MenuState = MENUSTATE.CREDITS;
			
		} else if (key.up) {
			System.out.println("Go to Help Option");
			action += 30;
			MenuState = MENUSTATE.HELP;
		}
		
	}
	
	private void helpState() {
		if (key.confirm) {
			System.out.println("Go to Help Screen");
			action += 30;
			// Game.GameState = GAMESTATE.HELP;
			
		} else if (key.down) {
			System.out.println("Go to Quit Option");
			action += 30;
			MenuState = MENUSTATE.QUIT;
			
		} else if (key.up) {
			System.out.println("Go to Play Option");
			action += 30;
			MenuState = MENUSTATE.PLAY;
		}
		
	}
	
	private void playState() {
		if (key.confirm) {
			System.out.println("Go to Play Screen");
			action += 30;
			Game.GameState = GAMESTATE.PLAY;
			
		} else if (key.down) {
			System.out.println("Go to Help Option");
			action += 30;
			MenuState = MENUSTATE.HELP;
			
		} else if (key.up) {
			System.out.println("Go to Play Option");
			action += 30;
			MenuState = MENUSTATE.PLAY;
		}
		
	}
	
	// 'for (int i = 0; i < options.length; i++) {
	// if (i == currentSelection) {
	// screen.drawRect((x) - (swX) - 1, ((i + 1) * 50) - 1, 96 + 1, 32 + 1, 0xffffff, true);
	// }
	// ''}
	
	// here's where you put background image
	
	//
	// if (State == STATE.GAME) screen.drawRect((x >> 1) - (SpriteSheet.playButton.SPRITE_WIDTH >> 1) - 1, 150 - 1, 96 + 1, 32 + 1, 0xffffff, true);
	
	// if (State == STATE.EXIT) screen.drawRect((x >> 1) - (SpriteSheet.quitButton.SPRITE_WIDTH >> 1) - 1, 200 - 1, 96 + 1, 32 + 1, 0xffffff, true);
	
}
