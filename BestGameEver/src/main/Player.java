package main;



import items.Coin;

import java.util.ArrayList;

import specialattacks.*;
import items.*;


public class Player {
	private ArrayList<Character> _party;
	private ArrayList<SpecialAttack> _specials;
	private ArrayList<Item> _items;
	public static int MAX_PARTY_SIZE = 4;
	private int _gold;
	//private Character[][] _battleBoard;
	
	public Player(){
//		_battleBoard = new Character[3][3];
		_gold = 100;
		_specials = new ArrayList<SpecialAttack>();
		_items = new ArrayList<Item>();
		_specials.add(new Splash());
		_items.add(new Coin());
		_party = new ArrayList<Character>();
		_party.add(new Character("Eliwood"));
		_party.add(new Character("Ike"));
		_party.add(new Character("Roy"));
		_party.add(new Character("Lyn"));
		//
		_party.get(0).setPosition(0, 1);
		_party.get(1).setPosition(1,0);
		_party.get(2).setPosition(1,2);
		_party.get(3).setPosition(2,1);
		//
		for(Character c: _party){
			c.setSpecial(new Splash());
			c.setItem(new Coin());
		}
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
	
	public int getGold(){
		return _gold;
	}
	
	public void setGold(int gold){
		_gold = gold;
	}
	
	public void addSpecial(SpecialAttack s){
		_specials.add(s);
	}
	
	public void addItem(Item i){
		_items.add(i);
	}
	
	public Character[][] getBattleBoard(){
		return updateBattleBoard();
	}
	private Character[][] updateBattleBoard(){
		Character[][] battleBoard = new Character[3][3];
		for(Character character: _party){
			battleBoard[character.getPositionX()][character.getPositionY()] = character;
		}
		return battleBoard;
	}
	
	public ArrayList<SpecialAttack> getSpecials(){
		return _specials;
	}
	
	public ArrayList<Item> getItems(){
		return _items;
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
