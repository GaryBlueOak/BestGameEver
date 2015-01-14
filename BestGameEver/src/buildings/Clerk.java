package buildings;

public class Clerk {
	private String _name;
	private int _level;
	private String _mood;
	
	
	public Clerk(String name, int level){
		_name = name;
		_level = level;
		_mood = "Happy";
	}
	
	public String getMood(){
		return _mood;
	}
	
	public String getName(){
		return _name;
	}
	
	public int getLevel(){
		return _level;
	}
	
	public void moodToHappy(){
		_mood = "Happy";
	}
	
	public void moodToTimid(){
		_mood = "Timid";
	}
	
	public void moodToAngry(){
		_mood = "Angry";
	}
	
	public void moodToStern(){
		_mood = "Pleasent"; //neutral
	}
}
