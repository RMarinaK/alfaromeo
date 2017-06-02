package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;


/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Cart extends PagesBase{
//	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Cart.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(id="order-button")
	private WebElement buttonorder; // Кнопка "Оформить заказ"
	
	@FindBy(xpath="//div[2]/label[1][@class='input-title']")
	private WebElement rcourierdelivery; // Радиобаттон "Курьерская доставка"
	
	@FindBy(xpath="//*[@id='cart-form']/div[5]/div[1]/div[2]/div[1]")
	private WebElement havebonuscard; // Блок у меня есть бонусная карта
	
	@FindBy(id="Cart_codeLoyalty")
	private WebElement bonuscardform; // Форма Номер бонусной карты
	
	@FindBy(xpath="//*[@id='promo-code-fields']/input[2]")
	private WebElement buttonapply;
	
	protected boolean isNecessaryToChangeParam(String param){
		if(param.equals(" ")||param.equals("")){
			return false;
		}else{
			return true;
		}		
	}
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на "Офорить заказ"
	public void clickButtonOrder() {
		buttonorder.click();
		Log.info("жмаканье на Оформить заказ");
	}

	// жмаканье на "Курьерская доставка"
	public void clickRCourierDelivery() throws Exception {
			rcourierdelivery.click(); 
			Log.info("жмаканье на Курьерская доставка");
	}
	
	// Ожидание лоэдера
	public void waitCartLoadingLayer() throws Exception {
		try {
			app.getNavigationHelper().waitInvisible(By.xpath("//div[@id='cart-loading-layer']"), 5);
			Log.info("Лоэдер отработал");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// Жмаканье ан блок У меня есть бонусная карта
	public void clickHaveBonusCard() {
		havebonuscard.click();
		Log.info("Жмаканье на блок У меня есть бонусная карта");
	}
	
	// Ввод бонусной карты
	public void setBonusCardForm(String string) {
		if(isNecessaryToChangeParam(string)){
			bonuscardform.click();
			bonuscardform.clear();
			bonuscardform.sendKeys(string);
		}
	}
	
	// Жмаканье на применить
	public void clickButtonApply() {
		buttonapply.click();
		Log.info("Жмаканье на Применить");
	}
}
