package com.hamenopi.thecheese;

import com.hamenopi.thecheese.graphics.Screen;

public abstract class GameState {

	protected GameStateManager gsm;
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	public abstract void init();
	public abstract void keyPressed();
	public abstract void keyReleased();
	public abstract void update();
	public abstract void render(Screen screen);
}
