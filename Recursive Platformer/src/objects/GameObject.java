package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.Main;
import util.GravityData;
import util.Point;
import util.Vect2D;

public abstract class GameObject {
	
	// Observer
	private static Main main = Main.get();
	
	// Properties
	public long birthTime;

	public Point position;
	public Vect2D velocity;
	
	public double drawWidth;
	public double drawHeight;
	
	public float hue;
	public float saturation;
	public float brightness;
	
	// Visible lists
	public ArrayList<GameObject> solidObjects;
	
	public GameObject(Point position, double drawWidth, double drawHeight, ArrayList<GameObject> solidObjects) {
		// Properties
		this.birthTime = System.currentTimeMillis();
		
		this.position = position;
		this.drawWidth = drawWidth;
		this.drawHeight = drawHeight;
		this.velocity = new Vect2D(0, 0);
		
		this.hue = 1.0f;
		this.saturation = 1.0f;
		this.brightness = 1.0f;
		
		// Visible lists
		this.solidObjects = solidObjects;
	}
	
	// Update
	public abstract void update(double deltaTime);

	public void applyGravity(double deltaTime, GravityData gravity) {
		velocity.j += gravity.descendingGravity * deltaTime;
		if (velocity.j > gravity.terminal) velocity.j = gravity.terminal;
	}
	
	public void translate(double deltaTime) {
		Rectangle hitbox = this.getHitbox();
		
		// Hitbox connecting current and future horizontal positions
		Rectangle translationHitbox_x = (velocity.i > 0)
				? new Rectangle(hitbox.x, hitbox.y, (hitbox.width + 1) + (int) (velocity.i * deltaTime), hitbox.height)
				: new Rectangle((hitbox.x - 1) - (int) (velocity.i * deltaTime), hitbox.y,
						hitbox.width + (int) (velocity.i * deltaTime), hitbox.height);
		
		// Translate horizontal positon 
		position.x += velocity.i * deltaTime;
		
		
		System.out.println("===============");
		
		// Search objects for collisions
		boolean touchingWall = false;
		for (GameObject s : solidObjects) {
			Rectangle objectHitbox = s.getHitbox();
			
			// Ignores collisions with itself
			if (this.equals(s)) continue;
			
			// Correct horizontal collisions
			if (translationHitbox_x.intersects(objectHitbox)) {
				System.out.println("");
				System.out.println("H intersection:");
				System.out.println("block @ x: " + objectHitbox.x + "y: " + objectHitbox.y);
				System.out.println("you @:" + hitbox.x + "y: " + hitbox.y);
				if (velocity.i > 0) {
					position.x = objectHitbox.x - hitbox.width;
					velocity.i = 0;
					touchingWall = true;
					System.out.println("right trigger");
				} else if (velocity.i < 0) {
					position.x = objectHitbox.x + objectHitbox.width;
					velocity.i = 0;
					touchingWall = true;
					System.out.println("left trigger");
				}
			}
		}
		
		// Execute horizontal triggers
		if (touchingWall) {
			wallTrigger();
		} else {
			wallAbsenceTrigger();
		}
		
		// Hitbox connecting current and future horizontal positions
		Rectangle translationalHitbox_y = (velocity.j > 0)
				? new Rectangle(hitbox.x, hitbox.y, hitbox.width, (hitbox.height + 1) + (int) (velocity.j * deltaTime))
				: new Rectangle(hitbox.x, (hitbox.y - 1) - (int) (velocity.j * deltaTime), hitbox.width,
						hitbox.height + (int) (velocity.j * deltaTime));
		
		// Translate vertical position
		position.y += velocity.j * deltaTime;
		
		// Search objects for collisions
		boolean touchingFloor = false;
		boolean touchingCeiling = false;
		for (GameObject s : solidObjects) {
			Rectangle objectHitbox = s.getHitbox();
			
			// Ignores collisions with itself
			if (this.equals(s)) continue;
			
			// Correct vertical collisions
			if (translationalHitbox_y.intersects(objectHitbox)) {
				System.out.println("");
				System.out.println("V intersection: ");
				System.out.println("block @ x: " + objectHitbox.x + "y: " + objectHitbox.y);
				System.out.println("you @:" + hitbox.x + "y: " + hitbox.y);

				
				if (velocity.j > 0) {
					position.y = objectHitbox.y - hitbox.height;
					velocity.j = 0;
					touchingFloor = true;
					System.out.println("floor trigger");
				} else if (velocity.j < 0) {
					position.y = objectHitbox.y + objectHitbox.height;
					velocity.j = 0;
					touchingCeiling = true;
					System.out.println("roof trigger");
				}
			}
		}
		
		// Execute vertical triggers
		if (touchingFloor) {
			floorTrigger();
		} else {
			floorAbsenceTrigger();
		}
		
		if (touchingCeiling) {
			ceilingTrigger();
		} else {
			ceilingAbsenceTrigger();
		}
		
	}

	// Collision triggers
	public abstract void wallTrigger();
	public abstract void floorTrigger();
	public abstract void ceilingTrigger();
	public abstract void wallAbsenceTrigger();
	public abstract void floorAbsenceTrigger();
	public abstract void ceilingAbsenceTrigger();
	
	// Render
	public void render(Graphics2D g) {
		g.setColor(getColor());
		g.fillRect((int) (position.x), (int) (position.y), (int) (drawWidth), (int) (drawHeight));
	}
	
	// Misc
	public long getAge() {
		return System.currentTimeMillis() - birthTime;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int) (position.x), (int) (position.y), (int) (drawWidth), (int) (drawHeight));
	}
	
	public Color getColor() {
		return Color.getHSBColor(hue, saturation, brightness);
	}
	
}
