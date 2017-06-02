package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;


/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Cart extends PagesBase{
//	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Cart.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(id="order-button")
	private WebElement buttonorder; // Кнопка "Оформить заказ"
	
	@FindBy(xpath="//div[2]/label[1][@class='input-title']")
	private WebElement rcourierdelivery; // Радиобаттон "Курьерская доставка"
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Офорить заказ"
	public void clickButtonOrder() {
		buttonorder.click();
		Log.info("жмаканье на Оформить заказ");
	}

	// жмаканье на "Курьерская доставка"
	public void clickRCourierDelivery() throws Exception {
			rcourierdelivery.click(); 
			Log.info("жмаканье на Курьерская доставка");
	}
	
	// Ожидание лоэдера
	public void waitCartLoadingLayer() throws Exception {
		try {
			app.getNavigationHelper().waitInvisible(By.xpath("//div[@id='cart-loading-layer']"), 5);
			Log.info("Лоэдер отработал");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}

}
