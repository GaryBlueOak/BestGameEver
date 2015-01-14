package main;


import java.awt.Graphics;
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
			System.out.println("Press up to visit the Merchant.");
			System.out.println("Press M to visit the Mystic.");
			System.out.println("Press A to visit the Armory.");
			System.out.println("Press B to visit the Black Market.");
			System.out.println("Press down to visit the trainer.");
			System.out.println("Press right to manage your party.");
			System.out.println("Press left to check party status.");
		}
		if(E.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println("You have chosen to battle.");
			Enemies enemies = new Enemies();
			_player.getParty().get(0).setPosition(0, 1);
			_player.getParty().get(1).setPosition(1,0);
			_player.getParty().get(2).setPosition(1,2);
			_player.getParty().get(3).setPosition(2,1);
			setCurrentState(new MovementState(_player,enemies));
			_player.advanceDay();
		}
		
		if(E.getKeyCode()==KeyEvent.VK_UP){
			setCurrentState(new MerchantState(_player, 1));
		}
		
		if(E.getKeyChar()=='m'){
			setCurrentState(new MerchantState(_player, 3));
		}
		
		if(E.getKeyChar()=='a'){
			setCurrentState(new MerchantState(_player, 2));

		}
		
		if(E.getKeyChar()=='b'){
			setCurrentState(new MerchantState(_player, 4));
		}
		
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			setCurrentState(new TrainerState(_player));
			_player.advanceDay();
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
		System.out.println("It is currently day " + _player.getDayNumber() + ".\nPress Enter.");
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	//scout to look for other recruits
	//day to day interactions, training etc until some event
	//chance of random event each day, or after x amount of days
}
