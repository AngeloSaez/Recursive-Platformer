package objects;

import engine.Main;
import util.Point;
import util.Style;

public class Coin extends GameObject {
	
	public double randomOffset;
	public double anchorHeight;

	public Coin(Point position) {
		super(position, Style.tileRes, Style.tileRes, null);
		this.hue = 0.19f;
		this.saturation = 0.4f;
		this.brightness = 0.95f;
		
		this.randomOffset = Math.random();
		this.anchorHeight = position.y;
		
	}

	@Override
	public void update(double deltaTime) {
		double speedUp = Main.get().timeModification;
		double raisePeriod = 3000 / speedUp;
		long ageWithOffset = System.currentTimeMillis() - birthTime + (long)(raisePeriod * randomOffset);
		double raiseX = (((ageWithOffset / speedUp) % raisePeriod) / raisePeriod);
		float maxRaiseHeight = Style.tileRes / 4;
		float riseSize = (float) (maxRaiseHeight * Math.sin(2 * Math.PI * raiseX));
		
		position.y = anchorHeight + riseSize;
	}

	@Override
	public void wallTrigger() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void floorTrigger() {
		// TODO Auto-generated method stub
		
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
