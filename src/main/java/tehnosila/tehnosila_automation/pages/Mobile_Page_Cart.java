package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Cart extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Cart.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(id="order-button")
	private WebElement buttonorder; // Кнопка "Оформить заказ"
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Офорить заказ"
	public void clickButtonOrder() {
		buttonorder.click(); 
	}	

}
