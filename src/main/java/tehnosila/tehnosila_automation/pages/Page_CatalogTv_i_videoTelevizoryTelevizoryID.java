package tehnosila.tehnosila_automation.pages;
/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;

public class Page_CatalogTv_i_videoTelevizoryTelevizoryID extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	//@FindBy(xpath="//a[@class='button yellow-flat pressable to-cart flocktory-add-to-cart cart gtm-process-add-to-cart flix_cart_click_check']")
	@FindBy(xpath = "//a[contains(text(),'                        Купить                    ')]")
	//*[@id="generic-area"]/div/div[1]/div[3]/a[2]
	private WebElement buttonbuy; // Кнопка "Купить"
	
	@FindBy(id="popup-button-to-cart")
	private WebElement popupbuttontocart; // Кнопка "Перейти в корзину"

	@FindBy(xpath="//strong[@itemprop='sku']")
	private WebElement itemprop; // Айдишник товара
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	
	// вытягивание номера заказа
	public String getItemprop(){
		return itemprop.getText();
	}
		
	//	вытягивание Артикула товара
	public void logItemprop(){
		app.getNavigationHelper().waitVisible(itemprop,10);
		Log.info("***QA: Артикул товара "+ getItemprop());   
	}	
	
	// жмаканье на "Купить"
	public void clickButtonBuy() throws Exception {
//		try {
			app.getNavigationHelper().waitVisible(buttonbuy,10);
			buttonbuy.click(); 
			Log.info("жмаканье на Купить");
	/*	}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	 
		
	// жмаканье на "Перейти в корзину"
	public void clickPopupButtonToCart() throws Exception {
	//	try {
			app.getNavigationHelper().waitVisible(popupbuttontocart,10);
			popupbuttontocart.click(); 
			Log.info("жмаканье на Перейти в корзину");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	 
	
}
