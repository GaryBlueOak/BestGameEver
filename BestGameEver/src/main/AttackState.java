package main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class AttackState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator<Character> _itr;
	private Character _c;
	boolean victory = false;
	
	public AttackState(Player p,Enemies e){
		_player = p;
		_enemies = e;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			_c.normalAttack(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_UP){
			_c.specialAttack(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_DOWN){
			_c.useItem(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_LEFT){
			showInfo(_player);
		}
		
		if(victory()){
			System.out.println("You are victorious!");
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
		System.out.println("ATTACK PHASE **   attack: right   special: up   item: down   info: left ");
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
		renderCharacters(g);
		renderEnemies(g);
	}
	
	private void renderCharacters(Graphics g){
		for(Character c: _player.getParty()){
			if(!c.isDead()){
				g.drawImage(Resources.testSprite, (c.getPositionX()*100)+50, (c.getPositionY()*100)+100, null);
			}
		}
	}
	
	private void renderEnemies(Graphics g){
		for(Enemy e: _enemies.getEnemies()){
			if(!e.isDead()){
				g.drawImage(Resources.testEnemy, 600, e.getPosition()*100, null);
			}
		}
	}
}
