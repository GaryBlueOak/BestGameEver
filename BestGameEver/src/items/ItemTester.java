package items;

public class ItemTester {
	public static void main(String[] args){
		for(int i = 0; i < 10; i ++){
			Sword sw = new Sword();
			System.out.println("###############");
			System.out.println(sw.getName());
			System.out.println(sw.getInfo());
			System.out.println("Durability: " + sw.getDurability());
			System.out.println("Damage: " + sw.getDamage());
		}
	}
}
