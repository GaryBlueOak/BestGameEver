package main;


import java.awt.Color;
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
		boolean moveFound = false;
		Character[][] battleBoard = _player.getBattleBoard();
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_c.getPositionY() > 0 ){
				if(battleBoard[_c.getPositionX()][_c.getPositionY()-1] == null){
					_c.setPosition(_c.getPositionX(), _c.getPositionY()-1);
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}
				else{
					System.out.println(battleBoard[_c.getPositionX()][_c.getPositionY()-1].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move any higher.");
			}
			
		}
		else if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_c.getPositionY() < 2){
				if(battleBoard[_c.getPositionX()][_c.getPositionY()+1] == null){
					_c.setPosition(_c.getPositionX(), _c.getPositionY()+1);
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}else{
					System.out.println(battleBoard[_c.getPositionX()][_c.getPositionY()+1].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move any lower.");
			}
				
		}
		else if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			if(_c.getPositionX() < 2){
				if(battleBoard[_c.getPositionX()+1][_c.getPositionY()] == null){
					_c.setPosition(_c.getPositionX()+1, _c.getPositionY());
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}else{
					System.out.println(battleBoard[_c.getPositionX()+1][_c.getPositionY()].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move anymore rightwards.");
			}
				
		}
		else if(E.getKeyCode()==KeyEvent.VK_LEFT){
			if(_c.getPositionX() > 0){
				if(battleBoard[_c.getPositionX()-1][_c.getPositionY()] == null){
					_c.setPosition(_c.getPositionX()-1, _c.getPositionY());
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}else{
					System.out.println(battleBoard[_c.getPositionX()-1][_c.getPositionY()].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move an more leftwards.");
			}
			
			
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println(_c.getName() + " held position!");
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
		System.out.println(_c.getName() + "'s move!");
		
	}

	@Override
	public void render(Graphics g) {
		renderStars(g);
		renderTiles(g);
		renderCharacters(g);
		renderEnemies(g);
	}
	
	private void renderCharacters(Graphics g){
		for(Character c: _player.getParty()){
			Image sprite = Resources.testSprite;
			if(c.equals(_c)) sprite = Resources.testSprite2;
			if(c.isDead()) sprite = Resources.testSprite4;
			g.drawImage(sprite, (c.getPositionX()*100)+50, (c.getPositionY()*100)+50, null);	
		}
	}
	
	private void renderEnemies(Graphics g){
		for(Enemy e: _enemies.getEnemies()){
			if(!e.isDead()){
				g.drawImage(Resources.testEnemy, 600, (e.getPosition()*100)+50, null);
			}
		}
	}
	
	private void renderTiles(Graphics g){
		g.drawImage(Resources.whiteTile, 50, 100, null);
		g.drawImage(Resources.whiteTile, 50, 200, null);
		g.drawImage(Resources.whiteTile, 50, 300, null);
		g.drawImage(Resources.whiteTile, 150, 100, null);
		g.drawImage(Resources.whiteTile, 150, 200, null);
		g.drawImage(Resources.whiteTile, 150, 300, null);
		g.drawImage(Resources.whiteTile, 250, 100, null);
		g.drawImage(Resources.whiteTile, 250, 200, null);
		g.drawImage(Resources.whiteTile, 250, 300, null);
		g.drawImage(Resources.whiteTile, 600, 100, null);
		g.drawImage(Resources.whiteTile, 600, 200, null);
		g.drawImage(Resources.whiteTile, 600, 300, null);
	}
	
	private void renderStars(Graphics g){
		g.setColor(Color.white);
		for(int i=60;i<800;i=i+60){
			for(int j=20;j<450;j=j+160){
				g.fillOval(i, j, 3, 3);
			}
		}
		for(int i=30;i<800;i=i+60){
			for(int j=100;j<450;j=j+160){
				g.fillOval(i, j, 3, 3);
			}
		}
	}
}
