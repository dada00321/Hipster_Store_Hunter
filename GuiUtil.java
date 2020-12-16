import java.io.IOException;
import java.net.URISyntaxException;

/* 和 GUI元件 無關，供 GUI類 呼叫的的程式邏輯(模組) 
 * 公開資料URL、資料庫及資料表名稱皆在此設定
 * */
public class GuiUtil {
	public String getOpenDataLink() {
		return "https://cloud.culture.tw/frontsite/trans/emapOpenDataAction.do?method=exportEmapJson&typeId=L";
	}
	
	public HipsterShopList read_JSON(String url) throws IOException, URISyntaxException {
		DataManagement dm = new DataManagement(url);
		dm.run();
		HipsterShopList hipsterShopList = dm.getHipsterShopList();
		//System.out.print("Hipster Shops: ");
		//System.out.println(hipsterShopList.getHipsterShopList());
		
		//System.out.printf("\nThe total amount of Hipster Shops is: %d\n", hipsterShopList.getListCounter());
		
		//System.out.println("\nThe 3rd record: ");
		//System.out.println(hipsterShopList.getHipsterShopList().get(2));
		return hipsterShopList;
	}
	
	public void insertToMongoDB(HipsterShopList shopList) {
		/*String dbName = "db_1215_01";
		String tableName = "table_1215_01";*/
		
		// 設定資料庫名稱為 test , collection(In RDBMS: table) 名稱為 data
		String dbName = "test";
		String tableName = "data";
		
		MongoDBManagement mangoTool = new MongoDBManagement();
		mangoTool.insertRecords(dbName, tableName, shopList);
	}
}
