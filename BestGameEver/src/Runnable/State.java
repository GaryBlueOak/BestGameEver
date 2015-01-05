package Runnable;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

	public abstract void onKeyPress(KeyEvent e);

	public void setCurrentState(State newState) {
		
	}
}
