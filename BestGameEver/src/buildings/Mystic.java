package buildings;

import specialattacks.*;
import items.Item;
import items.Sword;

public class Mystic extends Building{
		private int _storeLevel; // store level determines selection of goods
		private Clerk _clerk; // clerk level determines quality of goods
		private SpecialAttack[] _goods;
		
		public Mystic(){
			_storeLevel = 1;
			_clerk = new Clerk("Scrappy Doo", 1);
			_goods = new SpecialAttack[10];
		}
		@Override
		public int getLevel() {
			
			return _storeLevel;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "Mystic";
		}
		
		public boolean levelUp(){
			if(_storeLevel < 9){
				_storeLevel ++;
				System.out.println("The " + getName() + " has grown to level " + _storeLevel + "!");
				return true;
			}else{
				System.out.println("This building is already at max level!");
				return false;
			}
		}
		
		public Clerk getClerk(){
			return _clerk;
		}
		
		public void changeClerk(Clerk clerk){
			_clerk = clerk;
		}
		
		public SpecialAttack[] getGoods(){
			return _goods;
		}
		
		public void removeGood(int index){
			if(_goods[index] == null){
				System.out.println("Index was null.");
			}else{
				_goods[index] = null;
				System.out.println("Removed item.");
			}
		}
		
		public void generateGoods(){
			switch(_storeLevel){
				case 1:
					for(int i = 0; i <10; i ++){
						_goods[i] = new MagicArrow();
					}
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 9:
					break;
			}
		}
}
