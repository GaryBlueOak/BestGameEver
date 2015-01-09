package specialattacks;
import main.Character;
import main.Enemies;
import main.Enemy;





public class MagicArrow extends SpecialAttack {
	
	public MagicArrow(){
		super();
	}

	@Override
	public String getName() {
		return "Magic Arrow";
	}

	@Override
	public int getCost() {
		return 50;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(Character c, Enemies e) {
		boolean succesful = false;
		System.out.print(c.getName() + " unleashes a magical arrow... ");
		for(Enemy enemy: e.getEnemies()){
			if((enemy.getPosition()==c.getPositionY())&&(!enemy.isDead())){
				succesful = true;
				int damage = (int)(c.getAttackDamage());
				System.out.println(enemy.loseHealth(damage));
				if(enemy.isDead()){
					System.out.println(enemy.getName() + " was killed!");
				}
			}
		}
		if(!succesful) System.out.println("the arrow went astray!");
	}



}
