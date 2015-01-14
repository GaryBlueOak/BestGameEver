package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import items.*;

public class MerchantState extends State {
	
	private Item[] _goods;
	private Player _player;
	private int _index = -1;
	private Item _item;
	
	public MerchantState(Player p, int merchentNumber){
		_player = p;
		String storeName = "Error";
		switch(merchentNumber){
			case 1:
				storeName = _player.getHomeBase().getMerchent().getName();
				_goods = _player.getHomeBase().getMerchent().getGoods();
				break;
			case 2:
				storeName = _player.getHomeBase().getArmory().getName();
				_goods = _player.getHomeBase().getArmory().getGoods();
				break;
			case 3:
				storeName = _player.getHomeBase().getMystic().getName();
				_goods = _player.getHomeBase().getMystic().getGoods();
				break;
			case 4:
				storeName = _player.getHomeBase().getBlackMarket().getName();
				_goods = _player.getHomeBase().getBlackMarket().getGoods();
				break;
		}
		
		System.out.println("\n/////////////////////\nWelcome to the "+ storeName + "!\nPress up and down to browse and ENTER to purchase."
				+ "\nPress right to check funds.\nPress 'I' to see all available goods.");
		System.out.println("Press space when you are done.");
	}

	@Override
	public void init() {
		
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_index < (_goods.length)-1){
				_index++;
				_item = _goods[_index];
				System.out.println(_item.getName() + " " + _item.getCost() + "g");
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_index > 0){
				_index--;
				_item = _goods[_index];
				System.out.println(_item.getName() + " " + _item.getCost() + "g");
			}
		}
		
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			System.out.println("You have " + _player.getGold() + "g");
		}
		if(E.getKeyChar()=='i'){

			printShop();
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
	
	public void printShop(){
		System.out.flush();
		System.out.println("\n\nAvalable Goods:");
		for(Item item: _goods){
			System.out.println(item.getCost() + "g" + "\t" + item.getName());
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
