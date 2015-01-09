package main;

import items.Item;
import items.Potion;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import specialattacks.*;

public class TrainerState extends State {
	
	private ArrayList<SpecialAttack> _goods;
	private Player _player;
	private int _index = -1;
	private SpecialAttack _special;
	
	public TrainerState(Player p){
		_player = p;
		_goods = new ArrayList<SpecialAttack>();
		_goods.add(new MagicArrow());
	}

	@Override
	public void init() {
		System.out.println("Welcome to the trainer. Press up and down to browse and ENTER to purchase. Press right to check funds.");
		System.out.println("Press space when you are done.");
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_index < (_goods.size())-1){
				_index++;
				_special = _goods.get(_index);
				System.out.println(_special.getName() + " " + _special.getCost() + "g");
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_index > 0){
				_index++;
				_special = _goods.get(_index);
				System.out.println(_special.getName() + " " + _special.getCost() + "g");
			}
		}
		
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			System.out.println("You have " + _player.getGold() + "g");
		}
		
		if(E.getKeyCode()==KeyEvent.VK_ENTER){
			if(_special!=null){	
				if(_player.getGold() >= _special.getCost()){
					_player.addSpecial(_special);
					_player.setGold(_player.getGold() - _special.getCost());
					System.out.println("You bought " + _special.getName());
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
