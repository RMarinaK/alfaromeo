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

public class Page_ActionRassrochka extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_ActionRassrochka.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/action/rassrochka";
	

	@FindBy(xpath="//a[@class='button show-all-items go-to-catalog']")
	private WebElement actionrassrochkacatalog; // Кнопка Перейти к покупкам
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Перейти к покупкам"
	public void clickActionRassrochkaCatalog() throws Exception {
	//	try {
		actionrassrochkacatalog.click(); 
		Log.info("жмаканье на Перейти к покупкам");
  
	}	
	
	
}
