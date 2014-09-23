package com.hamenopi.thecheese;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.hamenopi.thecheese.entity.mob.Player;
import com.hamenopi.thecheese.graphics.Font;
import com.hamenopi.thecheese.graphics.Screen;
import com.hamenopi.thecheese.input.Keyboard;
import com.hamenopi.thecheese.input.Mouse;
import com.hamenopi.thecheese.level.Level;
import com.hamenopi.thecheese.level.TileCoordinate;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public final static String NAME = "The Cheese";
	private static int WIDTH = 600;
	private static int HEIGHT = WIDTH / 16 * 9;
	private static int SCALE = 1;
	private static int BIT_SHIFT = 5; // means 32 pixels
	private int fps = 0;
	private int ups = 0;
	private static int xScroll = 0;
	private static int yScroll = 0;
	private static double xAdjust = 0;
	public static double yAdjust = 0;
	private boolean running = false;
	public static boolean scroll = true;
	private Screen screen;
	private JFrame frame;
	private Keyboard key;
	private Mouse mouse;
	private TileCoordinate tC;
	private static Level level;
	private static Player player;
	private Thread thread;
	private MainMenu menu;
	private Font font;
	private static ArrayList<Level> levels;
	private int action;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public enum GAMESTATE {
		MAIN_MENU, PLAY, PAUSE, QUIT, CREDITS
	};
	
	public static GAMESTATE GameState = GAMESTATE.MAIN_MENU;
	
	public Game() {
		setDimenson();
		setObjects();
		makeLevels();
		setPlayer(9, 6);
		
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	private void makeLevels() { // Good for now!
		levels = new ArrayList<Level>();
		levels.add(Level.spawn); // 0
		levels.add(Level.test); // 1
		levels.add(Level.race); // 2
		for (int i = 0; i < levels.size(); i++) {
		}
		level = levels.get(0);
	}
	
	private void update() {
		if (action > 0) action--;
		key.update();
		if (action == 0) {
			
			switch (GameState) {
				case MAIN_MENU:
					menuState();
					break;
				
				case PLAY:
					playState();
					break;
				
				case QUIT:
					System.exit(0);
					break;
				
				case PAUSE:
					pauseState();
					break;
				
				default:
					break;
			}
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		setScroll();
		screen.clear(0);
		
		switch (GameState) {
			case MAIN_MENU:
				menu.render(screen);
				font.render(0, 0, 0xffffff, 0, "Main Menu", screen, false);
				break;
			
			case PLAY:
				level.render(screen, xScroll, yScroll);
				font.render(0, 0, 0xffffff, 0, "Play State", screen, false);
				//screen.uirender("1\n2\n3\n4");
				
			
				break;
			
			case QUIT:
				font.render(0, 0, 0xffffff, 0, "Quit", screen, false);
				break;
			
			case PAUSE:
				level.render(screen, xScroll, yScroll);
				font.render(0, 0, 0xffffff, 0, "Pause State", screen, false);
				break;
			
			default:
				break;
		}
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void pauseState() {
		if (key.confirm) {
			System.out.println("Un Pausing");
			action += 30;
			GameState = GAMESTATE.PLAY;
		} else if (key.escape) {
			System.out.println("Quitting");
			action += 30;
			GameState = GAMESTATE.MAIN_MENU;
		}
		
	}
	
	private void playState() {
		player.update();
		level.update();
		if (key.escape) {
			System.out.println("Pausing");
			action += 60;
			GameState = GAMESTATE.PAUSE;
		}
		
	}
	
	private void menuState() {
		menu.update();
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(NAME);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}
	
	public void run() {
		double fpsTimer = System.currentTimeMillis();
		double nspu = 1000000000d / 60d;
		double then = System.nanoTime();
		double unprocessed = 0d;
		requestFocus();
		
		while (running) {
			boolean canRender = false;
			double now = System.nanoTime();
			unprocessed += (now - then) / nspu;
			then = now;
			
			while (unprocessed > 1) {
				// Update
				ups++;
				update();
				canRender = true;
				--unprocessed;
			}
			
			// Render
			if (canRender) {
				fps++;
				render();
			}
			// Report
			if (System.currentTimeMillis() - fpsTimer > 1000) {
				fps = 0;
				ups = 0;
				fpsTimer += 1000;
			}
		}
	}
	
	public synchronized void start() {
		running = true;
		if (thread == null) new Thread(this, "Display").start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void quit() {
		System.exit(0);
		
	}
	
	public static int getBitShift() {
		return BIT_SHIFT;
	}
	
	public static int getSCALE() {
		return SCALE;
	}
	
	public static int getWindowHeight() {
		return HEIGHT * SCALE;
	}
	
	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}
	
	private void setLevel(Level level) {
		this.level = level;
	}
	
	private void setObjects() {
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		key = new Keyboard();
		mouse = new Mouse();
		// gsm = new GameStateManager();
		menu = new MainMenu(key);
		font = new Font();
	}
	
	public void setPlayer(int x, int y) {
		tC = new TileCoordinate(x, y);
		if (player == null) player = new Player(tC.x(), tC.y(), key);
		level.add(player);
		
	}
	
	private void setScroll() {
		if (scroll) {
			xScroll = (int) (player.getX() - (screen.width >> 1) + xAdjust);
			yScroll = (int) (player.getY() - (screen.height >> 1) + yAdjust);
		}
	}
	
	public void setDimenson() {
		Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
		setPreferredSize(dimension);
		setSize(dimension);
	}
	
	public void setGameState(GAMESTATE state) {
		Game.GameState = state;
	}
	
	public static void setLevel(int i) {
		level = levels.get(i);
		level.add(player);
		
	}
}
