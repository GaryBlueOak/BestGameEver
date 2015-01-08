package classes;

import java.util.ArrayList;

public class Player {
	private ArrayList<Character> _party;
	public static int MAX_PARTY_SIZE = 4;
	private int _gold;
	
	public Player(){
		_gold = 100;
		_party = new ArrayList<Character>();
		_party.add(new Character("Marth"));
		_party.add(new Character("Ike"));
		_party.add(new Character("Roy"));
		_party.add(new Character("Lynn"));
	}
	
	public boolean addToParty(Character chr){
		if(_party.size()>3){
			System.out.println("Your party is too full!");
			return false;
		}else{
			System.out.println(chr.getName()+ " has joined the party!");
			_party.add(chr);
			return true;
		}
	}
	
	public void statusCheck(){
		Character deadChar = checkHP();
		while(deadChar != null){
			_party.remove(deadChar);
			System.out.println(deadChar.getName() + " has died.");
			deadChar = checkHP();
		}
	}
	
	private Character checkHP(){
		for(Character chr: _party){
			if(chr.getHealth() < 1){
				return chr;
			}
		}
		return null;
	}
	
	public ArrayList<Character> getParty(){
		return _party;
	}
}
