import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HipsterShopList {
	ArrayList<HipsterShop> hipsterShopList;
	
	@JsonIgnore
	private int listCounter;
	
	public int getListCounter() {
		return this.listCounter;
	}

	public HipsterShopList() {
		this.hipsterShopList = new ArrayList<HipsterShop>();
		this.listCounter = 0;
	}
	
	public HipsterShopList(ArrayList<HipsterShop> shopList) {
		super();
		this.hipsterShopList = shopList;
		this.listCounter = shopList.size();
	}
	
	public String getHipsterShopInfo(int index) {
		return this.hipsterShopList.get(index).toString();
	}
	
	public ArrayList<HipsterShop> getHipsterShopList() {
		return this.hipsterShopList;
	}
	
	public void setHipsterShopList(ArrayList<HipsterShop> shopList) {
		this.hipsterShopList = shopList;
		this.listCounter = shopList.size();
	}
}
