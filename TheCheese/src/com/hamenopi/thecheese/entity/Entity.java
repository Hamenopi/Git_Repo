package com.hamenopi.thecheese.entity;

import java.util.Random;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.mob.Mob;
import com.hamenopi.thecheese.entity.mob.Player;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.Sprite;
import com.hamenopi.thecheese.level.Door;
import com.hamenopi.thecheese.level.House;
import com.hamenopi.thecheese.level.Level;
import com.hamenopi.thecheese.level.tile.Tile;

public class Entity {
	private double x;// top
	private double y;
	protected int width, height;
	
	protected double left; // west //x
	protected double right; // east //x+width
	protected double top; // north //y
	protected double bottom; // south //y+height
	protected double centerX; // x+(width/2)
	protected double centerY; // y+(height/2)
	
	protected double xa, ya; // movement
			
	protected Sprite sprite;
	protected Level level;
	protected EntityIDList id;
	
	private boolean removed = false;
	private boolean trigger = false;
	
	protected final Random random = new Random();
	protected String name;
	
	// cooldown
	protected int action;
	
	// Movement modifiers
	protected static final double BOOSTED = 1.5;
	protected static final double SLOWED = 0.5;
	protected static final double ENGAGED = 0.25;
	protected static final double STOPPED = 0.0;
	
	public Entity() {
	}
	
	public Entity(int x, int y, Sprite sprite) {
		this.setX(x);
		this.setY(y);
		this.sprite = sprite;
	}
	
	public void update() {
	}
	
	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) getX(), (int) getY(), sprite, true);
	}
	
	public void init(Level level) { // normalish stuff V V
		this.level = level;
	}
	
	public void initEntity(int x, int y, Sprite sprite, EntityIDList id) {
		this.setX(toPixel(x));
		this.setY(toPixel(y));
		this.sprite = sprite;
		this.width = sprite.SPRITE_WIDTH;
		this.height = sprite.SPRITE_HEIGHT;
		this.id = id;
	}
	
	protected boolean tileCollision(double xa, double ya) {
		boolean collision = false;
		
		for (int c = 0; c < 4; c++) {
			int ix = (int) ((x + xa) - c % 2) >> Game.getBitShift();
			int iy = (int) ((y + ya) - c / 2) >> Game.getBitShift();
			
			if (c % 2 != 0) ix += (width >> Game.getBitShift());
			if (c / 2 != 0) iy += (height >> Game.getBitShift());
			
			Tile tile = level.getTile(ix, iy);
			if (tile == null || tile.solid()) return true;
		}
		return collision;
	} // tileCollision
	
	protected Entity entityCollsision(double xa, double ya) {
		boolean collision = false;
		for (int i = 0, entSize = level.getEntities().size(); i < entSize; i++) {
			// default false
			collision = false;
			
			Entity target = level.getEntities().get(i);
			
			// If self, ignore
			if (target == this) continue;
			
			// If has no size, ignore (for now)
			if (target.getWidth() == 0 || target.getHeight() == 0) continue;
			
			if (ya < 0) {// going up //
				if ((getNorth() <= target.getSouth()) //
						&& (getNorth() >= target.getNorth()) //
						&& (getWest() <= target.getEast()) //
						&& (getEast() >= target.getWest())) {
					collision = true;
				}
			} else if (ya > 0) {// going down //
				if ((getSouth() <= target.getSouth()) //
						&& (getSouth() >= target.getNorth()) //
						&& (getWest() <= target.getEast()) //
						&& (getEast() >= target.getWest())) {
					collision = true;
				}
			} // (y)
			
			if (xa > 0) {// going east // yeah...
				if ((getEast() >= target.getWest())//
						&& (getEast() <= target.getEast()) //
						&& (getNorth() <= target.getSouth()//
						&& getSouth() >= target.getNorth())) {
					collision = true;
				}
			} else if (xa < 0) {// going west //
				if ((getWest() >= target.getWest())//
						&& (getWest() <= target.getEast()) //
						&& (getNorth() <= target.getSouth()//
						&& getSouth() >= target.getNorth())) {
					collision = true;
				}
			} // (x)
			
			if (collision) {
				System.out.println(collision);
				return target;
			}
		}
		return null;
	}
	
	protected double interact(Entity e) {
		EntityIDList check = e.getID();
		switch (check) {
		
			case MATHENTITY:
				MathEntity me = (MathEntity) e;
				mathCase(me);
				return 1;
				
			case HOUSE:
				House house = (House) e;
				houseCase(house);
				return STOPPED;
				
			case MOB:
				Mob mob = (Mob) e;
				mobCase(mob);
				return STOPPED;
				
			case PLAYER:
				Player player = (Player) e;
				playerCase(player);
				return STOPPED;
				
			default:
				otherCase(e);
				return 1;
		}
		
	}
	
	protected void interact(Entity e, double xa, double ya) {
		EntityIDList check = e.getID();
		
	}
	
	private void mathCase(MathEntity me) {
		// TODO Auto-generated method stub
		
	}
	
	private void otherCase(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
	private void playerCase(Player player) {
		// TODO Auto-generated method stub
		
	}
	
	private void mobCase(Mob mob) {
		System.out.println(mob);
		if (mob.getHostile()) {
			// this.combat(mob);
		} else {
			// this.interact(mob);;
		}
		
	}
	
	private void houseCase(House house) {
		
		Door door = house.getDoor();
		if (door == null) {
			// setSpeed(STOPPED);
			return;
		}
		int doorX = ((int) door.getX() >> 5);
		boolean atDoor = (this.ya < 0 // Going Up
				&& (int) this.getX() + (this.width >> 1) >> 5 == doorX // Verify X
		&& (int) this.getNorth() >> 5 == ((int) door.getSouth() >> 5));
		
		if (door.isOpen() && atDoor) {
			
			if (action == 0) {
				action = 5;
				door.goInside(this); // Go Inside // This is where the level changing will happen
				return;
			}
			
		} else {
			
			if (atDoor && !door.isLocked()) {
				
				if (action == 0) {
					action = 100;
					door.setOpen(true); // Open Door //
				}
				
			} else if (atDoor && door.isLocked()) {
				
				if (action == 0) {
					action = 20;
					door.hint(); // Give Hint //
				}
			}
		}
		// setSpeed(STOPPED);
	}
	
	public int toPixel(int x) {
		return x << Game.getBitShift();
	}
	
	public int toTile(int x) {
		return x << Game.getBitShift();
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public boolean isTriggered() {
		return trigger;
	}
	
	public EntityIDList getID() {
		return id;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getWidth() {
		int result = (width != 0) ? width : 32;
		return result;
	}
	
	public int getHeight() {
		int result = (height != 0) ? height : 32;
		return result;
	}
	
	public double getNorth() {
		return getY() - ya;
	}
	
	public double getSouth() {
		return getY() - ya + height;
	}
	
	public double getWest() {
		return getX() - xa;
	}
	
	public double getEast() {
		return getX() - xa + width;
	}
	
	private double setSpeed(double xy, double rate) {
		return (xy * rate);
		
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setPos(int dx, int dy) {
		this.setX(dx << Game.getBitShift());
		this.setY(dy << Game.getBitShift());
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
