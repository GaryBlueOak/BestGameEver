package main;


import java.awt.event.KeyEvent;

public class GameOverState extends State {

	@Override
	public void init() {
		System.out.println("All of your characters have been slain.");
		System.out.println("GAME OVER");
		
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		// TODO Auto-generated method stub
		
	}

}
