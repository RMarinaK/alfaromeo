package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;

/**
 * @author MRasstrigina
 *
 */

public class Page_Cart extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Cart.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(id="ordering")
	private WebElement buttonordering; // Кнопка "Оформить заказ"
	
	@FindBy(xpath="//input[@id='courier']/../span")
	private WebElement rcourierdelivery; // Радиобаттон "Курьерская доставка"
	
	@FindBy(id="loading-layer")
	private WebElement loadinglayer; // 
	
	
	@FindBy(xpath="//a[@class='title']")
	private WebElement promocodefield; // Поле "Код купона" для клика
	
	@FindBy(xpath="//input[@id='Cart_promoCode']")
	private WebElement cartpromocode; // Поле "Код купона"
	
	protected boolean isNecessaryToChangeParam(String param){
		if(param.equals(" ")||param.equals("")){
			return false;
		}else{
			return true;
		}
	}   
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Курьерская доставка"
	public void clickRCourierDelivery() throws Exception {
	//	try {
			rcourierdelivery.click(); 
			Log.info("жмаканье на Курьерская доставка");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	
	
	public void clickPromoCodeField() throws Exception {
		//	try {
				promocodefield.click(); 
				Log.info("жмаканье на У меня есть код купона на скидку");
	}
	
	// Ожидание лоэдера
	public void waitCartLoadingLayer() throws Exception {
		try {
			app.getNavigationHelper().waitInvisible(By.xpath("//div[@id='cart-loading-layer']"), 10);
			Log.info("Лоэдер отработал");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
	
	// жмаканье на "Офорить заказ"
	public void clickButtonOrdering() throws Exception {
	//	try {
			app.getNavigationHelper().waitVisible(buttonordering,10);
			buttonordering.click(); 
			Log.info("жмаканье на Офорить заказ");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	
	// Вставить купон
	public void setСartPromoСode(String string) {
		if(isNecessaryToChangeParam(string)){
			cartpromocode.click();
			cartpromocode.clear();
			cartpromocode.sendKeys(string);
		}
	}
}
