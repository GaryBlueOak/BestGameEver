package Runnable;

import java.awt.event.KeyEvent;
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
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_c.getPositionY() < 3){
				_c.setPosition(_c.getPositionX(), _c.getPositionY()+1);
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
		}
		else if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_c.getPositionY() > 1){
				_c.setPosition(_c.getPositionX(), _c.getPositionY()-1);
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");	
		}
		else if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			if(_c.getPositionX() < 3){
				_c.setPosition(_c.getPositionX()+1, _c.getPositionY());
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");	
		}
		else if(E.getKeyCode()==KeyEvent.VK_LEFT){
			if(_c.getPositionX() > 1){
				_c.setPosition(_c.getPositionX()-1, _c.getPositionY());
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
			
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println(_c.getName() + " held position!");
		}
		
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
		}
		else{
			setCurrentState(new AttackState(_player,_enemies));
		}
		
		
	}

	@Override
	public void init() {
		System.out.println("MOVEMENT PHASE **  PRESS ARROW KEYS TO MOVE OR SPACE TO HOLD POSITION");
		_itr = _player.getParty().iterator();
		_c = (Character)_itr.next();
		while(_c.isDead()){
			_c = (Character)_itr.next();
		}
		
	}

}
