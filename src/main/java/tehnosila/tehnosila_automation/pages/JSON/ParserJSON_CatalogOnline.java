package tehnosila.tehnosila_automation.pages.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.PagesBase;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;

/**
 * @author MRasstrigina
 *
 */
@SuppressWarnings("deprecation")
public class ParserJSON_CatalogOnline extends PagesBase {
	private static Logger Log = LoggerFactory.getLogger(ParserJSON_CatalogOnline.class);

	@Override
	protected
	void tryToOpen() {
	}

	// чтение JSON
	private static String readUrl(String urlString) throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
	    @SuppressWarnings({ "resource" })
		HttpClient httpClient = new DefaultHttpClient();
	    HttpGet httpGet = new HttpGet(urlString);

	    try {
	        HttpResponse response = httpClient.execute(httpGet);
	        StatusLine statusLine = response.getStatusLine();
	        int statusCode = statusLine.getStatusCode();
	        if (statusCode == 200) {
	            HttpEntity entity = response.getEntity();
	            InputStream inputStream = entity.getContent();
	            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
	            BufferedReader reader =  new BufferedReader(isr);
	            String line;
	            while ((line = reader.readLine()) != null) {
	                stringBuilder.append(line);
	            }
	            inputStream.close();
	        } else {
	            Log.info("JSON", "Failed to download file");
	        }
	    } catch (Exception e) {
	        Log.info("readJSONFeed", e.getLocalizedMessage());
	    }
	    return stringBuilder.toString();
	}
	
	// Создание массивов
	public void createMap(Map<String, String> mapOuterIdParentOuterId, Map<String, String> mapOuterIdName) throws Exception {
		String json = readUrl(NavigationBase.papicatalogonline);
		
		ObjectMapper mapper = new ObjectMapper();
		Example example = mapper.readValue(json, Example.class);
		for (Category category : example.getCategories()) {
			mapOuterIdParentOuterId.put(category.getOuterId(), category.getParentOuterId());
			mapOuterIdName.put(category.getOuterId(), category.getName());
		}
	}
	
	// удаление элементов с ключом null, т.е. первого элемента null=null
	public void delNull(Map<String, String> mapOuterIdParentOuterId, Map<String, String> mapOuterIdName) {
	    Object key = mapOuterIdParentOuterId.get(null);
	    if (key == null) {
	    	mapOuterIdParentOuterId.remove(key);
	    	mapOuterIdName.remove(key);
	    //	Log.info("mapOuterIdParentOuterId^  " + mapOuterIdParentOuterId);
	    //	Log.info("mapOuterIdName^ " + mapOuterIdName);
	    }	
	}
	
	public void createMapValueNull(Map<String, String> mapOuterIdParentOuterId, Map<String, String> mapValueNull) {
	    for (Entry<String, String> entry : mapOuterIdParentOuterId.entrySet())
	    	if (entry.getValue() == null) {
	    		mapValueNull.put(entry.getKey(), entry.getValue());
			}
	 //   Log.info("mapValueNull^ " + mapValueNull);
	}
	
	public void createMapValueNullName(Map<String, String> mapOuterIdName, Map<String, String> mapValueNull, Map<String, String> mapValueNullName) {
	    for (Entry<String, String> entry1 : mapOuterIdName.entrySet())
	    	for (Entry<String, String> entry2 : mapValueNull.entrySet())
	    		if (entry1.getKey() == entry2.getKey()) {
	    			mapValueNullName.put(entry1.getKey(), entry1.getValue());
	    		}
	  //  Log.info("mapValueNullName^ " + mapValueNullName);
	}
	
	Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);

	public void recurs(Map<String, String> mapOuterIdParentOuterId, Map<String, String> mapOuterIdName, String entrygetKey) throws Exception {		
		int y = -100;
		for (Entry<String, String> entry : mapOuterIdParentOuterId.entrySet()) {
			if (entrygetKey.equals(entry.getValue())) {
				String entry3getKey = entry.getKey();	
				for (Entry<String, String> entry1_2 : mapOuterIdName.entrySet()) {
					if (entry3getKey.equals(entry1_2.getKey())) {
						String entry1_2getValue = entry1_2.getValue();
					//	Log.info("entry1_2getValue: " + entry1_2getValue);

						WebElement title =  driver.findElement(By.xpath("//div[@id='content-wrapper']/div/div/div/h1"));
						if (app.getNavigationHelper().isElementPresent(By.xpath("//div[@class='filters children-categoryes']/div/div/div/h3/a[contains(text(),'"+entry1_2getValue+"')]")) == true) {
							WebElement category = driver.findElement(By.xpath("//div[@class='filters children-categoryes']/div/div/div/h3/a[contains(text(),'"+entry1_2getValue+"')]"));
							String code = "window.scroll(" + (category.getLocation().x) + "," + (category.getLocation().y + y) + ");";
						    ((JavascriptExecutor)driver).executeScript(code, category);
						    category.click();	
							recurs(mapOuterIdParentOuterId, mapOuterIdName, entry3getKey);
							if (entry3getKey!=null) {
								Thread.sleep(4000);
								pagetehnosila.navigateback();
			 	    		//	System.gc();
							}
						}
						else {
							Log.info("Not found '" + entry1_2getValue + "' тут: '" + title.getText() + "' " + driver.getCurrentUrl());
						}
					}
				}
			}
		}
	}
}
