package tehnosila.tehnosila_automation.tests.Desctop;

/**
 * @author MRasstrigina
 *
 */

import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.JSON.ParserJSON_CatalogOnline;
/**
 * @author DZhukov
 *
 */
import tehnosila.tehnosila_automation.tests.TestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.testng.annotations.Test;



// Тест изменения количества товаров в корзине, удаление товара из корзины
public class Check_Hamburger extends TestBase{
	Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
	ParserJSON_CatalogOnline parserJSONcatalogonline = MyPageFactory.getPage(ParserJSON_CatalogOnline.class);
	public List<String> numbersSubcategories = new ArrayList<>(); // массив кол-ва товаров по категориям
	public List<String> numbersSubSubcategories = new ArrayList<>(); // массив кол-ва товаров по подкатегориям
	Map<String, String> mapOuterIdParentOuterId = new HashMap<String, String>();
	Map<String, String> mapOuterIdName = new HashMap<String, String>();
	Map<String, String> mapValueNull = new TreeMap<String, String>();
	Map<String, String> mapValueNullName = new TreeMap<String, String>();
 
	@Test
	public void loginTest() throws Exception{
		
		app.getNavigationHelper().refreshPage();
		
	/*	Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>)gson.fromJson(json, map.getClass());*/
		
		parserJSONcatalogonline.createMap(mapOuterIdParentOuterId, mapOuterIdName);
		parserJSONcatalogonline.delNull(mapOuterIdParentOuterId, mapOuterIdName);
		parserJSONcatalogonline.createMapValueNull(mapOuterIdParentOuterId, mapValueNull);
		parserJSONcatalogonline.createMapValueNullName(mapOuterIdName, mapValueNull, mapValueNullName);
   		pagetehnosila.getPageCatalog();
	    
	    	 
   		for (Entry<String, String> entry : mapValueNullName.entrySet()) {
	    		String value = entry.getValue();
	    		String key = entry.getKey();
	 	  //  	pagetehnosila.clickHamburger();
	    		Log.info("value^ " + value);
	 	    	pagetehnosila.clickHamburgers(value);
 	    			parserJSONcatalogonline.recurs(mapOuterIdParentOuterId, mapOuterIdName, key);
	 	    			 if (key!=null) {
	 						Thread.sleep(4000);
	 						pagetehnosila.getPageCatalog();
	 					}
	 	    		}
	}
	
	
	
}
