package Runnable;

public class Character {
	public static int MAX_BASE_HEALTH = 100;
	public static double BASE_DEFENSE; // the lowest defense possible
	public static double BASE_ATTACK; // the lowest attack possible
	private String _name;
	private double _attack; 
	private double _defense;
	
	public Character(String name){
		_name = name;
	}
	
	public String getName(){
		return _name;
	}
	
	public double getAttackDamage(){
		return _attack;
	}
	
	public double getDefense(){
		return _defense;
	}
	
	public int takeDamage(Enemy enem){
		return 0;
	}
	
	
}
