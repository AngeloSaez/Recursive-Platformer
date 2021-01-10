package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import engine.GameState;
import engine.GameStateManager;
import levels.Level_1_1;
import objects.Player;
import util.Point;
import util.Style;
import util.Textures;

public class WorldMap extends GameState {
	
	// Style
	private Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
	private int leftMargin = Style.tileRes * 3;
	
	// Levels
	public static enum MapLevel {
		LVL_1_1,
		LVL_1_2,
		LVL_1_3,
	}
	
	// Player
	private Player player;
	private int followX;
	
	// Control
	private MapLevel selectedLevel = MapLevel.LVL_1_1;
	
	public WorldMap() {
		// Player initilization
		player = Player.get();
		player.position = new Point(leftMargin, (tileRes * Level.height) / 2 - tileRes * 2);
	}
	

	@Override
	public void update(double deltaTime) {
		// Update player follow x
		followX = leftMargin + tileRes / 8 + (tileRes * 2 * selectedLevel.ordinal());
		
		// Have player follow
		double followSpeed = 0.08 * deltaTime;
		double deltaX = followX - player.position.x;
		if (Math.abs(deltaX) > 1) {
			player.position.x = (deltaX > 0) ? (player.position.x + followSpeed) : (player.position.x - followSpeed);
		}
	}

	@Override
	public void render(Graphics2D g) {
		// Background
		g.setColor(Color.getHSBColor(0.777f, 0.32f, 0.37f));
		g.fillRect(0, 0, (tileRes * Level.height * 2), (tileRes * Level.height));
		
		// Level box renders
		g.setFont(new Font("Dialog", Font.BOLD, tileRes / 2));
		g.setColor(Style.offWhite);
		for (int i = 0 ; i < MapLevel.values().length; i++) {
			BufferedImage levelTexture = (i == selectedLevel.ordinal()) ? Textures.selectedLevel : Textures.level;
			int drawX = leftMargin + i * tileRes * 2;
			int drawY = (tileRes * Level.height) / 2;
			g.drawImage(levelTexture, drawX, drawY, tileRes, tileRes, main);
			g.drawString("" + (i + 1), drawX + tileRes / 8, drawY - tileRes / 8 + tileRes);
		}
		
		// Player render
		player.render(g);
		
	}

	// Control
	private void selectLevel() {
		switch(selectedLevel) {
		case LVL_1_1:
			GameStateManager.get().currentLevel = new Level_1_1();
			GameStateManager.get().currentState = GameStateManager.get().currentLevel;
			break;
		case LVL_1_2:
			break;
		case LVL_1_3:
			break;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		// Select level
		case KeyEvent.VK_Z:
			selectLevel();
			break;
		// Player movement
		case KeyEvent.VK_RIGHT:
			if (selectedLevel.ordinal() < MapLevel.values().length - 1) {
				selectedLevel = MapLevel.values()[selectedLevel.ordinal() + 1];
			} else  {
				selectedLevel = MapLevel.values()[0];
			}
			break;
		case KeyEvent.VK_LEFT:
			if (selectedLevel.ordinal() > 0) {
				selectedLevel = MapLevel.values()[selectedLevel.ordinal() - 1];
			} else  {
				selectedLevel = MapLevel.values()[MapLevel.values().length - 1];
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
