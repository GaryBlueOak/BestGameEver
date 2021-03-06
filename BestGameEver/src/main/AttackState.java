package main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class AttackState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator<Character> _itr;
	private Character _c;
	boolean victory = false;
	private int _gold = 0;
	private int _experience = 0;
	private String _toRender = "";
	private int[] _where;
	
	public AttackState(Player p,Enemies e){
		_where = new int[2];
		_where[0] = 400;
		_where[1] = 100;
		_player = p;
		_enemies = e;
		for(Enemy enemy: _enemies.getEnemies()){
			_gold += enemy.getGold();
			_experience += enemy.getExperience();
		}
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			_toRender = _c.getName() +" has attacked!";
			_c.normalAttack(_enemies);
			
		}
		else if (E.getKeyCode()==KeyEvent.VK_UP){
			_c.specialAttack(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_DOWN){
			_c.useItem(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_LEFT){
			_toRender = _c.getName() +" started blocking!";
			System.out.println(_c.getName() + " took a defensive stance!");
		}
		
		if(victory()){
			_toRender = "You are victorious!";
//			renderText("You are victorious!",100,200);
			System.out.println("You are victorious!");
			System.out.println("You have gained " + _gold + " gold and " + _experience + " experience.");
			_player.setExperience(_player.getExperience()+_experience);
			_player.setGold(_player.getGold() + _gold);
			setCurrentState(new MenuState(_player));
		}
		else{
		
			if(_itr.hasNext()){
				_c = _itr.next();
				
				if(_c.isDead() && !_itr.hasNext()){
					enemyPhase();
				}
				while(_c.isDead() && _itr.hasNext()){
					_c =_itr.next();
					if(_c.isDead() && !_itr.hasNext()){
						enemyPhase();
					}
				}
				System.out.println(_c.getName() + "'s move!");
			}
			else{
				enemyPhase();
			}
		}
	}

	@Override
	public void init() {
		System.out.println("ATTACK PHASE **   attack: right   special: up   item: down   defend: left ");
		_itr = _player.getParty().iterator();
		_c = _itr.next();
		
		while(_c.isDead()){
			_c = _itr.next();
		}
		System.out.println(_c.getName() + "'s move!");
		
	}
	
	private boolean victory(){
		boolean victory = true;
		for(Enemy enemy: _enemies.getEnemies()){
			if(!enemy.isDead()){
				victory = false;
			}
		}
		return victory;
	}
	
	private boolean defeat(){
		boolean defeat = true;
		for(Character c: _player.getParty()){
			if(!c.isDead()){
				defeat = false;
			}
		}
		return defeat;
	}
	
	private void enemyPhase(){
		if(_enemies.attack(_player) && !defeat()){
			setCurrentState(new MovementState(_player,_enemies));
		}
		else{
			setCurrentState(new GameOverState());
		}
	}

	@Override
	public void render(Graphics g) {
		renderTiles(g);
		renderCharacters(g);
		renderEnemies(g);
		renderText(g);
	}
	
	private void renderCharacters(Graphics g){
		for(Character c: _player.getParty()){
			Image sprite = Resources.testSprite;
			if(c.equals(_c)) sprite = Resources.testSprite3 ;
			if(c.isDead()) sprite = Resources.testSprite4;
			g.drawImage(sprite, (c.getPositionX()*100)+50, (c.getPositionY()*100)+50, null);	
		}
	}
	
	private void renderEnemies(Graphics g){
		for(Enemy e: _enemies.getEnemies()){
			if(!e.isDead()){
				//g.drawString("TEST", 600, (e.getPosition()*100)+50);
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
		//g.drawString("TEST", 200, 400);
		
	}
	private void renderText(Graphics g){
		g.drawString(_toRender,_where[0],_where[1]);
	}
}
