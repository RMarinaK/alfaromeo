package tehnosila.tehnosila_automation.pages;
/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;

public class Page_CatalogTv_i_videoTelevizoryTelevizoryID extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(xpath="//a[@class='button yellow-flat pressable to-cart flocktory-add-to-cart cart gtm-process-add-to-cart flix_cart_click_check']")
	private WebElement buttonbuy; // Кнопка "Купить"
	
	@FindBy(id="popup-button-to-cart")
	private WebElement popupbuttontocart; // Кнопка "Перейти в корзину"
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Купить"
	public void clickButtonBuy() throws Exception {
		try {
			buttonbuy.click(); 
			Log.info("жмаканье на Купить");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	 
		
	// жмаканье на "Перейти в корзину"
	public void clickPopupButtonToCart() throws Exception {
		try {
			popupbuttontocart.click(); 
			Log.info("жмаканье на Перейти в корзину");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	 
	
}
