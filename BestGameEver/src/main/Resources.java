package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Resources {

	public static BufferedImage testSprite, testSprite2, testSprite3, testSprite4, testEnemy, grassTile, orangeTile, whiteTile;
	
	public static void load(){
		testSprite = loadImage("p2_stand.png");
		testEnemy = loadImage("p3_front.png");
		testSprite2 = loadImage("p2_jump.png");
		testSprite3 = loadImage("p2_duck.png");
		testSprite4 = loadImage("p2_hurt.png");
		grassTile = loadImage("tileGrass.png");
		orangeTile = loadImage("tileAutumn_full.png");
		whiteTile = loadImage("tileSnow_full.png");
		
	}
	
	private static BufferedImage loadImage(String filename) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Resources.class
					.getResourceAsStream("/resources/" + filename));
		} catch (IOException e) {
			System.out.println("Error while reading: " + filename);
			e.printStackTrace();
		}
		return img;
	}
}
