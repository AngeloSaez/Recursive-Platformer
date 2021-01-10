package engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import util.Style;

public abstract class GameState {

	protected static Main main;
	
	protected static int tileRes = Style.tileRes;
	
	public GameState() {
		this.main = Main.get();
	}

	// Update
	public abstract void update(double deltaTime);

	// Render
	public abstract void render(Graphics2D g);

	// Control
	public abstract void keyPressed(KeyEvent e);
	public abstract void keyReleased(KeyEvent e);
	
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseEntered(MouseEvent e);
	public abstract void mouseExited(MouseEvent e);
	public abstract void mouseWheelMoved(MouseWheelEvent e);

}
