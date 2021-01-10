package objects;

import util.Point;
import util.Style;
import util.Textures;

public class Wall extends GameObject {

	public Wall(Point position, int textureVarient) {
		super(position, Style.tileRes, Style.tileRes, null);
		this.hue = 0.62f;
		this.saturation = 0.12f;
		this.brightness = 0.95f;
		
		switch (textureVarient) {
		case 0:
			texture = Textures.stone0;
			break;
		case 1:
			texture = Textures.stone1;
			break;
		case 2:
			texture = Textures.stone2;
			break;
		}
		
	}

	@Override
	public void update(double deltaTime) {}

	@Override
	public void wallTrigger() {}

	@Override
	public void floorTrigger() {}

	@Override
	public void ceilingTrigger() {}

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
