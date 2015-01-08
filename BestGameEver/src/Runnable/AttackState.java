package Runnable;

import java.awt.event.KeyEvent;
import java.util.Iterator;

public class AttackState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator _itr;
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
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			showInfo(_player);
		}
		
		if(victory()){
			System.out.println("You are victorious!");
			setCurrentState(new MenuState(_player));
		}
		else{
		
			if(_itr.hasNext()){
				_c = (Character)_itr.next();
				if(_c.isDead() && !_itr.hasNext()){
					enemyPhase();
				}
				while(_c.isDead() && _itr.hasNext()){
					_c = (Character)_itr.next();
					if(_c.isDead() && !_itr.hasNext()){
						enemyPhase();
					}
				}
			}
			else{
				enemyPhase();
			}
		}
	}

	@Override
	public void init() {
		System.out.println("ATTACK PHASE ** PRESS RIGHT ARROW TO ATTACK OR SPACE TO SHOW INFO");
		_itr = _player.getParty().iterator();
		_c = (Character)_itr.next();
		while(_c.isDead()){
			_c = (Character)_itr.next();
		}
		
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
}
