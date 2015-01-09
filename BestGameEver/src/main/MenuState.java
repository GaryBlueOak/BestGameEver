package main;


import java.awt.event.KeyEvent;


public class MenuState extends State {

	private Player _player;
	
	public MenuState(Player p){
		_player = p;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_ENTER){
			System.out.println("Press space to start a battle.");
			System.out.println("Press up to visit the merchant.");
			System.out.println("Press down to visit the trainer.");
			System.out.println("Press right to manage your party.");
			System.out.println("Press left to check party status.");
		}
		if(E.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println("You have chosen to battle.");
			Enemies enemies = new Enemies();
			for(Character c : _player.getParty()){
				c.setPosition(2,2);
			}
			setCurrentState(new MovementState(_player,enemies));
		}
		if(E.getKeyCode()==KeyEvent.VK_UP){
			setCurrentState(new MerchantState(_player));
		}
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			setCurrentState(new TrainerState(_player));
		}
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			setCurrentState(new ManageState(_player));
		}
		if(E.getKeyCode()==KeyEvent.VK_LEFT){
			for(Character c: _player.getParty()){
				System.out.println(c.checkStatus());
			}
		}
			
	}

	@Override
	public void init() {
		System.out.println("You are at home base. Press Enter for options.");
		
	}

}
