package Runnable;

public class Character {
	public static int MAX_BASE_HEALTH = 100;
	private String _name;
	private double _attack;
	
	public Character(String name){
		_name = name;
	}
	
	public String getName(){
		return _name;
	}
	
	public double getAttackDamage(){
		return _attack;
	}
	
	
}
