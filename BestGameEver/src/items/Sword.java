package items;

import java.util.Random;

import main.Character;
import main.Enemies;

public class Sword extends Item{
	private String _name = "Sword";
	private String _description = "";
	private int _durability = 50; // good for 50 swings
	private int _damage = 10; // possibly thinking that actual damage is _damage/(some number)
	//maybe add some rare name that adds percent health damage
	public Sword(){
		generateInfo();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return _name;
	}

	@Override
	public void use(Character c, Enemies e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return _description;
	}
	
	public int getDurability(){
		return _durability;
	}
	
	public int getDamage(){
		return _damage;
	}
	
	private void generateInfo(){
			Random rand = new Random();
		// these percentages are realllly wrong. im stupid
			int chance = rand.nextInt(5)+1;
			switch(chance){
				case 1: 
					_name = "Punishing " + _name;
					_description = " with incredible power.";
					_durability = _durability - (_durability/5); // 20% reduction
					_damage = _damage + (_damage/4); // 20 % increase in power
					break;
				case 2: 
					_name = "" + _name;
					_description = ".";
					_durability = _durability;  // left here for possible tweaking
					_damage = _damage; // base strength
					break;
				case 3: 
					_name = "Vampiric " + _name;
					_description = " with a unquenchable thirst.";
					_durability = _durability - (_durability/2); // 50% reduction
					_damage = _damage; // base strength
					break;
				case 4: 
					_name = "Reliable " + _name;
					_description = " made very well.";
					_durability = _durability + (_durability/5); // 20% increase
					_damage = _damage; // base strength
					break;
				case 5: 
					_name = "Cursed " + _name;
					_description = "......";
					_durability = _durability - (_durability - (_durability/4)); // 75% reduction;
					_damage = _damage * 2; // twice as strong.
					break;
			}
			chance = rand.nextInt(3)+1;
			switch(chance){
			case 1:
				_description = "A rusty sword" + _description;
				_name = "Rusty " + _name;
				_durability = _durability - (_durability/5); // 20% reduction
				_damage = _damage - (_damage/4); // 20 % decrease in power
				break;
			case 2: 
				_description = "A sword" + _description;
				break;
				
			case 3:
				_description = "A sharp sword" + _description;
				_name = "Sharp " + _name;
				_durability = _durability + (_durability/5); // 20% increase
				_damage = _damage + (_damage/4); // 20 % increase in power
				break;
			}
		}

	@Override
	public boolean isEquipable() {
		// TODO Auto-generated method stub
		return true;
	}
}
