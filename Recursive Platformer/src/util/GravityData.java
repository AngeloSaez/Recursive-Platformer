package util;

public class GravityData {
	
	public double descendingGravity;
	public double terminal;
	
	private GravityData(double descendingGravity, double terminal) {
		this.descendingGravity = descendingGravity;
		this.terminal = terminal;
	}
	
	public static final GravityData inGameGravity = new GravityData(0.00125, 0.225);
	
}