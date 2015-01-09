package main;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import items.*;

public class MerchantState extends State {
	
	private ArrayList<Item> _goods;
	private Player _player;
	private int _index = -1;
	private Item _item;
	
	public MerchantState(Player p){
		_player = p;
		_goods = new ArrayList<Item>();
		_goods.add(new Potion());
	}

	@Override
	public void init() {
		System.out.println("Welcome to the merchant. Press up and down to browse and ENTER to purchase. Press right to check funds.");
		System.out.println("Press space when you are done.");
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_index < (_goods.size())-1){
				_index++;
				_item = _goods.get(_index);
				System.out.println(_item.getName() + " " + _item.getCost() + "g");
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_index > 0){
				_index++;
				_item = _goods.get(_index);
				System.out.println(_item.getName() + " " + _item.getCost() + "g");
			}
		}
		
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			System.out.println("You have " + _player.getGold() + "g");
		}
		
		if(E.getKeyCode()==KeyEvent.VK_ENTER){
			if(_item!=null){
				if(_player.getGold() >= _item.getCost()){
					_player.addItem(_item);
					_player.setGold(_player.getGold() - _item.getCost());
					System.out.println("You bought a " + _item.getName());
				}
				else{
					System.out.println("not enough money.");
				}
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_SPACE){
			setCurrentState(new MenuState(_player));
		}
		
	}

}
