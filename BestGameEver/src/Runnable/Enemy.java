package Runnable;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
	
	private String _name;
	private double _attack;
	private double _defense;
	private int _health;
	private int _gold;
	private int _exp;
	private int _position;
	private boolean _dead;
	
	public Enemy(int enemyID, int difficulty){
		makeEnemy(enemyID, difficulty);
		_dead = false;
	}
	
	public void setPosition(int y){
		_position = y;
	}
	
	public int getPosition(){
		return _position;
	}
	
	public boolean isDead(){
		return _dead;
	}
	
	public String location(){
		String location = "";
		switch(_position){
		case 1:
			location += "on the bottom!";
			break;
		case 2:
			location += "in the middle!";
			break;
		case 3:
			location += "on the top!";
			break;
		}
		return location;
	}
	
	private void makeEnemy(int enemyID, int difficulty){
		double statModifier = getStatModifier(difficulty);
		switch(enemyID){
			// names subject to change
			// WORK IN PROGRESS! MUCH BALANCING GOING TO BE NEEDED
			case 1: 
				_name = getNameModifier(difficulty) + "Rat";
				_attack = 2 + (1*statModifier);
				_defense = 1 + (1*statModifier);
				_health = 4 + (int)(2*statModifier);
				_gold = 10 + (int)(3*statModifier);
				_exp = 7 + (int)(5*statModifier);
				break;
			case 2:
				_name = getNameModifier(difficulty) + "Bat";
				_attack = 2 + (2*statModifier);
				_defense = 2 + (1*statModifier);
				_health = 5 + (int)(2*statModifier);
				_gold = 14 + (int)(5*statModifier);
				_exp = 9 + (int)(7*statModifier);
				break;
			case 3:
				_name = getNameModifier(difficulty) + "Goblin";
				_attack = 3 + (4*statModifier);
				_defense = 2 + (3*statModifier);
				_health = 8 + (int)(7*statModifier);
				_gold = 20 + (int)(14*statModifier);
				_exp = 16 + (int)(10*statModifier);
				break;
			case 4:
				_name = getNameModifier(difficulty) + "Enemy Soldier";
				_attack = 5 + (4*statModifier);
				_defense = 4 + (4*statModifier);
				_health = 12 + (int)(7*statModifier);
				_gold = 30 + (int)(14*statModifier);
				_exp = 23 + (int)(10*statModifier);
				break;
			case 5:
				_name = getNameModifier(difficulty) + "Ogre";
				_attack = 7 + (5*statModifier);
				_defense = 4 + (4*statModifier);
				_health = 22 + (int)(10*statModifier);
				_gold = 44 + (int)(30*statModifier);
				_exp = 37 + (int)(20*statModifier);
				break;
			case 6:
				_name = getNameModifier(difficulty) + "Drake";
				_attack = 12 + (7*statModifier);
				_defense = 12 + (7*statModifier);
				_health = 50 + (int)(14*statModifier);
				_gold = 66 + (int)(50*statModifier);
				_exp = 50 + (int)(27*statModifier);
				break;
		}
	}
	
	private double getStatModifier(int difficulty){
		double statModifier = 1.0;
		if(difficulty >5)
			difficulty = 5;
		if(difficulty <1)
			difficulty = 1;
		
		switch(difficulty){
			case 1:
				statModifier = 0.6;
				break;
			case 2:
				statModifier = 0.8;
				break;
			case 3:
				statModifier = 1.0;
				break;
			case 4:
				statModifier = 1.2;
				break;
			case 5:
				statModifier = 1.4;
				break;
		}
		return statModifier; 
	}
	
	public double getAttack(){
		return _attack;
	}
	
	public double getDefense(){
		return _defense;
	}
	
	public int getHealth(){
		return _health;
	}
	
	public int getGold(){
		return _gold;
	}
	
	public int getExperience(){
		return _exp;
	}
	
	public String getName(){
		return _name;
	}
	
	private String getNameModifier(int difficulty){
		String nameModifier = "??? ";
		if(difficulty >5)
			difficulty = 5;
		if(difficulty <1)
			difficulty = 1;
		
		switch(difficulty){
			case 1: 
				nameModifier = "Wimpy ";
				break;
			case 2:
				nameModifier = "Weak ";
				break;
			case 3:
				// just normal enemy
				nameModifier = "";
				break;
			case 4:
				nameModifier = "Strong ";
				break;
			case 5:
				nameModifier = "EPIC ";
				break;
		}
		
		return nameModifier;
	}
	
	public boolean attack(Player p){
		int attackPosition;
		boolean position1Checked = false;
		boolean position2Checked = false;
		boolean position3Checked = false;
		ArrayList<Character> targets = new ArrayList<Character>();
		
		Random generator = new Random();
		int number = generator.nextInt(100);
		if(number < 75){
			attackPosition = 3;
			position3Checked = true;
		}
		else if(number >= 75 && number < 95){
			attackPosition = 2;
			position2Checked = true;
		}
		else{
			attackPosition = 1;
			position1Checked = true;
		}
		
		boolean foundTarget = false;
		while(!foundTarget){
			for(Character c: p.getParty()){
				if((c.getPositionX()==attackPosition)&&(!c.isDead())){
					targets.add(c);
					foundTarget = true;
				}
			}
			if(!foundTarget){
				if(position1Checked && position2Checked && position3Checked){
					return false;
				}
				else{
					if(!position3Checked){
						attackPosition = 3;
						position3Checked = true;
					}
					else if(!position2Checked){
						attackPosition = 2;
						position2Checked = true;
					}
					else{
						attackPosition = 1;
						position1Checked = true;
					}
						
				}
				
			}
		
		}
		
		int targetIndex = generator.nextInt(targets.size());
		targets.get(targetIndex).attackedByEnemy(this);
		return true;
	}
	
	public String loseHealth(int damage){
		if(damage > 0){
			_health = Math.max((_health - damage),0);
			if(_health==0){
				_dead = true;
			}
			return(_name + " took " + damage + " damage!");
		}
		else return (_name + " defended the attack!");
	}
	
	public String checkInfo(){
		return "\nName: " + _name + "\nHealth: " + _health + "\nAtk: " + _attack + "\nDef: " + _defense + "\nGold: " + _gold + "\nExp: " + _exp + "\n";
	}
}
