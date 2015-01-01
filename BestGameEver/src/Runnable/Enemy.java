package Runnable;

public class Enemy {
	
	private String _name;
	private double _attack;
	private double _defense;
	private double _health;
	private int _gold;
	private double _exp;
	
	public Enemy(int enemyID, int difficulty){
		makeEnemy(enemyID, difficulty);
	}
	
	private void makeEnemy(int enemyID, int difficulty){
		double statModifier = getStatModifier(difficulty);
		switch(enemyID){
			// names subject to change
			// WORK IN PROGRESS! MUCH BALANCING GOING TO BE NEEDED
			case 1: 
				_name = getNameModifier(difficulty) + "Rat";
				_attack = 2 + (int)(1*statModifier);
				_defense = 1 + (int)(1*statModifier);
				_health = 4 + (int)(2*statModifier);
				_gold = 10 + (int)(3*statModifier);
				_exp = 1;
				break;
			case 2:
				_name = getNameModifier(difficulty) + "Bat";
				_attack = 1;
				_defense = 1;
				_health = 1;
				_gold = 1;
				_exp = 1;
				break;
			case 3:
				_name = getNameModifier(difficulty) + "Goblin";
				_attack = 1;
				_defense = 1;
				_health = 1;
				_gold = 1;
				_exp = 1;
				break;
			case 4:
				_name = getNameModifier(difficulty) + "Enemy Soldier";
				_attack = 1;
				_defense = 1;
				_health = 1;
				_gold = 1;
				_exp = 1;
				break;
			case 5:
				_name = getNameModifier(difficulty) + "Ogre";
				_attack = 1;
				_defense = 1;
				_health = 1;
				_gold = 1;
				_exp = 1;
				break;
			case 6:
				_name = getNameModifier(difficulty) + "Drake";
				_attack = 1;
				_defense = 1;
				_health = 1;
				_gold = 1;
				_exp = 1;
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
}
