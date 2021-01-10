package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import states.Level;
import states.WorldMap;
import util.Style;
import util.Textures;

public class Main extends JFrame implements KeyListener {
	
	/*
	 * Singleton instance
	 */
	private static Main instance = null; 
	
	
	/*
	 * Instance variables
	 */
	public Dimension screenDimensions;
	public double timeModification;
	public boolean isRunning;
	public long realDeltaTime;
	public double modifiedDeltaTime;
		
	
	/*
	 * Initiatlization
	 */
	private Main() {
		// Instance variables
		screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		timeModification = 1.0;
		
		// Listener setup
		addKeyListener(this);
		setSize(screenDimensions.width, screenDimensions.height);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		// Load textures
		Textures.loadTextures();
		
		// Run setup
		isRunning = true;
	}
	
	public static Main get() {
		if (instance == null) {
			instance = new Main();
			// Lazy initialization
			GameStateManager.get().worldMap = new WorldMap();
			GameStateManager.get().currentState = GameStateManager.get().worldMap;
		}
		
		// Return single instance
		return instance;
	}
	
	public void run() {
		// Record timing
		long startTime = System.currentTimeMillis();
		long lastTime = startTime;
		
		while (isRunning) {
			// Calculate delta time
			realDeltaTime = System.currentTimeMillis() - lastTime;
			modifiedDeltaTime = realDeltaTime * timeModification;
			lastTime = System.currentTimeMillis();
			
			// Update
			GameStateManager.get().update(modifiedDeltaTime);
			
			// Render
			render();
			
		}
	}

	public void render() {
		// Creates 2-buffer buffer strategy
		BufferStrategy bs = getBufferStrategy();
		if (getBufferStrategy() == null) {
			createBufferStrategy(2);
			return;
		}

		// Draws to hidden buffer, clears previous image
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setBackground(Color.getHSBColor(0.0f, 0.0f, 0.0f));
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.clearRect(0, 0, screenDimensions.width, screenDimensions.height);
		
		// Scale
		double scale = ((double)(screenDimensions.height) / (Style.tileRes * Level.height));
		g.scale(scale, scale);

		// GameState render
		GameStateManager.get().render(g);
		
		// Disposes graphics object and shows hidden buffer
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		get().run();
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// End program
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
		// GameState
		GameStateManager.get().keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// GameState
		GameStateManager.get().keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


}