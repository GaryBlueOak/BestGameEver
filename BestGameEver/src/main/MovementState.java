package main;


import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class MovementState extends State {
	
	private Player _player;
	private Enemies _enemies;
	private Iterator _itr;
	private Character _c;
	
	public MovementState(Player p, Enemies e){
		_player = p;
		_enemies = e;
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		boolean moveFound = false;
		Character[][] battleBoard = _player.getBattleBoard();
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_c.getPositionY() > 0 ){
				if(battleBoard[_c.getPositionX()][_c.getPositionY()-1] == null){
					_c.setPosition(_c.getPositionX(), _c.getPositionY()-1);
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}
				else{
					System.out.println(battleBoard[_c.getPositionX()][_c.getPositionY()-1].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move any higher.");
			}
			
		}
		else if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_c.getPositionY() < 2){
				if(battleBoard[_c.getPositionX()][_c.getPositionY()+1] == null){
					_c.setPosition(_c.getPositionX(), _c.getPositionY()+1);
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}else{
					System.out.println(battleBoard[_c.getPositionX()][_c.getPositionY()+1].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move any lower.");
			}
				
		}
		else if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			if(_c.getPositionX() < 2){
				if(battleBoard[_c.getPositionX()+1][_c.getPositionY()] == null){
					_c.setPosition(_c.getPositionX()+1, _c.getPositionY());
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}else{
					System.out.println(battleBoard[_c.getPositionX()+1][_c.getPositionY()].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move anymore rightwards.");
			}
				
		}
		else if(E.getKeyCode()==KeyEvent.VK_LEFT){
			if(_c.getPositionX() > 0){
				if(battleBoard[_c.getPositionX()-1][_c.getPositionY()] == null){
					_c.setPosition(_c.getPositionX()-1, _c.getPositionY());
					moveFound = true;
					System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
				}else{
					System.out.println(battleBoard[_c.getPositionX()-1][_c.getPositionY()].getName() + " is in " + _c.getName() + "'s way!");
				}
			}else{
				System.out.println(_c.getName() + " cannot move an more leftwards.");
			}
			
			
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println(_c.getName() + " held position!");
			moveFound = true;
		}
		if(moveFound){
			if(_itr.hasNext()){
				_c = (Character)_itr.next();
				if(_c.isDead() && !_itr.hasNext()){
					setCurrentState(new AttackState(_player,_enemies));
				}
				while(_c.isDead() && _itr.hasNext()){
					_c = (Character)_itr.next();
					if(_c.isDead() && !_itr.hasNext()){
						setCurrentState(new AttackState(_player,_enemies));
					}
				}
				System.out.println(_c.getName() + "'s move!");
			}
			else{
				setCurrentState(new AttackState(_player,_enemies));
			}
		}
		
		
	}
	
	public void showBattleField(){
		String[][] map = new String[4][3];
		System.out.println("/////// BattleField ////////\n");
		for(Character character: _player.getParty()){
			if(character.isDead()){
				map[character.getPositionX()][character.getPositionY()] = "X";
			}else{
				map[character.getPositionX()][character.getPositionY()] = character.getName().substring(0,1);
			}
		}
		for(Enemy enemy: _enemies.getEnemies()){
			map[3][enemy.getPosition()-1] = enemy.getName().substring(0,1);
		}
		for(int i = 0; i < 3; i ++){
			for(int k = 0; k < 4; k ++){
				if(k == 3){
					System.out.print("\t");
				}
				if(map[k][i] == null){
					System.out.print("[]");
				}else{
					System.out.print(map[k][i]);
				}
			}
			System.out.println();
		}        
	}

	@Override
	public void init() {
		System.out.println("MOVEMENT PHASE **   move: arrow keys   hold: space");
		_itr = _player.getParty().iterator();
		_c = (Character)_itr.next();
		while(_c.isDead()){
			_c = (Character)_itr.next();
		}
		System.out.println(_c.getName() + "'s move!");
		
	}

	@Override
	public void render(Graphics g) {
		renderCharacters(g);
		renderEnemies(g);
	}
	
	private void renderCharacters(Graphics g){
		for(Character c: _player.getParty()){
			if(!c.isDead()){
				g.drawImage(Resources.testSprite, (c.getPositionX()*100)+50, (c.getPositionY()*100)+100, null);
			}
		}
	}
	
	private void renderEnemies(Graphics g){
		for(Enemy e: _enemies.getEnemies()){
			if(!e.isDead()){
				g.drawImage(Resources.testEnemy, 600, e.getPosition()*100, null);
			}
		}
	}
}
