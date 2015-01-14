package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Resources {

	public static BufferedImage testSprite, testSprite2, testSprite3, testEnemy;
	
	public static void load(){
		testSprite = loadImage("p2_stand.png");
		testEnemy = loadImage("p3_front.png");
		testSprite2 = loadImage("p2_jump.png");
		testSprite3 = loadImage("p2_duck.png");
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
