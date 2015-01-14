package main;

import buildings.*;

public class HomeBase {
	public static int MAX_UPGRADE_LEVEL = 9;
	private Armory _armory;
	private Merchent _merchent;
	private Mystic _mystic;
	private BlackMarket _blackMarket;
	public HomeBase(){
		_armory = new Armory();
		_merchent = new Merchent();
		_mystic = new Mystic();
		_blackMarket = new BlackMarket();
	}
	
	public Armory getArmory(){
		return _armory;
	}
	
	public Merchent getMerchent(){
		return _merchent;
	}
	
	public Mystic getMystic(){
		return _mystic;
	}
	
	public BlackMarket getBlackMarket(){
		return _blackMarket;
	}
	
	public void levelUpArmory(){
		_armory.levelUp();
	}
	
	public void levelUpMystic(){
		_mystic.levelUp();
	}
	
	public void levelUpMerchent(){
		_merchent.levelUp();
	}
	
	public void generateDailyGoods(){
		_merchent.generateGoods();
		_armory.generateGoods();
	}
}
