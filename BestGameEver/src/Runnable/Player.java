package Runnable;

import java.util.ArrayList;

public class Player {
	private ArrayList<Character> _party;
	public static int MAX_PARTY_SIZE = 4;
	private int _gold;
	
	public Player(){
		_gold = 100;
		_party = new ArrayList<Character>();
		_party.add(new Character("Test1"));
		_party.add(new Character("Test2"));
		_party.add(new Character("Test3"));
		_party.add(new Character("Test4"));
	}
}
