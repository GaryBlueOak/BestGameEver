package main;


import java.awt.image.BufferedImage;
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
	private int _yCrd;
	private int _xCrd;
	private boolean _dead;
	private laser _laser;
	private int _deathDuration;
	private BufferedImage _image;
	private BufferedImage _digit1;
	private BufferedImage _digit2;
	private int _difficulty;
	
	public Enemy(int difficulty){
		makeEnemy(difficulty);
		_dead = false;
		_laser = new laser();
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
	
	private void makeEnemy(int difficulty){
		System.out.println(difficulty);
		_name = "Enemy fighter";
		Random generator = new Random();
		int rank = difficulty + generator.nextInt(10);
		if(rank <= 15){
			_attack = 10;
			_defense = 7;
			_health = 15;
			_image = Resources.enemy1;
		}
		else if(rank > 15 && rank <= 35){
			_attack = 11;
			_defense = 7;
			_health = 20;
			_image = Resources.enemy2;
		}
		else if(rank > 35 && rank <= 55){
			_attack = 12;
			_defense = 7;
			_health = 25;
			_image = Resources.enemy3;
		}
		else{
			_attack = 13;
			_defense = 7;
			_health = 30;
			_image = Resources.enemy4;
		}
	}
	
	public BufferedImage getFirstDigit(){
		return _digit1;
	}
	
	public BufferedImage getSecondDigit(){
		return _digit2;
	}
	
	public void healthDigits(){
		int firstDigit = _health / 10;
		int secondDigit = _health % 10;
		
		if(firstDigit==0) _digit1 = Resources.zero;
		if(firstDigit==1) _digit1 = Resources.one;
		if(firstDigit==2) _digit1 = Resources.two;
		if(firstDigit==3) _digit1 = Resources.three;
		
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
	
	public BufferedImage getImage(){
		return _image;
	}
	
	public int getXcrd(){
		return _xCrd;
	}
	
	public int getYcrd(){
		return _yCrd;
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
			_xCrd = 800;
			_yCrd = _position * 150 + 100;
		}
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
			attackPosition = 2;
			position3Checked = true;
		}
		else if(number >= 75 && number < 95){
			attackPosition = 1;
			position2Checked = true;
		}
		else{
			attackPosition = 0;
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
						attackPosition = 2;
						position3Checked = true;
					}
					else if(!position2Checked){
						attackPosition = 1;
						position2Checked = true;
					}
					else{
						attackPosition = 0;
						position1Checked = true;
					}
						
				}
				
			}
		
		}
		
		int targetIndex = generator.nextInt(targets.size());
		targets.get(targetIndex).attackedByEnemy(this);
		_laser.fire(targets.get(targetIndex));
		return true;
	}
	
	public String loseHealth(int damage){
		if(damage > 0){
			_health = Math.max((_health - damage),0);
			if(_health==0){
				_dead = true;
				_deathDuration = 45;
			}
			return(_name + " took " + damage + " damage!");
		}
		else return (_name + " defended the attack!");
	}
	
	public laser getLaser(){
		return _laser;
	}
	
	class laser{
		private Character _target;
		public int x;
		public int y;
		public int velocity;
		public laser(){
			x = 1100;
			y = 700;
			velocity = 0;
			_target = null;
		}
		public void fire(Character target){
			Resources.laser2Sound.play();
			_target = target;
			x = 800;
			y = (_position*150) + 120;
			velocity = 40;
		}
		public void update(){
			if(_target!=null){
				x = x - velocity;
				y += (((_target.getPositionY()*150)+100)-y)/8;
				if(x < (_target.getPositionX()*100)+100){
					velocity = 0;
					x = 1100;
					y = 700;
				}
			}
		}
	}
}
