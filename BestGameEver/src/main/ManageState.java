package main;

import java.awt.event.KeyEvent;
import items.*;
import specialattacks.*;

public class ManageState extends State {
	
	private Player _player;
	int _itemIndex;
	int _specialIndex;
	int _partyIndex;
	private Item _item;
	private SpecialAttack _special;
	private Character _c;
	
	public ManageState(Player p){
		_player = p;
		_itemIndex = -1;
		_specialIndex = -1;
		_partyIndex = -1;
	}

	@Override
	public void init() {
		System.out.println("You can set special attacks and items for your characters. Press up and down to cycle through characters.");
		System.out.println("Press left to cycle through items and right for special attacks. Press s to set special, i to set item, and space when done.");
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_S){
			if(_c!=null && _special!=null){
				_c.setSpecial(_special);
				System.out.println(_c.getName() + " has mastered " + _special.getName());
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_I){
			if(_c!=null && _item!=null){
				_c.setItem(_item);
				System.out.println(_c.getName() + " has equipped " + _item.getName());
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_SPACE){
			setCurrentState(new MenuState(_player));
		}
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_partyIndex > 0){
				_partyIndex--;
				_c = _player.getParty().get(_partyIndex);
				System.out.println(_c.getName());
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_partyIndex < _player.getParty().size()-1){
				_partyIndex++;
				_c = _player.getParty().get(_partyIndex);
				System.out.println(_c.getName());
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			if(_specialIndex < _player.getSpecials().size()-1){
				_specialIndex++;
				_special = _player.getSpecials().get(_specialIndex);
				System.out.println(_special.getName());
			}
			else if(_specialIndex == _player.getSpecials().size()-1){
				_specialIndex = 0;
				_special = _player.getSpecials().get(_specialIndex);
				System.out.println(_special.getName());
			}
		}
		if(E.getKeyCode()==KeyEvent.VK_LEFT){
			if(_itemIndex < _player.getItems().size()-1){
				_itemIndex++;
				_item = _player.getItems().get(_itemIndex);
				System.out.println(_item.getName());
			}
			else if(_itemIndex == _player.getItems().size()-1){
				_itemIndex = 0;
				_item = _player.getItems().get(_itemIndex);
				System.out.println(_item.getName());
			}
		}
		
	}

}
