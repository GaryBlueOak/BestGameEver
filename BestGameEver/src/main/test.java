package main;


import javax.swing.JFrame;


public class test {
	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 600;
	public static Game sGame;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("BOLT SQUADRON");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); // Prevents manual resizing of window
		sGame = new Game(GAME_WIDTH,GAME_HEIGHT);
		frame.add(sGame);
		frame.pack();
		frame.setVisible(true);
		
	}


}