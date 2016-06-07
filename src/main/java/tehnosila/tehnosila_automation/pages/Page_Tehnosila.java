package tehnosila.tehnosila_automation.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ApplicationManager;

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

public class Page_Tehnosila extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Tehnosila.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(id = "all-goods")
	public WebElement allgoods; // Каталог товаров
	
	@FindBy(xpath="//div[@class='cat-top-level']/ul/li/a[contains(text(),'Телевизоры, аудио, видео')]")
	private WebElement tvaudiovideo; // Телевизоры, аудио, видео
	
	@FindBy(xpath="//div[@id='category-172']/ul/li/a[contains(text(),'Телевизоры')]")
	private WebElement tv; // Телевизоры
	
	@FindBy(xpath="//div[@id='category-364']/ul/li/a[contains(text(),'LED телевизоры')]")
	private WebElement ledtv; // LED телевизоры
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на кнопку "Каталог товаров"
	public void clickAllGoods() {
		allgoods.click(); 
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
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.moveToElement(allgoods)
			.moveToElement(tvaudiovideo)
			.moveToElement(tv)
			.moveToElement(ledtv)
			.click(ledtv)
				.release(ledtv)
				.build();
	   dragAndDrop.perform();
	}

	
}
