package specialattacks;
import main.Character;
import main.Enemies;

public abstract class SpecialAttack {
	
	protected Character _owner;
	
	public SpecialAttack(){
		_owner = null;
	}
	
	public Character getOwner(){
		return _owner;
	}
	
	public void setOwner(Character c){
		_owner = c;
	}
	
	public abstract String getName();
	
	public abstract void use(Character c, Enemies e);
	
	public abstract int getCost();
	
	public abstract String getInfo();

}
