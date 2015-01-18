package main;


import java.awt.Color;
import java.awt.Font;
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
			setCurrentState(new MysticState(_player));
		}
		
		if(E.getKeyChar()=='a'){
			setCurrentState(new MerchantState(_player, 2));

		}
		
		if(E.getKeyChar()=='b'){
			setCurrentState(new MerchantState(_player, 3));
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
		//System.out.println("It is currently day " + _player.getDayNumber() + ".\nPress Enter.");
		
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.setColor(Color.WHITE);
		g.drawRect(20, 20, 400, 50);
		g.fillRect(20, 20, 400, 50);
		g.setColor(Color.GREEN);
		g.fillRect(20, 100, 150, 300);
		g.setColor(Color.GREEN);
		g.fillRect(200, 100, 150, 150);
		g.setColor(Color.GREEN);
		g.fillRect(380, 100, 150, 150);
		g.fillRect(200, 270,100, 100);
		g.fillRect(330, 270, 200, 130);
		g.setColor(Color.YELLOW);
		g.fillRect(560, 100, 150, 150);
		g.fillRect(560, 270, 150, 130);
		g.setColor(Color.BLACK);
		g.drawString("Welcome to day " + _player.getDayNumber(), 30, 50);
		g.drawString("Merchant", 55, 190);
		g.drawString("Press up to visit", 25, 220);
		g.drawString("Mystic", 240, 170);
		g.drawString("Press M to visit", 210, 200);
		g.drawString("Armory", 420, 170);
		g.drawString("Press A to visit", 390, 200);
		g.drawString("Battle", 600, 170);
		g.drawString("Press space", 570, 200);
		g.drawString("Manage Party", 570, 330);
		g.drawString("Press Right", 570, 360);
		g.drawString("Trainer", 400, 330);
		g.drawString("Press Down", 370, 360);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString("Black Market", 205, 320);
		g.drawString("Press B", 205, 340);
	}

	//scout to look for other recruits
	//day to day interactions, training etc until some event
	//chance of random event each day, or after x amount of days
}
