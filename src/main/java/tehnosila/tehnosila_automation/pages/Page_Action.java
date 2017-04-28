package tehnosila.tehnosila_automation.pages;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//a[@class='item first-in-row']")
	private WebElement clickcatalogitemincategory; // Выбор первого блока товаров в категории
	
	@FindBy(xpath = "//span[@class='items-per-page-view']") 
	private WebElement itemsperpageview; // Найдено N товаров в наличии 
	
	@Override
	void tryToOpen() {
//		driver.get(this.URL_MATCH);
	}
	
	// вытягивание промокода из шильдика товара
	public String getPCode(){ 
		return driver.findElement(By.xpath("//div[@id='promoword-banner']/span")).getText();
	}

	public void getCode() {
	//	Log.info("***QA: Message "+ getPCode());
	//	NavigationBase.pcode = Integer.valueOf(getPCode());
		//String stringpre = "ПРОМО" + getPCode();
		String stringpre = getPCode();
		NavigationBase.psolrarticle = "";
		String productpart = stringpre.substring(0);
		NavigationBase.psolrarticle = productpart.substring(0, productpart.indexOf("%"));
		NavigationBase.pcode = Integer.valueOf(NavigationBase.psolrarticle);
		NavigationBase.psolrarticle = "ПРОМО" + NavigationBase.psolrarticle;
		Log.info("***QA: Message "+ NavigationBase.psolrarticle);
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
	public void clickCatalogItemInCategory() throws Exception{
		try {
			clickcatalogitemincategory.click(); 
			Log.info("жмаканье на первый блок товаров в категории");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
	        ScreenShot.takeScreenShot();       
	    }  
	}
	
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
	public void assertTotalNumber(){
		int totatlnumbersite = Integer.valueOf(NavigationBase.ptotalnumbersite);
		int totatlnumber = Integer.valueOf(NavigationBase.ptotatlnumber);
		int mintotatlnumber = (int)Math.ceil(totatlnumber - totatlnumber*0.05);
		int maxtotatlnumber = (int)Math.ceil(totatlnumber + totatlnumber*0.05);
		if (totatlnumbersite > mintotatlnumber && totatlnumbersite < maxtotatlnumber) {
			Log.info("***QA: " + mintotatlnumber + "<" + totatlnumbersite + "<" + maxtotatlnumber);
		} else {
			Log.info("***QA: Кол-во товаров с сайта не соотвествует кол-ву товаров с апи");
		}
	}
}
