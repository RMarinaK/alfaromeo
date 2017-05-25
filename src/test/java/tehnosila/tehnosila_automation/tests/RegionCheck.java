/**
 * @author EDanilova
 *
 */

package tehnosila.tehnosila_automation.tests;

import java.io.File;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.WorkWithArr;
import tehnosila.tehnosila_automation.pages.WorkWithSFTP;
import tehnosila.tehnosila_automation.pages.WorkWithXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RegionCheck extends TestBase{
	
	private static Logger Log = LoggerFactory.getLogger(RegionCheck.class);
	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"RegionCheck"+File.separator+"RegionCheck.xls",
                "RegionCheck", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void regionTest(String ftpAdr, String port, String user, String password, String FullPathToPutFile, String FilenameOnFTP, String PathToCreateFile) throws Exception{			 	
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
	    CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
	    WorkWithSFTP workwithsftp = MyPageFactory.getPage(WorkWithSFTP.class);
	    WorkWithXML workwithxml = MyPageFactory.getPage(WorkWithXML.class);
	    WorkWithArr workwitharr = MyPageFactory.getPage(WorkWithArr.class);
	        	
	    workwithsftp.DownloadFileFromFTP(ftpAdr, port, user, password, FullPathToPutFile, FilenameOnFTP);
	    workwithxml.createFile(PathToCreateFile);
	    workwithxml.ReadXMLFileDOM(PathToCreateFile, FullPathToPutFile);
	    workwitharr.getDataFromTxt(PathToCreateFile);
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
		        		if(NavigationBase.cityXML[y].equals(citySite[l])){
		        		Log.info("Город " + NavigationBase.cityXML[y] + " есть на сайте.");
		        			break;
		        		} 
		        	}
		        	if (l == NavigationBase.arrSize) {
		        		Log.info("Ошибка! Города " + NavigationBase.cityXML[y] + " нет на сайте!");
	        		}
		        }
		       
		     //Проверка наличия городов из попапа в массиве городов из xml
		       for (int y = 0; y < NavigationBase.arrSize; y++){
		        	for (h = 0; h < NavigationBase.arrSize; h++){
		        		if(NavigationBase.domainXML[y].equals(domainSite[h])){
		        			Log.info("Домены города " + NavigationBase.cityXML[y] + " совпадают: " + NavigationBase.domainXML[y]);
		        			break;
		        		} 
		        	}
		        	if (h == NavigationBase.arrSize) {
		        		Log.info("Ошибка! Домены города " + NavigationBase.cityXML[y] + " отличаются!");
	        		}
		        }	        
}}