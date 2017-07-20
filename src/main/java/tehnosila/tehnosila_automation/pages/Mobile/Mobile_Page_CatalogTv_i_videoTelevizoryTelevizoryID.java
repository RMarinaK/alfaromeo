package tehnosila.tehnosila_automation.pages.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import tehnosila.tehnosila_automation.pages.PagesBase;

import java.util.List;

/**
 * @author RasstriginaMK
 *
 */


public class Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID extends PagesBase{
//	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(xpath="//div[1]/a[contains(text(), 'Купить')]")
	private WebElement buttonbuy; // Кнопка "Купить" по апи
	
	@FindBy(id="popup-button-to-cart")
	private WebElement popupbuttontocart; // Кнопка "Перейти в корзину"
	
	@Override
	protected
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Купить"
    public void clickButtonBuy() throws Exception {
    	int y = -100;
        List<WebElement> itemsCount = driver.findElements(By.xpath("//div[@class='product__buttons-wrap']"));
        for(WebElement count: itemsCount)
        {    
            if (count.isDisplayed()) {
				String code = "window.scroll(" + (count.getLocation().x) + "," + (count.getLocation().y + y) + ");";
			    ((JavascriptExecutor)driver).executeScript(code, count);
                count.click();
                Log.info("жмаканье на Купить");
                break;
            }

        }
    }
	
	// жмаканье на "Кнопка "Перейти в корзину"
	public void clickPopupButtonToCart() {
		popupbuttontocart.click();
		Log.info("жмаканье на Перейти в корзину");
	}
	
}
