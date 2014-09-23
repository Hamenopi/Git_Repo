package com.hamenopi.thecheese.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.hamenopi.thecheese.Game;
import com.hamenopi.thecheese.entity.Entity;
import com.hamenopi.thecheese.entity.mob.Player;
import com.hamenopi.thecheese.entity.particle.Particle;
import com.hamenopi.thecheese.entity.projectile.Projectile;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.level.tile.AnimatedTile;
import com.hamenopi.thecheese.level.tile.Node;
import com.hamenopi.thecheese.level.tile.Tile;
import com.hamenopi.thecheese.util.Vector2i;

public class Level {
	protected int width, height;
	protected int[] tileInt;
	protected int[] tiles;
	public static Level spawn = new SpawnLevel("/maze1.png");
	public static Level test = new SpawnLevel("/test.png");

	
	public static Level race = new RaceLevel("/maze1.png");
	private static final Random random = new Random();
	public static Map<Integer, Level> levels = new HashMap<Integer, Level>();

	
	// Lists
	private List<Entity> entities = new ArrayList<>();
	private List<Projectile> projectiles = new ArrayList<>();
	private List<Particle> particles = new ArrayList<>();
	private List<Player> players = new ArrayList<>();
	private List<AnimatedTile> animTiles = new ArrayList<>();
	
	// Comparators
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return +1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};
	
	// Constructors
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tileInt = new int[width * height];
		makeLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		makeLevel();
	}
	
	public Level(Level spawn2) {
		// TODO Auto-generated constructor stub
	}

	// Init
	protected void loadLevel(String path) {
	}
	
	protected void makeLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileInt[x + y * width] = random.nextInt(4);
			}
		}
	}
	
	// Update
	public void update() {
		
		for (int i = 0; i < getEntities().size(); i++) {
			getEntities().get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < getEntities().size(); i++) {
			if (getEntities().get(i).isRemoved()) getEntities().remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isRemoved()) players.remove(i);
		}
	}
	
	// Render Only the level tiles that are visible on screen
	public void render(Screen screen, int xScroll, int yScroll) {
		screen.setOffset(xScroll, yScroll);
		int x0 = (xScroll >> Game.getBitShift());
		int x1 = (xScroll + screen.width + 32) >> Game.getBitShift();
		int y0 = (yScroll >> Game.getBitShift());
		int y1 = (yScroll + screen.height + 32) >> Game.getBitShift();
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		renderEntities(screen);
	}
	
	protected void renderEntities(Screen screen) {
		for (int i = 0; i < getEntities().size(); i++) {
			getEntities().get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}
	
	// Tiles
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.water;
		int i = x + y * width;
		if (tiles[i] == Tile.iland) return Tile.land;
		if (tiles[i] == Tile.iwater) {
			return Tile.water;
		}
		return Tile.dirt;
	}
	
		
	
	// Init Entities
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
			entities.add((Player) e);
		} else {
			getEntities().add(e);
		}
	}
	
	public void addTile(AnimatedTile aTile) {
		animTiles.add(aTile);
	}
	
	// Unused
	private void time() {
	}
	
	// Collision
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> Game.getBitShift();
			int yt = (y - c / 2 * size + yOffset) >> Game.getBitShift();
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	// Get Players AND Entities
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		Player player = players.get(0);
		for (int i = 0; i < players.size(); i++) {
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = x - ex;
			int dy = y - ey;
			double distance = (dx * dx) + (dy * dy);
			if (distance <= radius * radius) result.add(player);
		}
		Entity ent = entities.get(0);
		for (int i = 0; i < entities.size(); i++) {
			int x = (int) ent.getX();
			int y = (int) ent.getY();
			
			int dx = x - ex;
			int dy = y - ey;
			double distance = (dx * dx) + (dy * dy);
			if (distance <= radius * radius) result.add(ent);
		}
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		Player player = players.get(0);
		for (int i = 0; i < players.size(); i++) {
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = x - ex;
			int dy = y - ey;
			double distance = (dx * dx) + (dy * dy);
			if (distance <= radius * radius) result.add(player);
		}
		return result;
	}
	
	public Player getPlayerAt(int i) {
		return players.get(i);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public List<Projectile> getProjectiles() {
		return projectiles;
	}
	
	// Vectors
	public List<Node> findPath(Vector2i start, Vector2i finish) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, finish));
		openList.add(current);
		
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			
			if (current.tile.equals(finish)) {
				List<Node> path = new ArrayList<Node>();
				
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null || at.solid()) continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + getDistance(current.tile, a) == 1 ? 1 : 0.95;
				double hCost = getDistance(a, finish);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && (gCost >= node.gCost)) continue;
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			}
		}
		openList.clear();
		closedList.clear();
		return null;
	}
	
	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}
	
	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = (tile.getX() - goal.getX()) * 2;
		double dy = (tile.getY() - goal.getY()) * 2;
		double distance = dx + dy;
		return distance * distance;
	}
	
	public Entity getEntityAt(int x0, int y0, int x1, int y1) {
		for (int e = 0; e < getEntities().size(); e++) {
			double entX = getEntities().get(e).getX();
			double entY = getEntities().get(e).getY();
			int entW = getEntities().get(e).getWidth();
			int entH = getEntities().get(e).getHeight();
			if (entX + entW-1 >= x0 && entX + 1 <= x1 && entY + entH-1 >= y0 && entY + 1 <= y1) { return getEntities().get(e); // for interaction
			}
		}
		return null;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
}
