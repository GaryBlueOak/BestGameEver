package specialattacks;
import main.Character;
import main.Enemies;

public class Splash extends SpecialAttack {
	
	public Splash(){
		super();
	}

	@Override
	public String getName() {
		return "Splash";
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(Character c, Enemies e) {
		System.out.println(c.getName() + " used Splash... but nothing happened.");
		
	}

}
