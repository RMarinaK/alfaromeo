package tehnosila.tehnosila_automation.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.PagesBase;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * @author MRasstrigina
 *
 */

public class Term_Page_Tehnosila extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Term_Page_Tehnosila.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	private String url = "http://terminal.tehnosila.ru/";
	
//	@FindBy(xpath="//a[@href='http://terminal.test7.srvtest-tehnosila.ru/catalog/tv_i_video']")
//	private WebElement tvaudiovideo; // Телевизоры, аудио, видео
//	
//	@FindBy(xpath="//a[@href='http://terminal.test7.srvtest-tehnosila.ru/catalog/tv_i_video/televizory']")
//	private WebElement tv; // Телевизоры
//	
//	@FindBy(xpath="//a[@href='http://terminal.test7.srvtest-tehnosila.ru/catalog/tv_i_video/televizory/televizory']")
//	private WebElement ledtv; // LED телевизоры
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
/*	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage(URL_MATCH);
	}*/
	

	// жмаканье на кнопку "Телевизоры, аудио, видео"
	public void clickTVAudioVideo() throws Exception {
		try {
			WebElement tvaudiovideo = driver.findElement(By.xpath("//a[contains(@href,  '" + url + "catalog/tv_i_video')]"));
			tvaudiovideo.click();
			Log.info("Телевизоры, аудио, видео");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
		
	}
	
	// жмаканье на кнопку "Телевизоры"
	public void clickTV() throws Exception {
		try {
			WebElement tv = driver.findElement(By.xpath("//a[contains(@href,  '" + url + "catalog/tv_i_video/televizory')]"));
			tv.click();
			Log.info("Телевизоры");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// жмаканье на кнопку "LED телевизоры"
	public void clickLEDTV() throws Exception {
		try {
			WebElement ledtv = driver.findElement(By.xpath("//a[contains(@href,  '" + url + "catalog/tv_i_video/televizory/televizory')]"));
			ledtv.click();
			Log.info("LED телевизоры");
		}
		catch(Exception e) {      
			Log.info("Element Not Found");     
			ScreenShot.takeScreenShot();       
		}    
	}
	
}
