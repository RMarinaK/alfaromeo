package tehnosila.tehnosila_automation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author MRasstrigina
 *
 */

public class Page_Actions extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Actions.class);
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
	
	Iframes iframes = MyPageFactory.getPage(Iframes.class);
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Рассрочку"
	public void clickActionRassrochka(){
	//	try {
		actionrassrochka.click(); 
		Log.info("жмаканье на Рассрочку");
 	}	
	CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
	// жмаканье на "Акция Экономить - просто!"
	public void clickActionSaveMoney(){
	/*	if (actionsavemoney.isEnabled()) {
		//	commonmetods.scrolling();
			iframes.exitIframes();
		}*/
		actionsavemoney.click(); 
		Log.info("жмаканье на Экономить - просто!");
		
  	}	
	
	// жмаканье на "Акция Скидка 5% при онлайн-оплате"
	public void clickActionSale5(){
		actionsale5.click(); 
		Log.info("жмаканье на Скидка 5% при онлайн-оплате");
		
  	}	
	
	// жмаканье на "Акция Честные цены"
	public void clickActionFairPrice(){
		actionfairprice.click(); 
		Log.info("жмаканье на Честные цены");
  	}	
	
	// жмаканье на "Акция супертройка"
	public void clickActionSuperThree(){
		actionsuperthree.click(); 
		Log.info("жмаканье на Супертрйоку");
	}	
	
	
}
