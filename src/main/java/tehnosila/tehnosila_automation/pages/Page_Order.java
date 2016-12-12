package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	// Выбор первого пункта вывоза
	public void clickRadioButtonDelivery() {
		radiobuttondelivery.click(); 
		buttonenter.click();
	}	
	
	// жмаканье на "Завершить оформление"
	public void clickButtonSubmitOrder() throws Exception {
		try {
			buttonsubmitorder.click(); 
			Log.info("жмаканье на Завершить оформление");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	 	
}
