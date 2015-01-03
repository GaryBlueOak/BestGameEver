package Runnable;

public class test {
	public static void main(String[] args){
		for(int i = 1; i <7; i ++){
			for(int k = 1; k <6; k++){
				System.out.print(new Enemy(i,k).checkInfo());
			}
		}
	}
}
