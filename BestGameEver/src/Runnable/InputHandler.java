package Runnable;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener {

	private State currentState;

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void keyPressed(KeyEvent e) {
		currentState.onKeyPress(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}

