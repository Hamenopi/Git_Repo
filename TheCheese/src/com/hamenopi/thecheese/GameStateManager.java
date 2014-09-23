package com.hamenopi.thecheese;

import java.util.ArrayList;

import com.hamenopi.thecheese.graphics.Screen;

public class GameStateManager {
	
	private ArrayList<GameState> states;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	
	public GameStateManager() {
		states = new ArrayList<GameState>();
		currentState = MENUSTATE;
	}
	
	public void setCurrentStat(int i) {
		currentState = i;
	}
	
	public void update() {
		states.get(currentState).update();
	}
	
	public void draw(Screen screen) {
		states.get(currentState).render(screen);
	}
	
	public void  keyPressed(){
		
	}
	public void  keyReleased(){
		
	}
}
