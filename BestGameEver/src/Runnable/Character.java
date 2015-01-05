package Runnable;

public class Character {
	public static int MAX_BASE_HEALTH = 100;
	public static double BASE_DEFENSE = 1.0; // the lowest defense possible
	public static double BASE_ATTACK = 1.0; // the lowest attack possible
	private String _name;
	private double _attack; 
	private double _defense;
	private int _health;
	private int _maxHealth;
	private boolean _dead;
	private int _positionX;
	private int _positionY;
	
	public Character(String name){
		_name = name;
		_dead = false;
		_maxHealth = MAX_BASE_HEALTH;
		_health = _maxHealth;
		_attack = BASE_ATTACK;
		_defense = BASE_DEFENSE;
	}
	
	public String getName(){
		return _name;
	}
	
	public void trainAttack(double raised){
		_attack = _attack + raised;
		System.out.println(_name + "'s attack as risen by " + raised + "!");
	}
	
	public void trainDefense(double raised){
		_defense = _defense + raised;
		System.out.println(_name + "'s defense as risen by " + raised + "!");
	}
	
	public int getHealth(){
		return _health;
	}
	
	public int getMaxHealth(){
		return _maxHealth;
	}
	
	public double getAttackDamage(){
		return _attack;
	}
	
	public double getDefense(){
		return _defense;
	}
	
	public boolean isDead(){
		return _dead;
	}
	
	public void raiseFromDead(){
		_health = _maxHealth;
		_dead = false;
		System.out.println(_name + " has risen from the dead!");
	}
	
	private int damageTaken(Enemy enem){
		int damage = (int)(enem.getAttack()-_defense);
		if(damage < 1)
			return 0;
		return damage;
	}
	
	public String loseHealth(Enemy enem){
		_health = _health - damageTaken(enem);
		return damageTaken(enem) + " damage taken.";
	}
	
	public boolean gainHealth(int gained){
		if(_health == _maxHealth){
			System.out.println(_name + "'s health is already at max.");
			return false;
		}
		_health = _health + gained;
		if(_health >= _maxHealth){
			_health = _maxHealth;
			System.out.println(_name + " is now at max life!");
		}
		return true;
	}
	
	public boolean attackedByEnemy(Enemy enem){
		if(!_dead){
			System.out.println(_name + " was attacked by " + enem.getName());
			System.out.println(loseHealth(enem));
			if(_health < 0){
				_health = 0;
				_dead = true;
				System.out.println(_name + " has fallen in battle");
			}
			return true;
		}
		System.out.println(_name + " is already dead!");
		return false;
	}
	
	public String checkInfo(){
		return "\nName: " + _name + "\nHealth: " + _health + "\nAtk: " + _attack + "\nDef: " + _defense;
	}
	
	
	
	
}
