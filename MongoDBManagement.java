import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBManagement {
	
	public void insertRecords(String dbName, String tableName, HipsterShopList shopList) {
		/* Connect to MongoDB */
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB(dbName); // Note: If database is not present, MongoDB will create it.
		DBCollection collection = db.getCollection(tableName);
		BasicDBObject document;
		
		//System.out.println("\nrecord count: " + shopList.getListCounter());
		for(int i=0; i<shopList.getListCounter(); i++) {
			document = new BasicDBObject();
			HipsterShop currentShop = shopList.getHipsterShopList().get(i);
			
			String name = currentShop.getName();
			String representImage = currentShop.getRepresentImage();
			String intro = currentShop.getIntro();
			String address = currentShop.getAddress();
			String longitude = currentShop.getLongitude();
			String latitude = currentShop.getLatitude();
			String openTime = currentShop.getOpenTime();
			String phone = currentShop.getPhone();
			String fax = currentShop.getFax();
			String email = currentShop.getEmail();
			String facebook = currentShop.getFacebook();
			String website = currentShop.getWebsite();
			String headCityName = currentShop.getHeadCityName();
			String arriveWay = currentShop.getArriveWay();
			String name_eng = currentShop.getName_eng();
			String intro_eng = currentShop.getIntro_eng();
			String openTime_eng = currentShop.getOpenTime_eng();
			String arriveWay_eng = currentShop.getArriveWay_eng();
			String mainTypeName = currentShop.getGroupTypeName();
			String cityName = currentShop.getCityName();
			String groupTypeName = currentShop.getGroupTypeName();
			String mainTypePk = currentShop.getMainTypeName();
			String version = currentShop.getVersion();
			int hitRate = currentShop.getHitRate();
			
			document.put("name", name);
			document.put("representImage", representImage);
			document.put("intro", intro);
			document.put("address", address);
			document.put("longitude", longitude);
			document.put("latitude", latitude);
			document.put("openTime", openTime);
			document.put("phone", phone);
			document.put("fax", fax);
			document.put("email", email);
			document.put("facebook", facebook);
			document.put("website", website);
			document.put("headCityName", headCityName);
			document.put("arriveWay", arriveWay);
			document.put("name_eng", name_eng);
			document.put("intro_eng", intro_eng);
			document.put("openTime_eng", openTime_eng);
			document.put("arriveWay_eng", arriveWay_eng);
			document.put("mainTypeName", mainTypeName);
			document.put("cityName", cityName);
			document.put("groupTypeName", groupTypeName);
			document.put("mainTypePk", mainTypePk);
			document.put("version", version);
			document.put("hitRate", hitRate);
			
			collection.insert(document);
		}
	}
}
