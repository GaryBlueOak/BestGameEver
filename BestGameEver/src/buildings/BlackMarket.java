package buildings;

import items.*;

public class BlackMarket extends Building {

	
	private Item[] _goods;
	
	public BlackMarket(){
		_goods = new Item[4];
	}
	
	@Override
	public int getLevel() {
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Black Market";
	}
	
	public Item[] getGoods(){
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

}
