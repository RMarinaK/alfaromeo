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
	
	@FindBy(xpath="//input[@id='r-CloudPayments']/../span")
	private WebElement ronlainecardondelivery; // Онлайн Банковской картой
	
	@FindBy(xpath="//input[@id='r-bank']/../span")
	private WebElement rbank; // По счету (для юр. лиц)
	
	@FindBy(xpath="//span[contains(text(),'Доставка в Москву, Московская область')]")
	private WebElement moscow; // Доставка в Москву, Московская область
	
	@FindBy(xpath="//div[@id='OrderForm_orderAddress_metro_station_code_chosen']/a/span")
	private WebElement metrostation; // Доставка в Москву, Московская область
	
	@FindBy(xpath="//div[@id='OrderForm_orderAddress_metro_station_code_chosen']/div/ul/li")
	private WebElement firstmetrostation; // Первое метро
	
	@FindBy(id="OrderForm_orderAddress_street")
	private WebElement orderformorderaddressstreet; // Поле "Улица"
	
	@FindBy(id="OrderForm_orderAddress_house")
	private WebElement orderformorderaddresshouse; // Поле "Дом"
	
	@FindBy(id="OrderForm_orderContact_companyInn")
	private WebElement orderformordercontactcompanyinn; // Поле "ИНН"
	
	@FindBy(id="OrderForm_orderContact_companyKpp")
	private WebElement orderformordercontactcompanykpp; // Поле "КПП"
	
	@FindBy(id="OrderForm_orderContact_namecompany")
	private WebElement orderformordercontactnamecompany; // Поле "Название компании"
	
	@FindBy(id="OrderForm_orderContact_companyAddress")
	private WebElement orderformordercontactcompanyaddress; // Поле "Юридический адрес"
	
	@FindBy(id="OrderForm_orderContact_companyAddressFact")
	private WebElement orderformordercontactcompanyaddressfact; // Поле "Фактический адрес"
	
	@FindBy(id="OrderForm_orderContact_companyAccount")
	private WebElement orderformordercontactcompanyaccount; // Поле "Раcчетный счет"
	
	@FindBy(id="OrderForm_orderContact_companyBik")
	private WebElement orderformordercontactcompanybik; // Поле "БИК"
	
	@FindBy(id="OrderForm_orderContact_companyAccountCorr")
	private WebElement orderformordercontactcompanyaccountcorr; // Поле "Корр. Счет"
	
	@FindBy(id="OrderForm_orderContact_companyBankName")
	private WebElement orderformordercontactcompanybankname; // Поле "Наименование банка"
	
	@FindBy(id="OrderForm_orderContact_companyCity")
	private WebElement orderformordercontactcompanycity; // Поле "Город"
	
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
	
	// Выбор метро
	public void setMetro() throws Exception {
	if (moscow.isDisplayed()) {
		try {
			metrostation.click(); 
			Log.info("жмаканье на выпадашку выбора меню");
			firstmetrostation.click();
			Log.info("жмаканье на первое по списку метро");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    }
	}	
	
	
	public void setOrderFormOrderAddressStreet(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformorderaddressstreet.click();
			orderformorderaddressstreet.clear();
			orderformorderaddressstreet.sendKeys(string);
		}
	}

	
	public void setOrderFormOrderAddressHouse(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformorderaddresshouse.click();
			orderformorderaddresshouse.clear();
			orderformorderaddresshouse.sendKeys(string);
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
	
	// Онлайн Банковской картой
	public void clickROnlineCardOnDelivery(String paymentName) {
		ronlainecardondelivery.click(); 
		NavigationBase.prcardondelivery = paymentName;
	}
	
	// По счету (для юр. лиц)
	public void clickRBank(String paymentName) {
		rbank.click();
		NavigationBase.prbank = paymentName;
	}	
	
	// Ввод ИНН
	public void setOrderFormOrderContactCompanyInn(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanyinn.click();
			orderformordercontactcompanyinn.clear();
			orderformordercontactcompanyinn.sendKeys(string);
		}
	}
	
	// Ввод КПП
	public void setOrderFormOrderContactCompanyKpp(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanykpp.click();
			orderformordercontactcompanykpp.clear();
			orderformordercontactcompanykpp.sendKeys(string);
		}
	}
	
	// Ввод Название компании
	public void setOrderFormOrderContactNameCompany(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactnamecompany.click();
			orderformordercontactnamecompany.clear();
			orderformordercontactnamecompany.sendKeys(string);
		}
	}
	
	// Ввод Юридический адрес
	public void setOrderFormOrderContactCompanyAddress(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanyaddress.click();
			orderformordercontactcompanyaddress.clear();
			orderformordercontactcompanyaddress.sendKeys(string);
		}
	}
	
	// Ввод Фактический адрес
	public void setOrderFormOrderContactCompanyAddressFact(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanyaddressfact.click();
			orderformordercontactcompanyaddressfact.clear();
			orderformordercontactcompanyaddressfact.sendKeys(string);
		}
	}
	
	// Ввод Рассчетный счет
	public void setOrderFormOrderContactCompanyAccount(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanyaccount.click();
			orderformordercontactcompanyaccount.clear();
			orderformordercontactcompanyaccount.sendKeys(string);
		}
	}
	
	// Ввод БИК
	public void setOrderFormOrderContactCompanyBik(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanybik.click();
			orderformordercontactcompanybik.clear();
			orderformordercontactcompanybik.sendKeys(string);
		}
	}
	
	// Ввод Корр. счет
	public void setOrderFormOrderContactCompanyAccountCorr(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanyaccountcorr.click();
			orderformordercontactcompanyaccountcorr.clear();
			orderformordercontactcompanyaccountcorr.sendKeys(string);
		}
	}
	
	// Ввод Наименование банка
	public void setOrderFormOrderContactCompanyBankName(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanybankname.click();
			orderformordercontactcompanybankname.clear();
			orderformordercontactcompanybankname.sendKeys(string);
		}
	}
	
	// Ввод Город
	public void setOrderFormOrderContactCompanyCity(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformordercontactcompanycity.click();
			orderformordercontactcompanycity.clear();
			orderformordercontactcompanycity.sendKeys(string);
		}
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
