package main;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {
	private Thread gameThread;
	private volatile boolean running;
	private volatile State currentState;
	private int gameWidth;
	private int gameHeight;
	

	private InputHandler inputHandler;

	public Game(int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		setPreferredSize(new Dimension(gameWidth, gameHeight));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus();
	}
	      
	public void addNotify() {
		super.addNotify();
		initInput();
		setCurrentState(new MenuState(new Player()));
		initGame();
	}
	

	public void setCurrentState(State newState) {
		System.gc();
		newState.init();
		currentState = newState;
		inputHandler.setCurrentState(currentState);
	}

	private void initGame() {
		running = true;
		gameThread = new Thread(this, "Game Thread");
		gameThread.start();
	}

	@Override
	public void run() {
		while (running) {
			
		}
		// End game immediately when running becomes false.
		System.exit(0);
	}

	public void exit() {
		running = false;
	}

	private void initInput() {
		inputHandler = new InputHandler();
		addKeyListener(inputHandler);
	}

}
