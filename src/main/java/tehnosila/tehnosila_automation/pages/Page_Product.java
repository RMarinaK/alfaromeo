package tehnosila.tehnosila_automation.pages;
import java.util.List;

import org.openqa.selenium.By;
/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page_Product extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Product.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	//@FindBy(xpath="//a[@class='button yellow-flat pressable to-cart flocktory-add-to-cart cart gtm-process-add-to-cart flix_cart_click_check']")
	@FindBy(xpath = "//a[contains(text(),'Купить')]")
	//*[@id="generic-area"]/div/div[1]/div[3]/a[2]
	private WebElement buttonbuy; // Кнопка "Купить"
	
	@FindBy(id="popup-button-to-cart")
	private WebElement popupbuttontocart; // Кнопка "Перейти в корзину"

	@FindBy(xpath="//strong[@itemprop='sku']")
	private WebElement itemprop; // Айдишник товара
	
	@FindBy(xpath="//*[@id='buy-cheaper-link']")
	private WebElement buttonbuycheaper;	// Кнопка Видели дешевле? Снизим цену!
	
	@FindBy(id="ActionBuyCheaper_fio")
	private WebElement actionbuycheaperfio; // Поле "Ваше имя" в попапе Видели дешевле? Снизим цену!
	
	@FindBy(id="ActionBuyCheaper_phone")
	private WebElement actionbuycheaperphone; // Поле "Телефон" в попапе Видели дешевле? Снизим цену!
	
	@FindBy(id="ActionBuyCheaper_email")
	private WebElement actionbuycheaperemail; // Поле "Электронная почта" в попапе Видели дешевле? Снизим цену!
	
	@FindBy(id="ActionBuyCheaper_link")
	private WebElement actionbuycheaperlink;  // Поле "Ссылка на товар" в попапе Видели дешевле? Снизим цену!
	
	@FindBy(id="ActionBuyCheaper_price")
	private WebElement actionbuycheaperprice;  // Поле "Цена товара" в попапе Видели дешевле? Снизим цену!
	
	@FindBy(xpath = "//*[@class='button yellow-flat submit-button pressable']")
	private WebElement buttonsubmit;	// кнопка "Отправить заявку"
	
	@FindBy(xpath = "//*[@class='button yellow-flat close-button pressable']")
	private WebElement buttonclose;

	@FindBy(xpath = "//span[@class = 'user-bonus__value']") //Поле с количеством начисляемых бонусов в карточке товара
	private WebElement bonusAccrueCard;
	
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
	
	
	// вытягивание Артикула товара
	public String getItemprop(){
		return itemprop.getText();
	}
		
	//	вытягивание Артикула товара
	public void logItemprop(){
		app.getNavigationHelper().waitVisible(itemprop,10);
		Log.info("***QA: Артикул товара "+ getItemprop());   
	}	
	
	// жмаканье на "Купить"
	public void clickButtonBuy() throws Exception {
		//By.xpath("//div[@class='cart-block cart']/a") != null
		if (app.getNavigationHelper().isElementPresent(By.xpath("//div[@class='cart-block cart']/a")) == true) {
			List<WebElement> itemsCart = driver.findElements(By.xpath("//div[@class='cart-block cart']/a"));
			for(WebElement count: itemsCart)
			{	
				if (count.isDisplayed()) {
					count.click();
					Log.info("жмаканье на Купить");
					break;
				}
			}
		} else
		{	//By.xpath("//div[@class='cart-block order']/a") != null
			if (app.getNavigationHelper().isElementPresent(By.xpath("//div[@class='cart-block order']/a")) == true) {
				List<WebElement> itemsOrder = driver.findElements(By.xpath("//div[@class='cart-block order']/a"));
				for(WebElement count: itemsOrder)
				{	
					if (count.isDisplayed()) {
						count.click();
						Log.info("жмаканье на Купить");
						break;
					}
				}
			}
		}
	}
		
	// жмаканье на "Перейти в корзину"
	public void clickPopupButtonToCart() throws Exception {
	//	try {
			app.getNavigationHelper().waitVisible(popupbuttontocart,10);
			popupbuttontocart.click(); 
			Log.info("жмаканье на Перейти в корзину");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	 
	
	// жмаканье на Видели дешевле? Снизим цену!
	public void clickButtonBuyCheaper() throws Exception {
			app.getNavigationHelper().waitVisible(buttonbuycheaper, 10);
			buttonbuycheaper.click();
			Log.info("жмаканье на Видели дешевле? Снизим цену!");
		
	}
	
	public void setActonBuyCheaperFio(String string) {
		if(isNecessaryToChangeParam(string)){
			actionbuycheaperfio.click();
			actionbuycheaperfio.clear();
			actionbuycheaperfio.sendKeys(string);
		}
	}
	
	public void setActonBuyCheaperPhone(String string) {
		if(isNecessaryToChangeParam(string)){
			actionbuycheaperphone.click();
			actionbuycheaperphone.clear();
			actionbuycheaperphone.sendKeys(string);
		}
	}
	
	public void setActonBuyCheaperEmail(String string) {
		if(isNecessaryToChangeParam(string)){
			actionbuycheaperemail.click();
			actionbuycheaperemail.clear();
			actionbuycheaperemail.sendKeys(string);
		}
	}
	
	public void setActonBuyCheaperLink(String string) {
		if(isNecessaryToChangeParam(string)){
			actionbuycheaperlink.click();
			actionbuycheaperlink.clear();
			actionbuycheaperlink.sendKeys(string);
		}
	}
	
	public void setActonBuyCheaperPrice(String string) {
		if(isNecessaryToChangeParam(string)){
			actionbuycheaperprice.click();
			actionbuycheaperprice.clear();
			actionbuycheaperprice.sendKeys(string);
		}
	}
	
	// жмаканье на "Отправить заявку"
	public void clickButtonSubmit() throws Exception {
			app.getNavigationHelper().waitVisible(buttonsubmit,10);
			buttonsubmit.click(); 
			Log.info("жмаканье на Отправить заявку");   
	}
	
	// жмаканье на "Закрыть"
	public void clickButtonClose() throws Exception {
			app.getNavigationHelper().waitVisible(buttonclose,10);
			buttonclose.click(); 
			Log.info("жмаканье на Закрыть");   
	}
	

	//Получение количества бонусов к начислению   @author EDanilova
	public void bonusSteal(){
			String resivedStr = bonusAccrueCard.getText();
			String[] cutStr = resivedStr.split (" ");
			String resultCutStr = cutStr[1];
			Log.info("***QA: Количество начисляемых бонусов в карточке товара: "+ resultCutStr);
			NavigationBase.bonusAccCard = Integer.parseInt(resultCutStr);
	}
}
