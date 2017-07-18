/**
 * @author EDanilova
 *
 */

package tehnosila.tehnosila_automation.tests.Desctop;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import tehnosila.tehnosila_automation.pages.JSON.ParserJSON_Cities;
import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Region_Change_Check;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.tests.TestBase;

public class Region_Check extends TestBase{
		
	Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
    Region_Change_Check regionchangecheck = MyPageFactory.getPage(Region_Change_Check.class);
    ParserJSON_Cities parserJSONcities= MyPageFactory.getPage(ParserJSON_Cities.class);    	
	Map<String, String> mapDomainVisibility = new HashMap<String, String>();
	Map<String, String> mapDomainName = new HashMap<String, String>();
	
	@Test 
	public void regionTest() throws Exception{			 	
		
		app.getNavigationHelper().refreshPage();
		
	    String key = "true";
	    parserJSONcities.createMap(mapDomainVisibility, mapDomainName, key);
	    parserJSONcities.recurs(mapDomainVisibility, mapDomainName, key);
	   
	    pagetehnosila.clickCityPopup();
	    //Подсчитываем количество городов в попапе и создаём массив с количеством городов по столбцам
	    regionchangecheck.cityAmount();
	    //Сравниваем количество городов в api-запросе и попапе
	    regionchangecheck.CityCountCheck(NavigationBase.acCount, NavigationBase.arrSize);
	    //Собираем в массив все города из попапа в Москве
	    regionchangecheck.getPopUpCity(); 
	    //Проверка наличия городов из api-запроса в попапе (Москва)
	    Log.info("***QA: Проверка отображения всех городов из api-запроса в попапе");
	    regionchangecheck.checkCityName(NavigationBase.cityXML, NavigationBase.citySite, 0); 
	    Log.info("***QA: Проверка наличия всех городов из попапа в api-запросе");
	    regionchangecheck.checkCityName(NavigationBase.citySite, NavigationBase.cityXML, 1); 
	    Log.info("***QA: Проверка перехода по городам из попапа со сверкой названия города и домена из поапа");
	    //Проверка перехода по городам из попапа со сверкой названия города и домена из поапа с возвращением в Мск
	    regionchangecheck.popUpCityChange();
	    Log.info("***QA: Проверка отображения городов в попапах по городам");
	    //Проверка отображения городов в попапах по городам с возвращением в Мск
	    regionchangecheck.popUpCityCheck();
	}
}