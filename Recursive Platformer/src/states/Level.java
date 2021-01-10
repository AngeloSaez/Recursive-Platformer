package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import engine.GameState;
import objects.Coin;
import objects.GameObject;
import objects.Player;
import objects.Wall;
import util.GravityData;
import util.Point;
import util.Prefabs;
import util.Style;

public class Level extends GameState {
	
	// Room
	public static final int height = 16;
	private int width;
	public int[][] room;
	
	// Room background (0.62f, 0.5f, 0.7f)
	protected float hue = 0.777f;
	private float saturation = 0.12f;
	private float brightness = 0.9f;
	
	// Lists
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	ArrayList<GameObject> solidObjects = new ArrayList<GameObject>();
	
	
	// Player
	private Player player;
	
	// Initialization
	public Level(int width) {
		this.width = width;
		initializeRoom();
		initializePlayer();
	}
	
	protected void initializeRoom() {
		// Pick random prefab
		int prefabIndex = (int) (Math.random() * Prefabs.mapPool.length);
		room = Prefabs.mapPool[prefabIndex];
		
		// Create walls from prefab
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (room[y][x] == 4) {
					int textureVarient = 1;
					if (y < height - 1) {
						if (room[y + 1][x] != 4) {
							textureVarient = 2;
						}
					}
					if (y > 0) {
						if (room[y - 1][x] != 4) {
							textureVarient = 0;
						}
					}
					
					solidObjects.add(new Wall(new Point(x * tileRes, y * tileRes), textureVarient));
				}
			}
		}
		
		// Get indexes of all spawn points
		ArrayList<int[]> spawnPoints = new ArrayList<int[]>();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (room[y][x] == 2) {
					 spawnPoints.add(new int[] {y, x});
				}
			}
		}
		
		// Spawn coins
		int spawnMax = 10;
		for (int spawnCount = 0; spawnCount < spawnMax; spawnCount++) {
			if (spawnPoints.size() > 0) {
				int randomIndex = (int) (Math.random() * spawnPoints.size());
				Point spawnPosition = new Point(spawnPoints.get(randomIndex)[1] * tileRes,
						spawnPoints.get(randomIndex)[0] * tileRes - tileRes / 8);
				spawnPoints.remove(randomIndex);
				objects.add(new Coin(spawnPosition));
			}
		}
		
	}
	
	protected void initializePlayer() {
		player = Player.get();
		player.position = new Point(0, 0);
		player.solidObjects = solidObjects;
		objects.add(player);
		solidObjects.add(player);
	}

	// Update
	@Override
	public void update(double deltaTime) {
		playerControlsAccelerate(deltaTime);
		player.update(deltaTime);
		
		for (GameObject o : objects) {
			if (o instanceof Coin) o.update(deltaTime);
		}

		
	}

	// Render
	@Override
	public void render(Graphics2D g) {
		
		renderBackground(g);
		renderRoom(g);
		renderObjects(g);
	}
	
	private void renderBackground(Graphics2D g) {
		g.setColor(Color.getHSBColor(hue, saturation, brightness));
		g.fillRect(0, 0, width * tileRes, height * tileRes);
	}
	
	private void renderRoom(Graphics2D g) {
		g.setColor(Style.blockColor);
		for (GameObject r : solidObjects) {
			if (r instanceof Wall) {
				r.render(g);
			}
		}
	}
	
	private void renderObjects(Graphics2D g) {
		for (GameObject o : objects) {
			o.render(g);
		}
	}

	// Control
	public boolean north = false;
	public boolean east = false;
	public boolean west = false;
	public boolean south = false;
	
	private void playerControlsAccelerate(double deltaTime) {
		double xAcc = 0.0375;
		double terminal = 0.0875;
		// Accelerate horizontally based off of button presses
		if (east) {
			player.velocity.i += xAcc * deltaTime;
		}
		if (west) {
			player.velocity.i -= xAcc * deltaTime;
		}
		// Terminal velocity correction
		if (player.velocity.i > terminal) {
			player.velocity.i = terminal;
		}
		if (player.velocity.i < -terminal) {
			player.velocity.i = -terminal;
		}
	}
	
	// KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// Player jump
		case KeyEvent.VK_Z:
			player.jump(GravityData.inGameGravity);
			break;
		// Player movement
		case KeyEvent.VK_UP:
			north = true;
			break;
		case KeyEvent.VK_RIGHT:
			east = true; 
			break;
		case KeyEvent.VK_DOWN:
			south = true;
			break;
		case KeyEvent.VK_LEFT:
			west = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		// Player movement
		case KeyEvent.VK_UP:
			north = false;
			break;
		case KeyEvent.VK_RIGHT:
			east = false; 
			if (player.velocity.i > 0) player.velocity.i = 0;
			break;
		case KeyEvent.VK_DOWN:
			south = false;
			break;
		case KeyEvent.VK_LEFT:
			west = false;
			if (player.velocity.i < 0) player.velocity.i = 0;
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
