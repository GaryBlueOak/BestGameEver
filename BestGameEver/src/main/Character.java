package main;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
	private int _xCrd;
	private int _yCrd;
	private SpecialAttack _special;
	private laser _laser;
	private BufferedImage _laserImage;
	private BufferedImage _image;
	private int _deathDuration;
	private BufferedImage _digit1;
	private BufferedImage _digit2;
	
	
	public Character(String name){
		_name = name;
		_dead = false;
		_maxHealth = MAX_BASE_HEALTH;
		_health = _maxHealth;
		_attack = BASE_ATTACK;
		_defense = BASE_DEFENSE;
		_laser = new laser();
		_positionX = 2;
		_positionY = 2;
		_image = Resources.fighter1;
	}
	
	public laser getLaser(){
		return _laser;
	}
	
	public BufferedImage getLaserImage(){
		return _laserImage;
	}
	
	public BufferedImage getImage(){
		return _image;
	}
	
	public void setPosition(int x,int y){
		_positionX = x;
		_positionY = y;
	}

	public int getPositionX(){
		return _positionX;
	}
	
	public int getPositionY(){
		return _positionY;
	}
	
	public int getXcrd(){
		return _xCrd;
	}
	
	public int getYcrd(){
		return _yCrd;
	}
	
	public BufferedImage getFirstDigit(){
		return _digit1;
	}
	
	public BufferedImage getSecondDigit(){
		return _digit2;
	}
	
	public String getName(){
		return _name;
	}
	
	public void normalAttack(Enemies e){
		_laser.fire();
		Resources.laserSound.play();
		boolean succesful = false;
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
	}
	
	public void specialAttack(Enemies e){
		_special.use(this, e);
	}
	
	public int getHealth(){
		return _health;
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
	
	private int damageTaken(Enemy enem){
		int damage = (int)(enem.getAttack()-_defense);
		if(damage < 1)
			return 0;
		return damage;
	}
	
	public void healthDigits(){
		int firstDigit = _health / 10;
		int secondDigit = _health % 10;
		
		if(firstDigit==0) _digit1 = Resources.zero;
		if(firstDigit==1) _digit1 = Resources.one;
		if(firstDigit==2) _digit1 = Resources.two;
		
		if(secondDigit==0) _digit2 = Resources.zero;
		if(secondDigit==1) _digit2 = Resources.one;
		if(secondDigit==2) _digit2 = Resources.two;
		if(secondDigit==3) _digit2 = Resources.three;
		if(secondDigit==4) _digit2 = Resources.four;
		if(secondDigit==5) _digit2 = Resources.five;
		if(secondDigit==6) _digit2 = Resources.six;
		if(secondDigit==7) _digit2 = Resources.seven;
		if(secondDigit==8) _digit2 = Resources.eight;
		if(secondDigit==9) _digit2 = Resources.nine;
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
		System.out.print(_name + " was attacked by " + enem.getName() + "... ");
		System.out.println(loseHealth(enem));
		if(_health <= 0){
			_health = 0;
			_dead = true;
			_deathDuration = 45;
			System.out.println(_name + " was destroyed");
		}
		return true;
	}
	
	public void update(){
		if(_dead){
			_deathDuration--;
			if(_deathDuration < 30){
				_image = Resources.death1;
				if (_deathDuration == 29){
					Resources.deathSound.play();
				}
			}	
			if(_deathDuration < 20){
				_image = Resources.death2;
			}
			if(_deathDuration < 10){
				_image = Resources.death3;
			}
			
			if(_deathDuration <= 0){
				_xCrd = 1100;
				_yCrd = 900;
			}
		}
		else{
			healthDigits();
			_xCrd = _positionX * 100 + 50;
			_yCrd = _positionY * 150 + 100;
		}
	}
	
	class laser{
		public int x;
		public int y;
		public int velocity;
		private int duration;
		public laser(){
			x = 1100;
			y = 700;
			velocity = 0;
		}
		public void fire(){
			duration = 20;
			_laserImage = Resources.laser;
			x = (_positionX*100) + 50;
			y = (_positionY*150) + 120;
			velocity = 50;
		}
		public void update(){
			_image = Resources.fighter1;
			x = x + velocity;
			duration = duration - 1;
			if(x > 780){
				velocity = 0;
				_laserImage = Resources.laserHit;
			}
			if(duration <= 0){
				x = 1100;
				y = 700;
			}
		}
		
	}
		
	
}
