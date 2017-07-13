package tehnosila.tehnosila_automation.pages.Mobile;


import tehnosila.tehnosila_automation.pages.PagesBase;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;


/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Tehnosila extends PagesBase{
//	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Tehnosila.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(id = "menu_trigger")
	public WebElement menutrigger; // Меню
	
//	@FindBy(xpath = "//*[@id='menu_popup']/ul/li[1]/a/span")
//	public WebElement authorization; // Войти на сайт
	
	@Override
	protected
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage(URL_MATCH);
	}
	
	// жмаканье на кнопку "Меню"
	public void clickMenuTrigger() {
		menutrigger.click(); 
	}	
	
	// жмаканье на кнопку "Каталог товаров"
	public void clickCatalog() {
		if(!app.getNavigationHelper().waitPresense(By.xpath("//div[@id='menu_popup']"), 0))
		    return;
		WebElement catalog = driver.findElement(By.xpath("//a[contains(@href,  '" + URL_MATCH + "catalog')]"));
		catalog.click(); 
	}	
	
	// жмаканье на кнопку "Телевизоры, аудио, видео"
	public void clickTVAudioVideo() {
		WebElement tvaudiovideo = driver.findElement(By.xpath("//a[contains(@href,  '" + URL_MATCH + "catalog/tv_i_video')]"));
		tvaudiovideo.click(); 
	}
	
	// жмаканье на кнопку "Телевизоры"
	public void clickTV() {
		WebElement tv = driver.findElement(By.xpath("//a[contains(@href,  '" + URL_MATCH + "catalog/tv_i_video/televizory')]"));
		tv.click(); 
	}
	
	// жмаканье на кнопку "LED телевизоры"
	public void clickLEDTV() {
		WebElement ledtv = driver.findElement(By.xpath("//a[contains(@href,  '" + URL_MATCH + "catalog/tv_i_video/televizory/televizory')]"));
		ledtv.click(); 
	}
	
	//клик по Войти на сайт
	public void clickMenuCabinet() throws Exception {
		if(!app.getNavigationHelper().waitPresense(By.xpath("//*[@id='menu_popup']/ul/li[1]/a/span"), 0))
				return;
		WebElement authorization = driver.findElement(By.xpath("//a[contains(@href,  '" + URL_MATCH + "passport/login')]"));
		authorization.click();
	}
	/*	try {
			authorization.click();
			Log.info("Меню Кабинет");
		} catch (Exception e) {
			Log.info("Element Not Found");
		//	ScreenShot.takeScreenShot();
		} 
	}*/
}
