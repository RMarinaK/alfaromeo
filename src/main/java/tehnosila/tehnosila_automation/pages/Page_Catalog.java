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

public class Page_Catalog extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Catalog.class);
	//DSE: url to check page
//	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(xpath="//a[@id='open-self-delivery-description']/../../../../..")//a[@id='open-courier-description']
	private WebElement openselfdeliverydescription; // Первый товар самовывоз
	
	@FindBy(xpath="//a[@id='open-courier-description']/../../../../..")//a[@id='open-courier-description']
	private WebElement opencourierdescription; // Первый товар  с доставкой
	

	
	@Override
	void tryToOpen() {
	//	driver.get(this.URL_MATCH);
	}
	
	public void clickOpenSelfDeliveryDescription() throws Exception {
		try {
			openselfdeliverydescription.click(); 
			Log.info("Первый товар самовывоз");
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
			Log.info("Первый товар с доставкой");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         } 
	}

}
