package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;

public class MovementState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator _itr;
	private Character _c;
	
	public MovementState(Player p, Enemies e){
		_player = p;
		_enemies = e;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		Resources.moveSound.play();
		boolean moveFound = false;
		Character[][] battleBoard = _player.getBattleBoard();
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_c.getPositionY() > 0 ){
				if((battleBoard[_c.getPositionX()][_c.getPositionY()-1] == null) || battleBoard[_c.getPositionX()][_c.getPositionY()-1].isDead()){
					_c.setPosition(_c.getPositionX(), _c.getPositionY()-1);
					moveFound = true;
				}
			}
		}
		else if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_c.getPositionY() < 2){
				if((battleBoard[_c.getPositionX()][_c.getPositionY()+1] == null) || battleBoard[_c.getPositionX()][_c.getPositionY()+1].isDead()){
					_c.setPosition(_c.getPositionX(), _c.getPositionY()+1);
					moveFound = true;
				}
			}			
		}
		else if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			if(_c.getPositionX() < 2){
				if((battleBoard[_c.getPositionX()+1][_c.getPositionY()] == null) || battleBoard[_c.getPositionX()+1][_c.getPositionY()].isDead()){
					_c.setPosition(_c.getPositionX()+1, _c.getPositionY());
					moveFound = true;
				}
			}	
		}
		else if(E.getKeyCode()==KeyEvent.VK_LEFT){
			if(_c.getPositionX() > 0){
				if((battleBoard[_c.getPositionX()-1][_c.getPositionY()] == null) || battleBoard[_c.getPositionX()-1][_c.getPositionY()].isDead()){
					_c.setPosition(_c.getPositionX()-1, _c.getPositionY());
					moveFound = true;
				}
			}
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			moveFound = true;
		}
		if(moveFound){
			if(_itr.hasNext()){
				_c = (Character)_itr.next();
				if(_c.isDead() && !_itr.hasNext()){
					setCurrentState(new AttackState(_player,_enemies));
				}
				while(_c.isDead() && _itr.hasNext()){
					_c = (Character)_itr.next();
					if(_c.isDead() && !_itr.hasNext()){
						setCurrentState(new AttackState(_player,_enemies));
					}
				}
				System.out.println(_c.getName() + "'s move!");
			}
			else{
				setCurrentState(new AttackState(_player,_enemies));
			}
		}	
	}

	@Override
	public void init() {
		System.out.println("MOVEMENT PHASE **   move: arrow keys   hold: space");
		_itr = _player.getParty().iterator();
		_c = (Character)_itr.next();
		while(_c.isDead()){
			_c = (Character)_itr.next();
		}
	}

	@Override
	public void render(Graphics g) {
		renderText(g);
		renderStars(g);
		renderEnemies(g);
		renderCharacters(g);
	}
	
	private void renderText(Graphics g){
		g.setFont(Resources.font);
		g.setColor(Color.white);
		Font newFont = Resources.font.deriveFont((float)25.0);
		g.setFont(newFont);
		g.drawString("Use the arrow keys to move or space to stay", 30, 550);
		
	}
	
	private void renderCharacters(Graphics g){
		for(Character c: _player.getParty()){
			c.update();
			Image icon = Resources.moveIcon;
			g.drawImage(c.getImage(), c.getXcrd(), c.getYcrd(), null);
			c.getLaser().update();
			g.drawImage(c.getLaserImage(),c.getLaser().x,c.getLaser().y,null);
			g.drawImage(c.getFirstDigit(),c.getXcrd()+50,c.getYcrd()+70,null);
			g.drawImage(c.getSecondDigit(),c.getXcrd()+71,c.getYcrd()+70,null);
			if(c.equals(_c)){
				g.drawImage(icon, (c.getPositionX()*100)+100, (c.getPositionY()*150)+100,null);
			}
			
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
