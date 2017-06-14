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

public class Term_Page_CatalogTv_i_videoTelevizoryTelevizoryID extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Term_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(id="addToCart")
	private WebElement addtocart; // Кнопка "Купить"
	
	@FindBy(xpath="//li[@id='footerBasket']/a")
	private WebElement buttontocart; // Кнопка "Корзина"
	
	@Override
	protected
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Купить"
	public void clickAddToCart() throws Exception {
		try {
			addtocart.click();
			Log.info("жмаканье на Купить");
		}
		catch(Exception e) {      
			Log.info("Element Not Found");     
			ScreenShot.takeScreenShot();       
		}  
	}
	
	// жмаканье на "Корзина"
	public void clickButtonToCart() throws Exception {
		try {
			buttontocart.click();
			Log.info("жмаканье на Корзина");
		}
		catch(Exception e) {      
			Log.info("Element Not Found");     
			ScreenShot.takeScreenShot();       
		}  
	}
}
