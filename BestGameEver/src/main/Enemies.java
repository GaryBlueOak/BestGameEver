package main;



import java.util.ArrayList;
import java.util.Random;

public class Enemies {

	private ArrayList<Enemy> _enemies;
	
	public Enemies(){
		Random randomGenerator = new Random();
		int numEnemies = randomGenerator.nextInt(3);
		_enemies = new ArrayList<Enemy>();
		Enemy enemy; 
		for(int i=0;i<=numEnemies;i++){
			enemy = new Enemy(randomGenerator.nextInt(6)+1,randomGenerator.nextInt(5)+1);
			enemy.setPosition(i+1);
			_enemies.add(enemy);
			System.out.println(enemy.getName()+" has taken position " + enemy.location());
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
