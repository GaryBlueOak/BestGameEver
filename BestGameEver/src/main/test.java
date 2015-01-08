package main;

import javax.swing.JFrame;


public class test {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	public static Game sGame;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("BESTGAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // Prevents manual resizing of window
		sGame = new Game(GAME_WIDTH,GAME_HEIGHT);
		frame.add(sGame);
		frame.pack();
		frame.setVisible(true);
		
	}


}