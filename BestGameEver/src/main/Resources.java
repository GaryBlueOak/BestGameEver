package main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;


public class Resources {

	public static BufferedImage fighter1,enemy1,enemy2,enemy3,enemy4,moveIcon,attackIcon,laser,laser2,laserHit,death1,death2,death3;
	public static BufferedImage zero,one,two,three,four,five,six,seven,eight,nine;
	public static AudioClip laserSound,laser2Sound,laser3Sound,moveSound,deathSound;
	public static Font font;
	
	public static void load(){
		fighter1 = loadImage("playerShip1_blue.png");
		enemy1 = loadImage("enemyGreen1.png");
		enemy2 = loadImage("enemyBlue5.png");
		enemy3 = loadImage("enemyRed2.png");
		enemy4 = loadImage("enemyBlack3.png");
		moveIcon = loadImage("bolt_gold.png");
		attackIcon = loadImage("crossair_red.png");
		laser = loadImage("laserBlue07.png");
		laser2 = loadImage("laserRed10.png");
		laserHit = loadImage("laserBlue08.png");
		laser3Sound = loadSound("laser5.wav");
		laser2Sound = loadSound("laser9.wav");
		laserSound = loadSound("laser1.wav");
		moveSound = loadSound("lowRandom.wav");
		death1 = loadImage("playerShip1_damage3.png");
		death2 = loadImage("playerShip1_damage1.png");
		death3 = loadImage("playerShip3_damage1.png");
		deathSound = loadSound("explosion.wav");
		zero = loadImage("numeral0.png");
		one = loadImage("numeral1.png");
		two = loadImage("numeral2.png");
		three = loadImage("numeral3.png");
		four = loadImage("numeral4.png");
		five = loadImage("numeral5.png");
		six = loadImage("numeral6.png");
		seven = loadImage("numeral7.png");
		eight = loadImage("numeral8.png");
		nine = loadImage("numeral9.png");
		font = loadFont("kenpixel_future_square.ttf");
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
	
	private static AudioClip loadSound(String filename) {
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);
	}
	
	private static Font loadFont(String filename){
		Font future = new Font("Ariel",1000,1000);
		InputStream file = Resources.class.getResourceAsStream("/resources/" + filename);
		try {
			future = Font.createFont(Font.TRUETYPE_FONT,file);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Font newFont = future.deriveFont((float) 50.0);
		return newFont;
	}
}
