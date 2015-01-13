package main;
import items.Coin;
import items.Item;
import specialattacks.*;

public class Character {
	public static int MAX_BASE_HEALTH = 20;
	public static double BASE_DEFENSE = 5.0; // the lowest defense possible
	public static double BASE_ATTACK = 15.0; // the lowest attack possible
	private String _name;
	private double _attack; 
	private double _defense;
	private int _health;
	private int _maxHealth;
	private boolean _dead;
	private int _positionX;
	private int _positionY;
	private SpecialAttack _special;
	private Item _item;
	private Item _equipped;
	
	public Character(String name){
		_name = name;
		_dead = false;
		_maxHealth = MAX_BASE_HEALTH;
		_health = _maxHealth;
		_attack = BASE_ATTACK;
		_defense = BASE_DEFENSE;
		_positionX = 2;
		_positionY = 2;
	}
	
	public void setPosition(int x,int y){
		_positionX = x;
		_positionY = y;
	}
	
	public void setSpecial(SpecialAttack s){
		_special = s;
		if(s.getOwner()!=null){
			s.getOwner().setSpecial(new Splash());
		}
		s.setOwner(this);
	}
	
	public boolean equipItem(Item item){
		if(item.isEquipable()){
			if(_equipped == null){
				_equipped = item;
			}else{
				System.out.println(_name + " already has a " + item.getName() + " equipped!");
				return false;
			}
			return true;
		}
		System.out.println(item.getName() + " is not an equippable item.");
		return false;
	}
	
	public Item unequipItem(){
		Item toReturn = _equipped;
		_equipped = null;
		System.out.println(_name + " has unequipped a " + toReturn.getName());
		return toReturn;
	}
	
	public String getLocation(){
		String position = "";
		switch(_positionX){
		case 1:
			position += "rear ";
			break;
		case 2:
			position += "center ";
			break;
		case 3:
			position += "front ";
			break;
		}
		switch(_positionY){
		case 1:
			position += "bottom";
			break;
		case 2:
			position += "middle";
			break;
		case 3:
			position += "top";
			break;
		}
		return position;
	}
	
	public int getPositionX(){
		return _positionX;
	}
	
	public int getPositionY(){
		return _positionY;
	}
	
	public String getName(){
		return _name;
	}
	
	public void normalAttack(Enemies e){
		boolean succesful = false;
		System.out.print(_name + " charges into battle... ");
		for(Enemy enemy: e.getEnemies()){
			if((enemy.getPosition()==_positionY)&&(!enemy.isDead())){
				succesful = true;
				int damage = (int)(_attack - enemy.getDefense());
				System.out.println(enemy.loseHealth(damage));
				if(enemy.isDead()){
					System.out.println(enemy.getName() + " was killed!");
				}
			}
		}
		if(!succesful) System.out.println("attack missed!");
	}
	
	public void specialAttack(Enemies e){
		_special.use(this, e);
	}
	
	public void setItem(Item i){
		_item = i;
		if(i.getOwner()!=null){
			i.getOwner().setItem(new Coin());
		}	
		i.setOwner(this);
	}
	
	public void useItem(Enemies e){
		_item.use(this, e);
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
			System.out.print(_name + " was attacked by " + enem.getName() + "... ");
			System.out.println(loseHealth(enem));
			if(_health <= 0){
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
		return _name + "   Health: " + _health + "   Position: " + this.getLocation();
		//return "\nName: " + _name + "\nHealth: " + _health + "\nAtk: " + _attack + "\nDef: " + _defense;
	}
	
	public String checkStatus(){
		return _name + "   HP: " + _health + "   Item: " + _item.getName() + "   Special: " + _special.getName();
	}

	
	
	
	
	
}
