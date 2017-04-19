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
	private WebElement actionsavemoney; // Картинка экономить просто
	
	@FindBy(xpath="//a[@href='/action/sale_5']/img")
	private WebElement actionsale5; // Картинка скидка 5% при онлайн-оплате
	
	@FindBy(xpath="//a[@href='/action/chestnye_ceny']/img")
	private WebElement actionfairprice; // Картинка честные цены
	
	@FindBy(xpath="//a[@href='/action/supertroyka']/img")
	private WebElement actionsuperthree; // Картинка супертройки
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Рассрочку"
	public void clickActionRassrochka() throws Exception {
	//	try {
		actionrassrochka.click(); 
		Log.info("жмаканье на Рассрочку");
  
	}	
	
	// жмаканье на "Акция Экономить - просто!"
	public void clickActionSaveMoney() throws Exception {
		actionsavemoney.click(); 
		Log.info("жмаканье на Экономить - просто!");
  
	}	
	
	// жмаканье на "Акция Скидка 5% при онлайн-оплате"
	public void clickActionSale5() throws Exception {
		actionsale5.click(); 
		Log.info("жмаканье на Скидка 5% при онлайн-оплате");
  
	}	
	
	// жмаканье на "Акция Честные цены"
	public void clickActionFairPrice() throws Exception {
		actionfairprice.click(); 
		Log.info("жмаканье на Честные цены");
  
	}	
	
	// жмаканье на "Акция супертройка"
	public void clickActionSuperThree() throws Exception {
		actionsuperthree.click(); 
		Log.info("жмаканье на Супертрйоку");
  
	}	
}
