package tehnosila.tehnosila_automation.pages.Desctop;
/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.PagesBase;

public class Page_CatalogTv_i_videoTelevizoryTelevizory extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/catalog/tv_i_video/televizory/televizory";
	
	@FindBy(xpath="//div[@class='item-info-wrap first']")
	private WebElement iteminfowrapfirst; // Первый телевизор
	
	@FindBy(xpath="//a[@id='open-self-delivery-description']/../../../../..")//a[@id='open-courier-description']
	private WebElement openselfdeliverydescription; // Первый телевизор самовывоз
	
	@FindBy(xpath="//a[@id='open-courier-description']/../../../../..")//a[@id='open-courier-description']
	private WebElement opencourierdescription; // Первый телевизор с доставкой
	
	@Override
	protected
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage("http://www.tehnosila.ru/catalog/tv_i_video");
	}
	
	public void clickOpenSelfDeliveryDescription() throws Exception {
		try {
			openselfdeliverydescription.click(); 
			Log.info("Первый телевизор");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         } 
	}	
	
	// жмаканье на Первый телевизор с доставкой
	public void clickOpenCourierDescription() throws Exception {
		try {
			opencourierdescription.click(); 
			Log.info("Первый телевизор с доставкой");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         } 
	}

}
