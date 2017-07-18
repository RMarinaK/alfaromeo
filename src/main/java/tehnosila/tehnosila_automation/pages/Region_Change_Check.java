package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.CommonMetods;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author EDanilova
 *
 */

public class Region_Change_Check extends Page_AreaMenu {
	
	private static Logger Log = LoggerFactory.getLogger(CommonMetods.class);
	
	@Override
	public boolean isOnThisPage(){
		return true;
	}
	
	@FindBy(id="current-region")
	public WebElement currentRegion; // Текущий город   @author EDanilova	
		
	//подсчитываем кол-во больших городов в попапе
	public int bigCityCount() {
		int bcCount = driver.findElement(By.xpath("//td[@class='big-cities']/ul")).findElements(By.tagName("li")).size(); 
			return (bcCount);
	}
		
	//подсчитываем кол-во прочих городов в попапе
	public int otherCityCount(int i) {
		int ocCount = driver.findElement(By.xpath("//div[@class='region-lists']/table/tbody/tr[2]/td[2]/ul[" + i + "]")).findElements(By.tagName("li")).size();
		return (ocCount);
	}
	
    //Подсчитываем количество городов в попапе и создаём массив с количеством городов по столбцам
	public void cityAmount() throws IOException{
		NavigationBase.count = new int[4];
	    NavigationBase.acCount = 0;
	    for(int j = 0; j < NavigationBase.count.length; j++){
	    	if (j == 0) {
	    		NavigationBase.count[j] = bigCityCount();
	    	} else {
	    		NavigationBase.count[j] = otherCityCount(j);
	        }
	    	NavigationBase.acCount = NavigationBase.acCount + NavigationBase.count[j];
	    }	
	}
	
	//Сравнение кол-ва городов на сайте и в xml
	public void CityCountCheck (int x, int y){
		if(x == y){
			Log.info("***QA: Количество городов в api-запросе и попапе совпадает = " + x);
			} else {
				Log.info("***QA: Количество городов НЕ совпадает! В api-запросе: " + y + ", в попапе:  " + x);
			}
	}
	
	//перебор городов в попапе БЕЗ перехода по ним
	public void getNewCity(int i, int j, int k) throws Exception {
		try {
			WebElement findCity = driver.findElement(By.xpath("//div[@class='region-lists']/table/tbody/tr[2]/td[" + i + "]/ul[" + j + "]/li[" + k + "]/a"));
			
			//выдёргиваем из кода название города и его домен
			String city = findCity.getText();
								
			//передаём полученные значения в глобальные переменные
			NavigationBase.currCity = city;
					
			} catch (TimeoutException ignore) {
				Log.info("Element Not Found");
				ScreenShot.takeScreenShot();
			}
	}
	
	//Собираем в массив все города из попапа в Москве
	public void getPopUpCity() throws Exception{ 	
	    int m = 0;
	    int a = 1;
	    int b = 1;
	    cityAmount();
		NavigationBase.citySite = new String[NavigationBase.acCount];
	    for(int j = 0; j < NavigationBase.count.length; j++){
	    	if (j != 0) a = 2;
	        if (j > 1) b++;
		    for (int c = 1; c <= NavigationBase.count[j]; c++){ 
		    	getNewCity(a,b,c);
		    	NavigationBase.citySite[m] = NavigationBase.currCity;
			    m++;
			}
	    }
	    Log.info(" NavigationBase.currCity" +  NavigationBase.currCity);
	}
	
	// вытягивание url страницы  
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	//обрезание полученного url 
	public String cutingUrl(String resivedUrl){
			String[] cutURL = resivedUrl.split ("\\.");
			cutURL = cutURL[0].split("//");
			String resultCutStr = cutURL[cutURL.length-1];
			return resultCutStr;
	}
	
	// проверка url выбранного города  
	public void assertURL(String cityURL) throws Exception {
		try {
			String getURL = getCurrentUrl();
			StringBuffer pageURL = new StringBuffer(cutingUrl(getURL));
			boolean result = cityURL.contentEquals(pageURL);
			if (result == true){
				Log.info("***QA: Проверка перехода на URL " + cityURL + " OK");
			} else {
				Log.info("***QA: url из попапа " + cityURL + " и адресной строки НЕ совпадают");  
			}
		}
	    catch(Exception e) {      
	    	Log.info("***QA: url из попапа и адресной строки НЕ совпадают");     
	        ScreenShot.takeScreenShot();       
	     }    
	}
	
