package items;

import java.util.Random;

import main.Character;
import main.Enemies;

public class Coin extends Item {
	
	public Coin(){
		super();
	}

	@Override
	public String getName() {
		return "Coin";
	}

	@Override
	public void use(Character c, Enemies e) {
		System.out.print(c.getName() + " flipped a coin... ");
		Random generator =  new Random();
		int result = generator.nextInt(2);
		if(result==1) System.out.println("heads" );
		else System.out.println("tails");
		
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
	public boolean isEquipable() {
		// TODO Auto-generated method stub
		return false;
	}

}
