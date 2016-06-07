package tehnosila.tehnosila_automation.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ApplicationManager;
import tehnosila.tehnosila_automation.pages.PagesBase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Tehnosila extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Tehnosila.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(id = "menu_trigger")
	public WebElement menutrigger; // Меню
		

	@FindBy(xpath="//a[@href='http://m.tehnosila.ru/catalog']") //  div[@id='menu_popup']/ul/li/a/   xpath = "//span[contains(text(),'Каталог товаров')]/../.."
	public WebElement catalog; // Каталог товаров
	
	
	@FindBy(xpath="//a[@href='http://m.tehnosila.ru/catalog/tv_i_video']")
	private WebElement tvaudiovideo; // Телевизоры, аудио, видео
	
	@FindBy(xpath="//a[@href='http://m.tehnosila.ru/catalog/tv_i_video/televizory']")
	private WebElement tv; // Телевизоры
	
	@FindBy(xpath="//a[@href='http://m.tehnosila.ru/catalog/tv_i_video/televizory/televizory']")
	private WebElement ledtv; // LED телевизоры
	
	@Override
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
		catalog.click(); 
	}	
	
	// жмаканье на кнопку "Телевизоры, аудио, видео"
	public void clickTVAudioVideo() {
		tvaudiovideo.click(); 
	}
	
	// жмаканье на кнопку "Телевизоры"
	public void clickTV() {
		tv.click(); 
	}
	
	// жмаканье на кнопку "LED телевизоры"
	public void clickLEDTV() {
		ledtv.click(); 
	}
	
}
