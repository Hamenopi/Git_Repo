package com.hamenopi.thecheese.level;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.entity.EntityIDList;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;

public class Door extends Entity {
	private boolean locked = false;
	private boolean open = false;
	private int dx, dy;
	private int dl = -1;
	
	// Constructors
	public Door(int x, int y) {
		initEntity(x, y, Sprite.doorClosed, EntityIDList.DOOR);
	}
	
	public Door(int x, int y, int dx, int dy) {
		this(x, y);
		this.dx = toPixel(dx);
		this.dy = toPixel(dy);
	}
	
	public Door(int x, int y, int level, int dx, int dy) {
		this(x, y, dx, dy);
		dl = level; // << -- ?? !!
	}
	
	// Methods
	public void update() {
	}
	
	public void render(Screen screen) {
		// Debug.drawRect(screen, (int) x, (int) y, width, height, true);
		screen.renderMob((int) getX(), (int) getY(), sprite);
	}
	
	//protected void interact(Entity e) {
	//	if (!isLocked() && !isOpen()) setOpen(true);
	//}
	
	public void goInside(Entity e) {
		if (open && dx != 0 && dy != 0) {
			if (dl > -1){ // 
				Game.setLevel(dl); // << -- !! ??
				
			}
			
			e.setX(dx);//
			e.setY(dy);//
			
		}
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
		sprite = Sprite.doorOpened;
	}
	
	public void open() {
	}
	
	public void hint() {
	}}
