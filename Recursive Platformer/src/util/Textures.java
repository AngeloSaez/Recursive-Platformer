package util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textures {

	
	// Returns an image, given a local resource path ("/images/_.png")
    public static BufferedImage getImage(String path) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(Textures.class.getResource(path));
        } catch (IOException e) {
            System.out.println("image not found");
        }
        return bi;
    }
    
    public static BufferedImage selectedLevel;
    public static BufferedImage level;
    public static BufferedImage green0;
    public static BufferedImage green1;
    public static BufferedImage green2;
    public static BufferedImage yellow0;
    public static BufferedImage yellow1;
    public static BufferedImage yellow2;
    public static BufferedImage red0;
    public static BufferedImage red1;
    public static BufferedImage red2;
    public static BufferedImage bgRocks0;
    public static BufferedImage stone0;
    public static BufferedImage stone1;
    public static BufferedImage stone2;
    public static BufferedImage lock;
    public static BufferedImage key;
    public static BufferedImage fireMan;
    public static BufferedImage fireOrb;
    public static BufferedImage fire;
    public static BufferedImage vine;
    public static BufferedImage leaf;
    public static BufferedImage keyMan;
    public static BufferedImage coinMan;
    public static BufferedImage greenMan;
    public static BufferedImage greenPlayer;
    public static BufferedImage redPlayer;
    public static BufferedImage bluePlayer;
    public static BufferedImage coin;
    public static BufferedImage openDoor;
    public static BufferedImage closedDoor;
    public static BufferedImage flag;
    public static BufferedImage onOrb;
    public static BufferedImage offOrb;
    public static BufferedImage onBlock;
    public static BufferedImage offBlock;
    public static BufferedImage spikes;
    
    public static void loadTextures() {
    	 selectedLevel = getImage("/images/selectedLevel.png");
         level = getImage("/images/level.png");
         green0 = getImage("/images/green0.png");
         green1 = getImage("/images/green1.png");
         green2 = getImage("/images/green2.png");
         yellow0 = getImage("/images/yellow0.png");
         yellow1 = getImage("/images/yellow1.png");
         yellow2 = getImage("/images/yellow2.png");
         red0 = getImage("/images/red0.png");
         red1 = getImage("/images/red1.png");
         red2 = getImage("/images/red2.png");
         bgRocks0 = getImage("/images/bgRocks0.png");
         stone0 = getImage("/images/stone0.png");
         stone1 = getImage("/images/stone1.png");
         stone2 = getImage("/images/stone2.png");
         lock = getImage("/images/lock.png");
         key = getImage("/images/key.png");
         fireMan = getImage("/images/fireMan.png");
         fireOrb = getImage("/images/fireOrb.png");
         fire = getImage("/images/fire.png");
         vine = getImage("/images/vine.png");
         leaf = getImage("/images/leaf.png");
         keyMan = getImage("/images/keyMan.png");
         coinMan = getImage("/images/coinMan.png");
         greenMan = getImage("/images/greenMan.png");
         greenPlayer = getImage("/images/greenPlayer.png");
         redPlayer = getImage("/images/redPlayer.png");
         bluePlayer = getImage("/images/bluePlayer.png");
         coin = getImage("/images/coin.png");
         openDoor = getImage("/images/openDoor.png");
         closedDoor = getImage("/images/closedDoor.png");
         flag = getImage("/images/flag.png");
         onOrb = getImage("/images/onOrb.png");
         offOrb = getImage("/images/offOrb.png");
         onBlock = getImage("/images/onBlock.png");
         offBlock = getImage("/images/offBlock.png");
         spikes = getImage("/images/spikes.png");
    }
    
	
}
