package com.hamenopi.thecheese.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[150];
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean confirm;
	public boolean escape;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		confirm = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER];
		escape = keys[KeyEvent.VK_ESCAPE];
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
	
}
