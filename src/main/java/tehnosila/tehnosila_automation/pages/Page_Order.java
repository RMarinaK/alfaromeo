package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;

/**
 * @author MRasstrigina
 *
 */

public class Page_Order extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Order.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/order";
	

	
	@FindBy(id="OrderForm_orderContact_fio")
	private WebElement orderfromordercontactfio; // Поле "Ваше имя"
	
	@FindBy(id="OrderForm_orderContact_phone")
	private WebElement orderfromordercontactphone; // Поле "Телефон"
	
	@FindBy(id="OrderForm_orderContact_email")
	private WebElement orderfromordercontactemail; // Поле "Электронная почта"
		
//	@FindBy(xpath="//div[@id='self-delivery-list']/div/div/span")
	@FindBy(xpath="//div[@id='listPoints']/ul/li[@class='as-tab__shops-cell as-tab__shops-cell_date']")
	private WebElement radiobuttondelivery; // Первый пункт выдачи
	
	@FindBy(xpath="//div[@id='listPoints']/ul/li[@class='as-tab__shops-cell as-tab__shops-cell_date']/button[contains(text(),'Выбрать')]")
	private WebElement buttonenter; // Кнопка Выбрать
	
	//@FindBy(xpath="//button[@class='button yellow pressable submitOrder']")
	@FindBy(xpath="//div[@id='submit-order']/button")
	private WebElement buttonsubmitorder; // Кнопка "Завершить оформление"
	
	@FindBy(xpath="//div[@id='listPoints']/ul")
	private WebElement firstpoint; // Первый пункт самовывоза
	
	@FindBy(xpath="//div[@id='listPoints']/ul/li[@class='as-tab__shops-cell as-tab__shops-cell_date']/button")
	private WebElement buttonselect; // Кнопка "Выбрать"
	
	@FindBy(xpath="//input[@id='r-cash']/../span")
	private WebElement rcash; // Наличными
	
	@FindBy(xpath="//input[@id='r-cardOnDelivery']/../span")
	private WebElement rcardondelivery; // Банковской картой
	
	@FindBy(xpath="//input[@id='r-CreditInStore']/../span")
	private WebElement rcreditinstore; //  Рассрочка или кредит в магазине
	
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
	
	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage(URL_MATCH);
	}
	
	public void setOrderFromOrderContactFio(String string) {
		if(isNecessaryToChangeParam(string)){
			orderfromordercontactfio.click();
			orderfromordercontactfio.clear();
			orderfromordercontactfio.sendKeys(string);
		}
	}
	
	public void setOrderFromOrderContactPhone(String string) {
		if(isNecessaryToChangeParam(string)){
			orderfromordercontactphone.click();
			orderfromordercontactphone.clear();
			orderfromordercontactphone.sendKeys(string);
		}
	}
	
	public void setOrderFromOrderContactEmail(String string) {
		if(isNecessaryToChangeParam(string)){
			orderfromordercontactemail.click();
			orderfromordercontactemail.clear();
			orderfromordercontactemail.sendKeys(string);
		}
	}
	
	// жмаканье первый пункт самовывоза
	public void clickFirstPoint() throws Exception {
		try {
			firstpoint.click(); 
			Log.info("жмаканье на первый пункт самовывоза");
			buttonselect.click();
			Log.info("жмаканье на Выбрать");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
	
	// Наличными
	public void clickRCash(String paymentName) {
		rcash.click();
		NavigationBase.prcash = paymentName;
	}	
	
	// Банковской картой
	public void clickRCardOnDelivery(String paymentName) {
		rcardondelivery.click(); 
		NavigationBase.prcardondelivery = paymentName;
	}	
	
	// Рассрочка или кредит в магазине
	public void clickRCreditInStore(String paymentName) {
		rcreditinstore.click(); 
		NavigationBase.prcardondelivery = paymentName;
	}	
	
	/*public String getNumber(){
		return driver.findElement(By.xpath("//div[@id='test_order-number']")).getText();
	}*/
	
/*	@FindBy(xpath="//div[contains(text(),'СПАСИБО! ВАШ ЗАКАЗ ПРИНЯТ!')]")
	private WebElement message; // Сообщение Спасибо! Ваш заказ принят!*/
	
	// вытягивание Спасибо! Ваш заказ принят!
/*	public String getMessage(){
		return message.getText();
	}*/
	
//	private String getOrders = super.getBaseURL()+"#/sys/getOrders?gID="+getNumber();	
	// ожидание пока отработает прелоэдер

	// жмаканье на "Завершить оформление"
	public void clickButtonSubmitOrder() throws Exception {
		try {
			buttonsubmitorder.click(); 
			Log.info("жмаканье на Завершить оформление");
			//commonmetods.refreshPage();
			//Waiting();
		//	Log.info("Номер заказа " + message.getText());
	//		WebElement webElement = driver.findElement(By.id("test_order-number"));
		//	webElement.getText();
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
   
       

            
	
}
