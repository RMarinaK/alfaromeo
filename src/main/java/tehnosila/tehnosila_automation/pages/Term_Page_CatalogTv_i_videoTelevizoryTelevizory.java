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

public class Term_Page_CatalogTv_i_videoTelevizoryTelevizory extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Term_Page_CatalogTv_i_videoTelevizoryTelevizory.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/catalog/tv_i_video/televizory/televizory";
	
	@FindBy(xpath="//div[@id='productsCarousel']/div/div/div/div/div/div/a")
	private WebElement iteminfowrapfirst; // Первый телевизор
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage(URL_MATCH);
	}
	
	// жмаканье на "Первый телевизор"
	public void clickItemInFowrapFirst() throws Exception {
		try {
			iteminfowrapfirst.click();
			Log.info("Первый телевизор");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }  
	}	

}