	// проверка выбранного города 
	public void assertCity(String cityName) throws Exception {
		try {
			StringBuffer getCity = new StringBuffer(currentRegion.getText());
			boolean result = cityName.toUpperCase().contentEquals(getCity);
			if (result == true){
				Log.info("***QA: Проверка смены города " + cityName + " OK");
			} else {
				Log.info("***QA: название города " + cityName + " из попапа и из шапки сайта НЕ совпадают");  
			}
		}
		catch(Exception e) {      
	    	Log.info("***QA: название города из попапа и из шапки сайта НЕ совпадают");     
	        ScreenShot.takeScreenShot();       
	     }    
	}
	
	//выбор нового города в попапе и клик по нему + запись названия города и домена в глобальные переменные
	public void clickNewCity(int i, int j, int k) throws Exception {
		try {
			//if (k > 1) getPopUpCity();
			WebElement findCity = driver.findElement(By.xpath("//div[@class='region-lists']/table/tbody/tr[2]/td[" + i + "]/ul[" + j + "]/li[" + k + "]/a"));
			
			//выдёргиваем из кода название города и его домен
			String city = findCity.getText();
			String cityURL = findCity.getAttribute("href");
			
			//передаём полученные значения в глобальные переменные
			NavigationBase.currCity = city;
			NavigationBase.currDomain = cutingUrl(cityURL);

			findCity.click();
				
			} catch (TimeoutException ignore) {
				Log.info("Element Not Found");
				ScreenShot.takeScreenShot();
			}
	}
	
	//Проход по таблице с городами в попапе и запись данных (название, домен) в массивы + валидация с url и названием города с сайта
	public void popUpCityChange() throws Exception{
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);       
	    int a = 1;
	    int b = 1;
	    for(int j = 0; j < NavigationBase.count.length; j++){
	    	if (j != 0) a = 2;
	        if (j > 1) b++;
		    for (int c = 1; c <= NavigationBase.count[j]; c++){ 
		    	clickNewCity(a,b,c);
		    	commonmetods.getHTTPResponseCode();
			    assertURL(NavigationBase.currDomain);
			    assertCity(NavigationBase.currCity);
			    returnStart();
		    	pagetehnosila.clickCityPopup();
			}
	    }
	}
	
	//Проход по таблице с городами в попапе и сравнение городов из попапа с xml-массивом
	public void popUpCityCheck() throws Exception{
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);      
	    int a = 1;
	    int b = 1;
	    for(int j = 0; j < NavigationBase.count.length; j++){
	    	if (j != 0) a = 2;
	        if (j > 1) b++;
		    for (int c = 1; c <= NavigationBase.count[j]; c++){
		    	clickNewCity(a,b,c);
		    	pagetehnosila.clickCityPopup();
		    	Log.info("Города, доступные из региона " + NavigationBase.currCity);
		    	getPopUpCity();
		    	checkCityName(NavigationBase.cityXML, NavigationBase.citySite, 0);
			    returnStart();
		    	pagetehnosila.clickCityPopup();
			}
	    }
	}
	
	//Возвращение в МСК
	public void returnStart() throws Exception{
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		
		pagetehnosila.clickCityPopup();
		clickNewCity(1,1,1);
		commonmetods.getHTTPResponseCode();
	}

	//Проверка наличия городов из xml в попапе (Москва)
	public void checkCityName(String[] args, String[] args1, int f) throws Exception{
		int l;
		int counter = 0;
		int limA;
		int limB;
		if(f==0){
			limA = NavigationBase.arrSize;
			limB = NavigationBase.acCount;
		}else{
			limA = NavigationBase.acCount;
			limB = NavigationBase.arrSize;
		}
	    for (int y = 0; y < limA; y++){
	    	for (l = 0; l < limB; l++){
	    		if(args[y].trim().equals(args1[l].trim())){
	    		    //Log.info("***QA: " + args[y] + " - есть");
	    			counter++;
	    			break;
	    		}
	        } 
	    	if (l == limB) {
	    			Log.info("***QA: ОШИБКА! " + args[y] + " - нет!");
	    	}
	    }
	    if(counter == limA){
	    	Log.info("***QA: города в api-запросе и попапе совпадают");
	    }
	}
}

