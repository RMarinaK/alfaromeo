package tehnosila.tehnosila_automation.pages;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;


/**
 * @author MRasstrigina
 *
 */

public class Page_Action extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Action.class);
	//DSE: url to check page
	//protected String URL_MATCH = super.getBaseURL()+"#/action/sale_5";
	
	@FindBy(xpath="//a[@class='button show-all-items']")
	private WebElement actioncatalog; // Кнопка Перейти к покупкам
	
	@FindBy(xpath="//div[@class='item'][2]")
	private WebElement clickactioncatalogitem; // Выбор второго блока товаров в акции честные цены
	
	@FindBy(xpath = "//span[@class='items-per-page-view']") 
	private WebElement itemsperpageview; // Найдено N товаров в наличии
	
	@FindBy(xpath = "//div[@id='promoword-banner']/span")
	private WebElement getpcode; // Промокод из шильдика 
	
	@FindBy(xpath = "//*[@id='popup-action-buy-cheaper']/div/div[2]/div/div/div[2]/div/span")
	private WebElement getcheaperprice; // Ценник в попапе Нашли этот товар дешевле?
	
	@Override
	void tryToOpen() {
//		driver.get(this.URL_MATCH);
	}
	
	// Получение промокода из шильдика
	public void getCode() {
		Pattern pattern = Pattern.compile("\\d+");
		String stringpre = getpcode.getText();
		Matcher matcher = pattern.matcher(stringpre);

		int start = 0;
		while (matcher.find(start)) {
			String erw = stringpre.substring(matcher.start(), matcher.end());
			NavigationBase.pcode = Integer.valueOf(erw);
			NavigationBase.promocode = "ПРОМО" + stringpre.substring(matcher.start(), matcher.end());
			Log.info("***QA: promocode "+ NavigationBase.promocode);
			start = matcher.end();
		}
	}
	
	// жмаканье на "Перейти к покупкам"
	public void clickActionCatalog() throws Exception{
		try {
			actioncatalog.click(); 
			Log.info("жмаканье на Перейти к покупкам");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
	        ScreenShot.takeScreenShot();       
	    }  
	}
	
	// жмаканье на второй блок товаров в акции честные цены
	public void clickActionCatalogItem() throws Exception{
		try {
			clickactioncatalogitem.click(); 
			Log.info("жмаканье на второй блок товаров в акции честные цены");
		}
		catch(Exception e) {      
		   	Log.info("Element Not Found");     
	        ScreenShot.takeScreenShot();       
	    }  
	}
		
	// жмаканье на первый блок товаров в категории 
/*	public void clickCatalogItemInCategory() throws Exception{
		try {
			clickcatalogitemincategory.click(); 
			Log.info("жмаканье на первый блок товаров в категории");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
	        ScreenShot.takeScreenShot();       
	    }  
	}*/
	
	// получение количество товаров с сайта
	public void getTotalNubmer(){
		Pattern pattern = Pattern.compile("\\d+");
		String word = itemsperpageview.getText(); // мой пример строки
		Matcher matcher = pattern.matcher(word);
		int start = 0;
		while (matcher.find(start)) {
			NavigationBase.ptotalnumbersite = word.substring(matcher.start(), matcher.end());
			Log.info("***QA: ptotalnumbersite "+ NavigationBase.ptotalnumbersite);
			start = matcher.end();
		}
	}

	// проверка попадания количества товаров с сайта в диапазон (totalnumber-totalnumber*0.05; totalnumber+totalnumber+0.05)
	public void assertTotalNumber(double percent){
		int totatlnumbersite = Integer.valueOf(NavigationBase.ptotalnumbersite);
		int totatlnumber = Integer.valueOf(NavigationBase.ptotatlnumber);
		int mintotatlnumber = (int)Math.ceil(totatlnumber - totatlnumber*percent);
		int maxtotatlnumber = (int)Math.ceil(totatlnumber + totatlnumber*percent);
		if (totatlnumbersite > mintotatlnumber && totatlnumbersite < maxtotatlnumber) {
			Log.info("***QA: " + mintotatlnumber + "<" + totatlnumbersite + "<" + maxtotatlnumber);
		} else {
			Log.info("***QA: Кол-во товаров с сайта не соотвествует кол-ву товаров с апи");
		}
	}
	
	// Получение цены из попапа Нашли этот товар дешевле?
	public void getСheaperPrice() {
		Pattern pattern = Pattern.compile("\\d+");
		String stringprice = getcheaperprice.getText();
		stringprice = stringprice.replaceAll(" ", "");
		Matcher matcher = pattern.matcher(stringprice);
		int start = 0;
		while (matcher.find(start)) {
			NavigationBase.cheaperprice = stringprice.substring(matcher.start(), matcher.end());
			Log.info("***QA: cheaper price "+ NavigationBase.cheaperprice);
			start = matcher.end();
		}
	}
}
