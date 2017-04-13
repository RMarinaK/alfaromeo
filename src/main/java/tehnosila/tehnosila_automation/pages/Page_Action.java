package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;

/**
 * @author MRasstrigina
 *
 */

public class Page_Action extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Action.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/action";
	

	@FindBy(xpath="//a[@href='/action/rassrochka']/img")
	private WebElement actionrassrochka; // Картинка рассрочки
	
	@FindBy(xpath="//a[@href='/action/discount_world']/img")
	private WebElement actionsavemoney; // Картинка рассрочки
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Курьерская доставка"
	public void clickActionRassrochka() throws Exception {
	//	try {
		actionrassrochka.click(); 
		Log.info("жмаканье на Рассрочку");
  
	}	
	
	// жмаканье на "Акция Экономить - просто!"
	public void clickActionSaveMoney() throws Exception {
	//	try {
		actionsavemoney.click(); 
		Log.info("жмаканье на Экономить - просто!");
  
	}	
	
}
