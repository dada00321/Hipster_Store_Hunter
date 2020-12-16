/* 測試用程式: 測試從某 URL 讀取 JSON 格式資料 */
import java.io.IOException;
import java.net.URISyntaxException;

public class ReadJsonFromUrl {
	public static void main(String a[]) throws IOException, URISyntaxException {
    	//String json_path = "hipsterShops.json";
    	//write_JSON(json_path);
		String url = "https://cloud.culture.tw/frontsite/trans/emapOpenDataAction.do?method=exportEmapJson&typeId=L";
		read_JSON(url);
    }
	static void read_JSON(String url) throws IOException, URISyntaxException {
		//HipsterShopList hipsterShopList = new DataManagement().readAllList(url); // original (without Thread)
		DataManagement dm = new DataManagement(url);
		dm.run();
		HipsterShopList hipsterShopList = dm.getHipsterShopList();
		//System.out.print("Hipster Shops: ");
		//System.out.println(hipsterShopList.getHipsterShopList());
		
		//System.out.printf("\nThe total amount of Hipster Shops is: %d\n", hipsterShopList.getListCounter());
		
		System.out.println("\nThe 3rd record: ");
		System.out.println(hipsterShopList.getHipsterShopList().get(2));
	}
}