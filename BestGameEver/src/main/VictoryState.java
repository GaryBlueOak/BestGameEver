package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class VictoryState extends State {
	
	private Player _player;
	private Enemies _enemies;
	
	public VictoryState(Player p, Enemies e){
		_player = p;
		_enemies = e;
	}

	@Override
	public void init() {
		_player.difficulty += 10;
		for(Character c: _player.getParty()){
			c.gainHealth(5);
		}	
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		Enemies enemies = new Enemies(_player.difficulty);
		setCurrentState(new MovementState(_player,enemies));
	}

	@Override
	public void render(Graphics g) {
		renderText(g);
		renderStars(g);
		renderCharacters(g);
		renderEnemies(g);
	}
	
	private void renderCharacters(Graphics g){
		for(Character c: _player.getParty()){
			c.update();
			g.drawImage(c.getImage(), c.getXcrd(), c.getYcrd(), null);
			c.getLaser().update();
			g.drawImage(c.getLaserImage(),c.getLaser().x,c.getLaser().y,null);
			g.drawImage(c.getFirstDigit(),c.getXcrd()+50,c.getYcrd()+70,null);
			g.drawImage(c.getSecondDigit(),c.getXcrd()+71,c.getYcrd()+70,null);
		}	
	}
	
	private void renderEnemies(Graphics g){
		for(Enemy e: _enemies.getEnemies()){
			e.update();
			g.drawImage(e.getImage(), e.getXcrd(), e.getYcrd(), null);
			e.getLaser().update();
			g.drawImage(Resources.laser2,e.getLaser().x,e.getLaser().y,null);
			g.drawImage(e.getFirstDigit(),e.getXcrd()+85,e.getYcrd()+35,null);
			g.drawImage(e.getSecondDigit(),e.getXcrd()+106,e.getYcrd()+35,null);
		}
	}
	
	private void renderText(Graphics g){
		g.setFont(Resources.font);
		g.setColor(Color.white);
		g.drawString("Victory!",350,200);
		Font newFont = Resources.font.deriveFont((float)25.0);
		g.setFont(newFont);
		g.drawString("Press any key to begin the next wave", 100, 550);
		
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

}
