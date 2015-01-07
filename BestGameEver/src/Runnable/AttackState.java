package Runnable;

import java.awt.event.KeyEvent;
import java.util.Iterator;

public class AttackState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator _itr;
	boolean victory = false;
	
	public AttackState(Player p,Enemies e){
		_player = p;
		_enemies = e;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			Character c = (Character)_itr.next();
			c.normalAttack(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_UP){
			Character c = (Character)_itr.next();
			c.specialAttack(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_DOWN){
			Character c = (Character)_itr.next();
			c.useItem(_enemies);
		}
		else if (E.getKeyCode()==KeyEvent.VK_LEFT){
			Character c = (Character)_itr.next();
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			showInfo(_player);
		}
		
		if(checkVictory(_enemies)){
			System.out.println("You are victorious!");
			setCurrentState(new MenuState(_player));
		}
		
		if(!_itr.hasNext()){
			_enemies.attack(_player);
			setCurrentState(new MovementState(_player,_enemies));
		}
		
	}

	@Override
	public void init() {
		System.out.println("ATTACK PHASE ** PRESS RIGHT ARROW TO ATTACK OR SPACE TO SHOW INFO");
		_itr = _player.getParty().iterator();
		
	}
	
	private boolean checkVictory(Enemies e){
		boolean victory = true;
		for(Enemy enemy: e.getEnemies()){
			if(!enemy.isDead()){
				victory = false;
			}
		}
		return victory;
	}

}
