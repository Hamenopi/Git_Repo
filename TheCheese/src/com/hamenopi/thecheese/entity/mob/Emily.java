package com.hamenopi.thecheese.entity.mob;

import java.util.List;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.EntityIDList;
import com.hamenopi.thecheese.graphics.AnimatedSprite;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.graphics.SpriteSheet;
import com.hamenopi.thecheese.level.tile.Node;
import com.hamenopi.thecheese.util.Debug;
import com.hamenopi.thecheese.util.Vector2i;

public class Emily extends Mob {
	private AnimatedSprite down = new AnimatedSprite(32, SpriteSheet.eSouth, 1);
	@SuppressWarnings("unused")
	private AnimatedSprite up = new AnimatedSprite(32, SpriteSheet.eNorth, 1);
	@SuppressWarnings("unused")
	private AnimatedSprite left = new AnimatedSprite(32, SpriteSheet.eEast, 1);
	@SuppressWarnings("unused")
	private AnimatedSprite right = new AnimatedSprite(32, SpriteSheet.eWest, 1);
	
	private AnimatedSprite animSprite = down;
	private List<Node> path = null;
	private int radius = 5 << Game.getBitShift();
	
	// Constructor
	public Emily(int x, int y) {
		this.setX(x << Game.getBitShift());
		this.setY(y << Game.getBitShift());
		this.width = animSprite.SPRITE_WIDTH;
		this.height = animSprite.SPRITE_HEIGHT;
		
		initMob(1, down, 10, 1, 1, 1, 1, EntityIDList.MOB);
	}
	
	@Override
	public void update() {
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		List<Player> players = level.getPlayers(this, radius);
		if (players.size() > 0) {
			xa = 0;
			ya = 0;
			pathfind();
		} else {
			wander(4, 50);
		}
		move(xa,ya);
	}
	
	@Override
	public void render(Screen screen) {
		Debug.drawRect(screen, (int) getX(), (int) getY(), 32, 32, true);
		
		sprite = animSprite.getSprite();
		screen.renderMob((int) getX(), (int) getY(), sprite);
	}
	
	protected void pathfind() {
		time++;
		int px = (int) level.getPlayerAt(0).getX();
		int py = (int) level.getPlayerAt(0).getY();
		Vector2i start = new Vector2i((int) (getX() / 32), (int) (getY() / 32));
		Vector2i dest = new Vector2i(px >> 5, py >> 5);
		if (time % 10 == 0) path = level.findPath(start, dest);
		if (path != null) {
			if (path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
				if (getX() < vec.getX() << Game.getBitShift()) xa = speed;
				if (getX() > vec.getX() << Game.getBitShift()) xa = -speed;
				if (getY() < vec.getY() << Game.getBitShift()) ya = speed;
				if (getY() > vec.getY() << Game.getBitShift()) ya = -speed;
			}
		}
	}
}
