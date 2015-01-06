package Runnable;

import java.awt.event.KeyEvent;
import java.util.Iterator;

public class MovementState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator _itr;
	
	public MovementState(Player p, Enemies e){
		_player = p;
		_enemies = e;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_UP){
			Character c = (Character)_itr.next();
			if(c.getPositionY() < 3){
				c.setPosition(c.getPositionX(), c.getPositionY()+1);
			}
			System.out.println(c.getName() + " has moved to " + c.getLocation() + "!");
			if(!_itr.hasNext()){
				setCurrentState(new AttackState());
			}
		}
		else if(E.getKeyCode()==KeyEvent.VK_DOWN){
			Character c = (Character)_itr.next();
			if(c.getPositionY() > 1){
				c.setPosition(c.getPositionX(), c.getPositionY()-1);
			}
			System.out.println(c.getName() + " has moved to " + c.getLocation() + "!");
			if(!_itr.hasNext()){
				setCurrentState(new AttackState());
			}
		}
		else if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			Character c = (Character)_itr.next();
			if(c.getPositionX() < 3){
				c.setPosition(c.getPositionX()+1, c.getPositionY());
			}
			System.out.println(c.getName() + " has moved to " + c.getLocation() + "!");
			if(!_itr.hasNext()){
				setCurrentState(new AttackState());
			}
		}
		else if(E.getKeyCode()==KeyEvent.VK_LEFT){
			Character c = (Character)_itr.next();
			if(c.getPositionY() > 1){
				c.setPosition(c.getPositionX()-1, c.getPositionY());
			}
			System.out.println(c.getName() + " has moved to " + c.getLocation() + "!");
			if(!_itr.hasNext()){
				setCurrentState(new AttackState());
			}
		}
		
	}

	@Override
	public void init() {
		System.out.println("MOVEMENT PHASE");
		_itr = _player.getParty().iterator();
		
	}

}
