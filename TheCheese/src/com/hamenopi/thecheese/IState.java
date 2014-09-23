package com.hamenopi.thecheese;

import com.hamenopi.thecheese.graphics.Screen;

public interface IState {
	public void update();
	
	public void render(Screen screen);
	
	public void onEnter();
	
	public void onExit();
}
