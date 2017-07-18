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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.PagesBase;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;

/**
 * @author EDanilova
 *
 */
@SuppressWarnings("deprecation")
public class ParserJSON_Cities extends PagesBase {
	private static Logger Log = LoggerFactory.getLogger(ParserJSON_Cities.class);

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
	public void createMap(Map<String, String> mapDomainVisibility, Map<String, String> mapDomainName, String getKey) throws Exception {
		String json = readUrl(NavigationBase.papicities);
		ObjectMapper mapper = new ObjectMapper();
		Head head = mapper.readValue(json, Head.class);
		for (Cities cities : head.getCities()) {
			if(getKey.equals(cities.getIsActive())){
				mapDomainVisibility.put(cities.getDomain(), cities.getIsActive());
				mapDomainName.put(cities.getDomain(), cities.getName());
				NavigationBase.arrSize++;
			}
		}
	}
		
	Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
	int count=0;
	public void recurs(Map<String, String> mapDomainVisibility, Map<String, String> mapDomainName, String entrygetKey) throws InterruptedException {
		
		NavigationBase.cityXML = new String[NavigationBase.arrSize];
		NavigationBase.domainXML = new String[NavigationBase.arrSize];
		for (Entry<String, String> entry : mapDomainVisibility.entrySet()) {
			if (entrygetKey.equals(entry.getValue())) {
				String entrygetKey1 = entry.getKey();	
				for (Entry<String, String> entry1 : mapDomainName.entrySet()) {
					if (entrygetKey1.equals(entry1.getKey())) {
						NavigationBase.cityXML[count] = entry1.getValue();
						NavigationBase.domainXML[count] = entry1.getKey();
						count++;
					}
				}
			}
		}
	}
}
