package Runnable;

import java.awt.event.KeyEvent;

public class MenuState extends State {

	private Player _player;
	
	public MenuState(Player p){
		_player = p;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
			System.out.println("You have chosen to battle.");
			Enemies enemies = new Enemies();
			for(Character c : _player.getParty()){
				c.setPosition(2,2);
			}
			setCurrentState(new MovementState(_player,enemies));
			
		}

	@Override
	public void init() {
		System.out.println("Press any key to begin a battle.");
		
	}

}
