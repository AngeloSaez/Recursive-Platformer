package objects;

import util.GravityData;
import util.Style;

public class Player extends GameObject {
	
	// Instance
	public static Player instance = null;
	
	// Jump
	public double jumpHeight = 2.25 * Style.tileRes;
	public int jumpCounter = 0;
	public final int jumpCountMax = 2;

	private Player() {
		super(null, Style.tileRes, Style.tileRes, null);
		this.hue = 0.43f;
		this.saturation = 0.4f;
		this.brightness = 0.95f;
		
	}
	
	public static Player get() {
		// Will need to set position and potentially solid objects list seperatly
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	@Override
	public void update(double deltaTime) {
		applyGravity(deltaTime, GravityData.inGameGravity);
		translate(deltaTime);
	}
	
	// Control
	public void jump(GravityData gravity) {
		if (jumpCounter < jumpCountMax) {
			jumpCounter++;
			velocity.j = -Math.sqrt(2 * gravity.descendingGravity * jumpHeight);
		}
	}

	// Collision Triggers
	@Override
	public void wallTrigger() {
		// TODO Auto-generated method stub
	}

	@Override
	public void floorTrigger() {
		jumpCounter = 0;
	}

	@Override
	public void ceilingTrigger() {
		// TODO Auto-generated method stub
	}

	@Override
	public void wallAbsenceTrigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void floorAbsenceTrigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ceilingAbsenceTrigger() {
		// TODO Auto-generated method stub
		
	}

}
