package tehnosila.tehnosila_automation.pages;

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
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Офорить заказ"
	public void clickButtonOrdering() throws Exception {
		try {
			buttonordering.click(); 
			Log.info("жмаканье на Офорить заказ");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	 
}
