package tehnosila.tehnosila_automation.pages;


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
		String stringpre = "ПРОМО" + getPCode();
		NavigationBase.psolrarticle = "";
		String productpart = stringpre.substring(0);
		NavigationBase.psolrarticle = productpart.substring(0, productpart.indexOf("%"));
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
		String stringpre = itemsperpageview.getText();
		NavigationBase.ptotalnumbersite = "";
		String productpart = stringpre.substring(0);
		NavigationBase.ptotalnumbersite = productpart.substring(8, productpart.indexOf(" товаров в наличии"));
		Log.info("***QA: ptotalnumbersite "+ NavigationBase.ptotalnumbersite);
	/*	String stringpre = itemsperpageview.getText();
		int start = stringpre.indexOf("Найдено");
		NavigationBase.ptotalnumbersite = "";
		if (start > 0) {
			String productpart = stringpre.substring(start +1);
			NavigationBase.ptotalnumbersite  = productpart.substring(0, productpart.indexOf("товаров в наличии"));
		}
		Log.info("***QA: ptotatlnumber "+ NavigationBase.ptotalnumbersite );*/
	}

	// проверка попадания количества товаров с сайта в диапазон (totalnumber-totalnumber*0.05; totalnumber+totalnumber+0.05)
	public void assertTotalNumber(){
		int totatlnumbersite = Integer.valueOf(NavigationBase.ptotalnumbersite);
		int totatlnumber = Integer.valueOf(NavigationBase.ptotatlnumber);
		int mintotatlnumber = (int)Math.ceil(totatlnumber - totatlnumber*0.05);
		int maxtotatlnumber = (int)Math.ceil(totatlnumber + totatlnumber*0.05);
		if (totatlnumbersite > mintotatlnumber || totatlnumbersite < maxtotatlnumber) {
			Log.info("***QA: " + mintotatlnumber + "<" + totatlnumbersite + "<" + maxtotatlnumber);
		}
	}
}
