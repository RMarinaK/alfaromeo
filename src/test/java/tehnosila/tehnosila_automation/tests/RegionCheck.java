/**
 * @author EDanilova
 *
 */

package tehnosila.tehnosila_automation.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegionCheck extends TestBase{
	
	private static Logger Log = LoggerFactory.getLogger(RegionCheck.class);

	// З А П У С К А Е М   Т Е С Т
	@Test
	public void regionTest() throws Exception{

		try { 
			 	
	        	Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
	        	CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
	        	
				//Скачиваем xml-файл с SFTP, создаем txt-файл для записи, извлекаем названия и домены активных городов и записываем в txt-файл
	        	commonmetods.SFTPDownloadFile();
	        	commonmetods.createFile();
	        	commonmetods.ReadXMLFileDOM();
		    
		    	//Извлекаем данные из txt-файла и записываем их в массив
		        FileInputStream fstream = new FileInputStream("/repo/tests/src/test/resources/DDT/RegionCheck/city_list.txt");
		        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		        String strLine;
		        int i = 0;
		        String[] cityXML = new String[NavigationBase.arrSize];
		        String[] domainXML = new String[NavigationBase.arrSize];
		        while ((strLine = br.readLine()) != null){
		        	if (strLine.trim().length() != 0){
		        	   String[] cutStr = strLine.split(",");
					   cityXML[i] = cutStr[0];
					   domainXML[i] = cutStr[1];
		        	   i++;
		        	   }
		            }
		        br.close();
		        
		        pagetehnosila.clickCityPopup(0);

		        //Подсчитываем количество городов в попапе и создаём массив с количеством городов по столбцам 
		        int[] count = new int[4];
		        int acCount = 0;
		        for(int j = 0; j < count.length; j++){
		        	if (j == 0) {
		        		count[j] = pagetehnosila.bigCityCount();
		        	} else {
			        	count[j] = pagetehnosila.otherCityCount(j);
			        }
		        	acCount = acCount + count[j];
		        }
		       
		       //Сравнение кол-ва городов на сайте и в xml
		       commonmetods.CityCountCheck(acCount, NavigationBase.arrSize); 

		        String[] citySite = new String[NavigationBase.arrSize];
		        String[] domainSite = new String[NavigationBase.arrSize];
		        
		        //Проход по таблице с городами в попапе и запись данных (название, домен) в массивы
		        int m = 0;
		        int a = 1;
		        int b = 1;
		        for(int j = 0; j < count.length; j++){
		        	if (j != 0) a = 2;
		        	if (j > 1) b++;
			        for (int c = 1; c <= count[j]; c++){ 
				        pagetehnosila.clickNewCity(a,b,c);
				        citySite[m] = NavigationBase.currCity;
				        domainSite[m] = NavigationBase.currDomain;
				        pagetehnosila.clickCityPopup(1);
				        m++;
				    }
		        }
		       
		       //Проверка наличия городов из попапа в массиве городов из xml
		       int l, h;
		       for (int y = 0; y < NavigationBase.arrSize; y++){
		        	for (l = 0; l < NavigationBase.arrSize; l++){
		        		if(cityXML[y].equals(citySite[l])){
		        		Log.info("Город " + cityXML[y] + " есть на сайте.");
		        			break;
		        		} 
		        	}
		        	if (l == NavigationBase.arrSize) {
		        		Log.info("Ошибка! Города " + cityXML[y] + " нет на сайте!");
	        		}
		        }
		       
		     //Проверка наличия городов из попапа в массиве городов из xml
		       for (int y = 0; y < NavigationBase.arrSize; y++){
		        	for (h = 0; h < NavigationBase.arrSize; h++){
		        		if(domainXML[y].equals(domainSite[h])){
		        			Log.info("Домены города " + cityXML[y] + " совпадают: " + domainXML[y]);
		        			break;
		        		} 
		        	}
		        	if (h == NavigationBase.arrSize) {
		        		Log.info("Ошибка! Домены города " + cityXML[y] + " отличаются!");
	        		}
		        }	        

		}catch (Exception e) {
			e.getMessage();
			} 
	}
	
}