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

public class Page_Cart extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Cart.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(xpath="//div[@id='button-buy-in-order']/a[@id='ordering']")
	private WebElement buttonordering; // Кнопка "Оформить заказ"
	
	@FindBy(xpath="//input[@id='courier']/../span")
	private WebElement rcourierdelivery; // Радиобаттон "Курьерская доставка"
	
	@FindBy(id="loading-layer")
	private WebElement loadinglayer; // 
	
	
	@FindBy(xpath="//a[@class='title']")
	private WebElement promocodefield; // Поле "Код купона" для клика
	
	@FindBy(xpath="//input[@id='Cart_promoCode']")
	private WebElement cartpromocode; // Поле "Код купона"
	
	@FindBy(xpath="//a[@class='apply_bonus_btn button white-flat submit']")
	private WebElement applybonusbtn; // Кнопка Применить
	
	@FindBy(xpath="//a[@class='plus pressable gtm-process-add-to-cart']")
	private WebElement buttonplus; // Кнопка плюс, увеличение кол-ва товара
	
	@FindBy(xpath="//a[@class='minus pressable gtm-process-remove-from-cart']")
	private WebElement buttonminus; // Кнопка минус, уменьшение кол-ва товара
	
	@FindBy(xpath="//a[contains(@href,'http://www.tehnosila.ru/cart/removeItems')]")
	private WebElement buttondelete; // Кнопка удалить, удаляет товар из корзины
	
	@FindBy(xpath="//input[@class='stub text']")
	private WebElement quantityitem; // Количество товара
	
	@FindBy(xpath="//div[contains(text(),'Ваша корзина пуста')]")
	private WebElement emptycart; // Радиобаттон "Курьерская доставка"
	
//	@FindBy(xpath="//*[@id='cart-contents']/div[4]/a")
//	private WebElement emptycart;
	
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
	
	// жмаканье на "Курьерская доставка"
	public void clickRCourierDelivery() throws Exception {
	//	try {
			rcourierdelivery.click(); 
			Log.info("жмаканье на Курьерская доставка");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	
	
	// жмаканье на "У меня есть код купона на скидку"
	public void clickPromoCodeField() {
		promocodefield.click(); 
		Log.info("жмаканье на У меня есть код купона на скидку");
	}
	
	// Ожидание лоэдера
	public void waitCartLoadingLayer() throws Exception {
		try {
			app.getNavigationHelper().waitInvisible(By.xpath("//div[@id='cart-loading-layer']"), 10);
			Log.info("Лоэдер отработал");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
	
	// жмаканье на "Офорить заказ"
	public void clickButtonOrdering() {
			app.getNavigationHelper().waitVisible(buttonordering,10);
			buttonordering.click(); 
			Log.info("жмаканье на Офорить заказ");
	}	
	// Вставить купон
	public void setСartPromoСode(String string) {
		if(isNecessaryToChangeParam(string)){
			cartpromocode.click();
			cartpromocode.clear();
			cartpromocode.sendKeys(string);
		}
	}

	public void clickButtonApplyBonus() {

		applybonusbtn.click(); 
		Log.info("жмаканье на Применить");
   	}	
	
	// жмаканье на "плюс" увеличение кол-ва товара
	public void clickButtonPlus() {
		String qi = quantityitem.getAttribute("value");
		buttonplus.click();
		Log.info("жмаканье на плюс");
		String qis = quantityitem.getAttribute("value");
		int a = Integer.parseInt(qi);
		int summ = a+1;
		int b = Integer.parseInt(qis);
		if (summ == b) Log.info("Количество товара равно " + qis); else Log.info("Не равно");
	}
	
	// жмаканье на "минус" уменьшение кол-ва товара
	public void clickButtonMinus() {
		String qi = quantityitem.getAttribute("value");
		buttonminus.click(); 
		Log.info("жмаканье на минус");
		String qis = quantityitem.getAttribute("value");
		int a = Integer.parseInt(qi);
		int summ = a-1;
		int b = Integer.parseInt(qis);
		if (summ == b) Log.info("Количество товара равно " + qis); else Log.info("Не равно");
	}
	
	// жмаканье на Удалить
	public void clickButtonDelete() {
		buttondelete.click();
		Log.info("жмаканье на удалить");
	}	
	
	/*		if( driver.findElement(By.xpath("//*[@id='cart-contents']/div[4]")).isDisplayed()){
			Log.info("Корзина пуста");
			} else{
			Log.info("Корзина не пуста");
			}  
																									// Варианты проверки корзины, пока не удалять
			try {
			driver.findElement(By.xpath("//*[@id='cart-contents']/div[4]")).isDisplayed();
			Log.info("Корзина пуста");
			} catch (Exception e){
				Log.info("Корзина не пуста");
		} 
	} */
	// Assert.assertTrue(driver.findElement(By.xpath("//*[@id='cart-contents']/div[4]")).isDisplayed(),"Корзина не пуста");
	

	
	// Проверка пустой корзины
	
	public void emptycart() {
		app.getNavigationHelper().waitVisible(emptycart,10);
	} 
}
