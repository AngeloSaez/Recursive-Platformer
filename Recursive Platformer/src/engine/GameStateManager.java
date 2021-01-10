package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class GameStateManager {
	
	private static GameStateManager manager = null;
	
	// Active state
	public GameState currentState;
	// State archive
	public GameState worldMap;
	public GameState currentLevel;
	public ArrayList<GameState> subLevels = new ArrayList<GameState>();
	
	
	private GameStateManager() {}
	
	public static GameStateManager get() {
		if (manager == null) manager = new GameStateManager();
		return manager;
	}

	// Update
	public void update(double deltaTime) {
		if (currentState != null) {
			currentState.update(deltaTime);
		}
	}

	// Render
	public void render(Graphics2D g) {
		if (currentState != null) {
			currentState.render(g);
		}
	}

	// Control
	public void keyPressed(KeyEvent e) {
		if (currentState != null) {
			currentState.keyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (currentState != null) {
			currentState.keyReleased(e);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (currentState != null) {
			currentState.mousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseReleased(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseEntered(e);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (currentState != null) {
			currentState.mouseExited(e);
		}
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if (currentState != null) {
			currentState.mouseWheelMoved(e);
		}
	}

}
