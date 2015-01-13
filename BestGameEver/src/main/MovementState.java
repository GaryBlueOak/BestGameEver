package main;


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
		showBattleField();
	}

	@Override
	public void onKeyPress(KeyEvent E) {
		if(E.getKeyCode()==KeyEvent.VK_UP){
			if(_c.getPositionY() < 3){
				_c.setPosition(_c.getPositionX(), _c.getPositionY()+1);
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
		}
		else if(E.getKeyCode()==KeyEvent.VK_DOWN){
			if(_c.getPositionY() > 1){
				_c.setPosition(_c.getPositionX(), _c.getPositionY()-1);
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");	
		}
		else if(E.getKeyCode()==KeyEvent.VK_RIGHT){
			if(_c.getPositionX() < 3){
				_c.setPosition(_c.getPositionX()+1, _c.getPositionY());
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");	
		}
		else if(E.getKeyCode()==KeyEvent.VK_LEFT){
			if(_c.getPositionX() > 1){
				_c.setPosition(_c.getPositionX()-1, _c.getPositionY());
			}
			System.out.println(_c.getName() + " has moved to " + _c.getLocation() + "!");
			
		}
		else if(E.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println(_c.getName() + " held position!");
		}
		
		if(_itr.hasNext()){
			_c = (Character)_itr.next();
			showBattleField();
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
			showBattleField();
			setCurrentState(new AttackState(_player,_enemies));
		}
		
		
	}
	
	public void showBattleField(){
		String[][] map = new String[4][3];
		System.out.println("/////// BattleField ////////\n");
		for(Character character: _player.getParty()){
			map[character.getPositionX()-1][character.getPositionY()-1] = character.getName().substring(0,1);
		}
		for(Enemy enemy: _enemies.getEnemies()){
			map[3][enemy.getPosition()-1] = enemy.getName().substring(0,1);
		}
		for(int i = 2; i >= 0; i --){
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

}
