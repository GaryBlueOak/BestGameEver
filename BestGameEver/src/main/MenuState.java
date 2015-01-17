package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class MenuState extends State {

	private Player _player;
	
	public MenuState(Player p){
		_player = p;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		Resources.laser3Sound.play();
		Enemies enemies = new Enemies(_player.difficulty);
		_player.getParty().get(0).setPosition(0, 1);
		_player.getParty().get(1).setPosition(1,0);
		_player.getParty().get(2).setPosition(1,2);
		_player.getParty().get(3).setPosition(2,1);
		setCurrentState(new MovementState(_player,enemies));
	}

	@Override
	public void init() {

	}

	@Override
	public void render(Graphics g) {
		renderStars(g);
		g.setFont(Resources.font);
		g.setColor(Color.yellow);
		g.drawString("BOLT SQUADRON",180,200);
		Font newFont = Resources.font.deriveFont((float)25.0);
		g.setFont(newFont);
		g.drawString("Press any key to start",250,400);
		
	}
	
	private void renderStars(Graphics g){
		g.setColor(Color.white);
		g.fillOval(400,150,3,3);
		g.fillOval(600,300,3,3);
		g.fillOval(500,400,3,3);
		g.fillOval(700,200,3,3);
		g.fillOval(800,225,3,3);
		g.fillOval(900,200,3,3);
		g.fillOval(100,350,3,3);
		g.fillOval(500,120,3,3);
		g.fillOval(200,500,3,3);
		g.fillOval(300,460,3,3);
		g.fillOval(670,380,3,3);
		g.fillOval(500,550,3,3);
		g.fillOval(200,130,3,3);
		g.fillOval(900,560,3,3);
		g.fillOval(800,580,3,3);
		g.fillOval(650,460,3,3);
		g.fillOval(400,300,3,3);
		g.fillOval(800,500,3,3);
		g.fillOval(100,100,3,3);
		g.fillOval(700,80,3,3);
		g.fillOval(400,50,3,3);
		g.fillOval(500,300,3,3);
		g.fillOval(300,560,3,3);
		g.fillOval(100,500,3,3);
		g.fillOval(200,500,3,3);
		
	}

	//scout to look for other recruits
	//day to day interactions, training etc until some event
	//chance of random event each day, or after x amount of days
}
