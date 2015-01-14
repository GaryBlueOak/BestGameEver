package main;

import items.Item;
import items.Potion;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import specialattacks.*;

public class TrainerState extends State {
	
	private ArrayList<String> _goods;
	private Player _player;
	private int _index = -1;

	
	public TrainerState(Player p){
		_player = p;
		_goods = new ArrayList<String>();
		_goods.add("Fight Training Dummy");
		_goods.add("Workout");
		_goods.add("Combat Training");
	}

	@Override
	public void init() {
		System.out.println("Welcome to the trainer.\nHere you can level up your characters at the cost of gold and experience.\nPress up and down to browse and ENTER to purchase. Press right to check funds.");
		System.out.println("Press space when you are done.");
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_index < (_goods.size())-1){
				_index++;
				System.out.println( _goods.get(_index) + " " + getGoldCost(_index) + "g  " + getExperienceCost(_index) + "exp");
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_index > 0){
				_index--;
				System.out.println( _goods.get(_index) + " " + getGoldCost(_index) + "g  " + getExperienceCost(_index) + "exp");
			}
		}
		
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			System.out.println("You have " + _player.getGold() + "g and " + _player.getExperience() + "exp");
		}
		
		if(E.getKeyCode()==KeyEvent.VK_ENTER){
			
				if(_player.getGold() >= getGoldCost(_index)){
					if(_player.getExperience() >= getExperienceCost(_index)){
						_player.setGold(_player.getGold() - getGoldCost(_index));
						_player.setExperience(_player.getExperience() - getExperienceCost(_index));
						System.out.println("You bought " + _goods.get(_index));
						for(Character character: _player.getParty()){
							character.trainAttack(_index + 1.0);
							character.trainDefense(_index + 1.0);
						}
						setCurrentState(new MenuState(_player));
					}else{
						System.out.println("not enough experience.");
					}
				}
				else{
					System.out.println("not enough money.");
				}
			
		}	
		
		if(E.getKeyCode()==KeyEvent.VK_SPACE){
			setCurrentState(new MenuState(_player));
		}
		
	}
	
	public int getGoldCost(int index){
		switch(index){
			case 0:
				return 10;
			case 1:
				return 50;
			case 2:
				return 100;
		}
		return 0;
	}
	
	public int getExperienceCost(int index){
		switch(index){
		case 0:
			return 10;
		case 1:
			return 50;
		case 2:
			return 100;
		}
		return 0;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}


}
