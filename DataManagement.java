import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataManagement extends Thread {
	private String URL;
	private HipsterShopList hipsterShopList;
	
	public DataManagement(String url) {
		this.URL = url;
	}
	
	public void run() {
		try {
			this.readAllList(this.URL);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public HipsterShopList getHipsterShopList() {
		return this.hipsterShopList;
	}
	
	public void readAllList(String url) throws IOException, URISyntaxException {
		String response = getJsonStringByRestTemplate(url);
    	
    	String jString = String.format("{\"hipsterShopList\":%s}", response);
    	//System.out.println(jString);
    	//String jsonList = mapper.readTree(jString).get("hipsterShopList").toString();
        //System.out.println(jsonList);
        HipsterShopList newShopList = new HipsterShopList();
        newShopList.setHipsterShopList(getListFromJsonString(jString));
    	//return hipsterShopList; //original(without thread)
        this.hipsterShopList = newShopList;
	}
	/* Way-1 to get JSON from URI */
	/*
	static String getJsonStringByHttpGet(String url) {
		HttpGet request = new HttpGet(new URI(url));
    	CloseableHttpClient client = HttpClients.createDefault();
    	CloseableHttpResponse response = client.execute(request);
    	HttpEntity entity = response.getEntity();
    	String response = EntityUtils.toString(entity);
    	//System.out.println(response);
    	return response;
	}*/
	
	/* Way-2 to get JSON from URI */
	static String getJsonStringByRestTemplate(String url) throws RestClientException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
    	String response = restTemplate.getForObject(new URI(url), String.class);
    	//System.out.println(response);
    	return response;
	}
	
	static ArrayList<HipsterShop> getListFromJsonString(String jString) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonList = mapper.readTree(jString).get("hipsterShopList").toString();
		return mapper.readValue(jsonList, new TypeReference<ArrayList<HipsterShop>>(){});
	}
	
	/*
	public void writeAllList(String json_path, HipsterShopList shopList) {
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		try {
			om.writerWithDefaultPrettyPrinter().writeValue(new File(json_path), shopList);
			//om.writeValue(new File("hipsterShops.json"), shopList);
			System.out.printf("json file: \"%s\" has saved successfully!\n", json_path);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}