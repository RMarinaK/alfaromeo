package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.CommonMetods;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * @author EDanilova
 *
 */

public class WorkWithArr extends Page_AreaMenu {
	
	private static Logger Log = LoggerFactory.getLogger(CommonMetods.class);
	
	@Override
	public boolean isOnThisPage(){
		return true;
	}
	
	@FindBy(id="current-region")
	public WebElement currentRegion; // Текущий город   @author EDanilova	
	
	//Извлекаем данные из txt-файла и записываем их в массив
	public void getDataFromTxt(String PathToCreateFile) throws IOException{
		FileInputStream fstream = new FileInputStream(PathToCreateFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		int i = 0;
		NavigationBase.cityXML = new String[NavigationBase.arrSize];
		NavigationBase.domainXML = new String[NavigationBase.arrSize];
		while ((strLine = br.readLine()) != null){
			if (strLine.trim().length() != 0){
				String[] cutStr = strLine.split(",");
				NavigationBase.cityXML[i] = cutStr[0];
				NavigationBase.domainXML[i] = cutStr[1];
			    i++;
			}
		}
		br.close();
	}
	
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
			Log.info("***QA: Количество городов в xml и попапе совпадает = " + x);
			} else {
				Log.info("***QA: Количество городов НЕ совпадает! В xml: " + y + ", в попапе:  " + x);
			}
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
			String cutURL = cutingUrl(getURL);
			Assert.assertEquals(cutURL, cityURL.trim()); //проверка текущего URL
			Log.info("***QA: Проверка перехода на URL " + getCurrentUrl() + " OK");
		}
	    catch(Exception e) {      
	    	Log.info("***QA: url из попапа и адресной строки НЕ совпадают");     
	        ScreenShot.takeScreenShot();       
	     }    
	}
	
	// проверка выбранного города 
	public void assertCity(String cityName) throws Exception {
		try {
			String getCity = currentRegion.getText();
			Assert.assertEquals(getCity, cityName.toUpperCase());
			Log.info("***QA: Проверка смены города "+ getCity + " OK");
		}
		catch(Exception e) {      
	    	Log.info("***QA: название города из попапа и из шапки сайта НЕ совпадают");     
	        ScreenShot.takeScreenShot();       
	     }    
	}
	
	//выбор нового города в попапе и клик по нему + запись названия города и домена в глобальные переменные
	public void clickNewCity(int i, int j, int k) throws Exception {
		try {
			WebElement findCity = driver.findElement(By.xpath("//div[@class='region-lists']/table/tbody/tr[2]/td[" + i + "]/ul[" + j + "]/li[" + k + "]/a"));
			//выдёргиваем из кода название города и его домен
			app.getNavigationHelper().waitVisible(findCity, 5);
			String city = findCity.getText();
			String cityURL = findCity.getAttribute("href");
				
			//логи
			Log.info("***QA: Выбрали новый город: " + city);
			Log.info("***QA: URL города: " + cityURL);
				
			//передаём полученные значения в глобальные переменные
			NavigationBase.currCity = city;
			NavigationBase.currDomain = cutingUrl(cityURL);
				
			findCity.click();
				
			} catch (Exception e) {
				Log.info("Element Not Found");
				ScreenShot.takeScreenShot();
			}
	}
		
	//Проход по таблице с городами в попапе и запись данных (название, домен) в массивы + валидация с url и названием города с сайта
	public void popUpCity() throws Exception{
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
			
		NavigationBase.citySite = new String[NavigationBase.acCount];
		NavigationBase.domainSite = new String[NavigationBase.acCount];
	                        
	    int m = 0;
	    int a = 1;
	    int b = 1;
	    for(int j = 0; j < NavigationBase.count.length; j++){
	    	if (j != 0) a = 2;
	        if (j > 1) b++;
		    for (int c = 1; c <= NavigationBase.count[j]; c++){ 
		    	clickNewCity(a,b,c);
		    	NavigationBase.citySite[m] = NavigationBase.currCity;
		    	NavigationBase.domainSite[m] = NavigationBase.currDomain;
			    pagetehnosila.clickCityPopup(1);
			    assertURL(NavigationBase.domainSite[m]);
			    assertCity(NavigationBase.citySite[m]);
			    commonmetods.getHTTPResponseCode();
			    m++;
			}
	    }
	}
	//Проверка наличия городов из попапа в массиве городов из xml и наоборот
	public void checkCityName(String[] args, String[] args1, int f) throws Exception{
		int l;
	    for (int y = 0; y < NavigationBase.arrSize; y++){
	    	for (l = 0; l < NavigationBase.arrSize; l++){
	    		if(args[y].equals(args1[l])){
	    			if(f == 0){
	    				Log.info("***QA: Город " + args[y] + " есть на сайте.");
	    			} else {
	    				Log.info("***QA: Город " + args[y] + " есть в xml-файле.");
	    			}
	    			break;
	    		}
	        } 
	    	if (l == NavigationBase.arrSize) {
	    		if(f == 0){
	    			Log.info("***QA: Ошибка! Города " + args[y] + " нет на сайте!");
	    		} else{
	    			Log.info("***QA: Ошибка! Города " + args[y] + " в xml-файле!");
	    		}
	    	}
	    }
	}

	//Проверка наличия доменов городов из попапа в массиве городов из xml и наоборот
	public void checkCityDomain(String[] args, String[] args1, int f) throws Exception{	
		int h;
		for (int y = 0; y < NavigationBase.arrSize; y++){
			for (h = 0; h < NavigationBase.arrSize; h++){
				if(args[y].equals(args1[h])){
					if(f == 0){
						Log.info("***QA: Домен " + args[y]+ " из xml совпадает с доменом с сайта.");
					}  else {
	    				Log.info("***QA: Домен " + args[y]+ " с сайта совпадает с доменом из xml-файла.");
	    			}
       			break;
       		} 
       	}
       	if (h == NavigationBase.arrSize) {
    		if(f == 0){
    			Log.info("***QA: Ошибка! Домена " + args[y] + " нет на сайте!");
    		} else{
    			Log.info("***QA: Ошибка! Домена " + args[y] + " в xml-файле!");
    		}
   		}
       }	 	
	}
}

