package items;

import java.util.Random;

import main.Character;
import main.Enemies;

public class Potion extends Item {
	
	
	public Potion(){
		super();
	}

	@Override
	public String getName() {
		return "Potion";
	}

	@Override
	public void use(Character c, Enemies e) {
		System.out.print(c.getName() + " drank a potion...");
		Random generator = new Random();
		int result = generator.nextInt(100);
		if(result < 50){
			System.out.println("it was highly rejuvenating!");
			c.gainHealth(20);
		}
		else{
			System.out.println("it was just flavored water!");
		}
		
	}

	@Override
	public int getCost() {
		return 50;
	}

	@Override
	public String getInfo() {
		return null;
	}

}
