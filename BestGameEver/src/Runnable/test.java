package Runnable;

public class test {
	public static void main(String[] args){
		Player test = new Player();
		for(Character chr: test.getParty()){
			System.out.println(chr.checkInfo());
		}
		for(int i = 1; i <7; i ++){
			for(int k = 1; k <6; k++){
				test.getParty().get(0).attackedByEnemy(new Enemy(i,k));
			}
		}
		
	}
}
