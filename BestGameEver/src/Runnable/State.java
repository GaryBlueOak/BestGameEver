package Runnable;

import java.awt.event.KeyEvent;

public abstract class State {
	
	public abstract void init();
	
	public abstract void onKeyPress(KeyEvent E);
	
	public void setCurrentState(State newState) {
		test.sGame.setCurrentState(newState);
	}
	
	public void showInfo(Player p){
		for(Character c: p.getParty()){
			System.out.println(c.checkInfo());
		}
	}

}
