package main;



import java.util.ArrayList;
import java.util.Random;

public class Enemies {

	private ArrayList<Enemy> _enemies;
	
	public Enemies(int difficulty){
		Random randomGenerator = new Random();
		int numEnemies = 3;
		_enemies = new ArrayList<Enemy>();
		Enemy enemy; 
		for(int i=1;i<=numEnemies;i++){
			enemy = new Enemy(difficulty);
			enemy.setPosition(i-1);
			_enemies.add(enemy);
		}
		
	}
	//possible 3x2 enemy box (up to 6 enemies)
	public boolean attack(Player p){
		System.out.println();
		for(Enemy enemy: _enemies){
			if(!enemy.isDead()){
				if(!enemy.attack(p)) return false;
			}
		}
		return true;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return _enemies;
		
	}
	
	

}
