package Runnable;

import java.awt.event.KeyEvent;

public class AttackState extends State {

	@Override
	public void onKeyPress(KeyEvent E) {
		System.out.println("You are attacking!");
		
	}

	@Override
	public void init() {
		System.out.println("ATTACK PHASE");
		
	}

}
