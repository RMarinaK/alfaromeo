package tehnosila.tehnosila_automation.pages.Mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.PagesBase;


/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Order extends PagesBase{
//	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Order.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/order";
	
	@FindBy(id="OrderForm_orderContact_fio")
	private WebElement orderfromordercontactfio; // Поле "Ваше имя"
	
	@FindBy(id="OrderForm_orderContact_phone")
	private WebElement orderfromordercontactphone; // Поле "Телефон"
	
	@FindBy(id="OrderForm_orderContact_email")
	private WebElement orderfromordercontactemail; // Поле "Электронная почта"
		
	@FindBy(xpath="//*[@id='submitOrderForm']/div[2]/button")
	private WebElement radiobuttondelivery; // Пункты вывоза
	
	@FindBy(xpath="//div[1]/div[2]/div[6]/div[1]/a[@class='point-chooser-order']")
	private WebElement clickfirstdeliverybutton; // Первый пункт самовывоза
	
	@FindBy(xpath="//div[2]/label[1][@id='firstPaymentLabel']")
	private WebElement cardondelivery; // Оплата банковской картой
	
	@FindBy(xpath="//div[3]/label[1][@id='firstPaymentLabel']")
	private WebElement creditinstore; // Кредит в магазине
	
	@FindBy(xpath="//div/div[3]/div[2]/label[1][@class='input-title']")
	private WebElement rbank; // По счету (для юр. лиц)
	
	@FindBy(xpath="//button[@class='button-control buy-button buy-button--mod submitOrder']")
	private WebElement buttonsubmitorder; // Кнопка "Завершить оформление"
	
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
	
	@FindBy(xpath="//*[@id='OrderForm_orderAddress_metro_station_code']")
	private WebElement metrostation; // Ближайшая станция метро
	
	@FindBy(xpath="//*[@id='OrderForm_orderAddress_metro_station_code']/option[2]")
	private WebElement firstmetrostation; // Первое метро
	
	@FindBy(xpath="//*[@id='OrderForm_orderAddress_street']")
	private WebElement orderformorderaddressstreet; // Поле "Улица"
	
	@FindBy(xpath="//*[@id='OrderForm_orderAddress_house']")
	private WebElement orderformorderaddresshouse; // Поле "Дом"

	protected boolean isNecessaryToChangeParam(String param){
		if(param.equals(" ")||param.equals("")){
			return false;
		}else{
			return true;
		}		
	}
	
	@Override
	protected
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
	
	// Пункты вывоза
	public void clickRadioButtonDelivery() {
		if(app.getNavigationHelper().isElementPresent(By.xpath("//*[@id='choose-pvz']")) == false) {
			radiobuttondelivery.click();
			Log.info("жмаканье на пункты самовывоза");
		} else {
			Log.info("Пункт самовывоза уже выбран");
		}
	}
	
	// жмаканье на "Завершить оформление"
	public void clickButtonSubmitOrder() {
		buttonsubmitorder.click(); 
	}
	
	// Выбор первого пункта самовывоза
	public void clickFirstDeliveryButton() {
		if(app.getNavigationHelper().isElementPresent(By.xpath("//div[1]/div[2]/div[6]/div[1]/a[@class='point-chooser-order']")) == true) {
				clickfirstdeliverybutton.click();
				Log.info("жмаканье на пункт самовывоза");
		} else {
			Log.info("Пункт самовывоза уже выбран");
		}
	}
	// Оплата банковской картой
	public void clickCardOnDelivery(String paymentName) throws Exception {
		cardondelivery.click(); 
		NavigationBase.prcardondelivery = paymentName;
		Log.info("жмаканье на Банковской картой"); 
	}
	
	// Кредит в магазине
	public void clickCreditInStore(String paymentName) throws Exception {
		creditinstore.click();
		NavigationBase.prcardondelivery = paymentName;
		Log.info("жмаканье на Кредит в магазине"); 
	}
	
	// По счету (для юр. лиц)
	public void clickRBank(String paymentName) throws Exception {
			rbank.click();
			NavigationBase.prbank = paymentName;
			Log.info("жмаканье на По счету (для юр. лиц)");
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
	
	// Выбор метро
	public void setMetro() {
			metrostation.click(); 
			Log.info("жмаканье на выпадашку выбора меню");
			firstmetrostation.click();
			Log.info("жмаканье на первое по списку метро");     
	}
	
	// Заполнить поле Улица
	public void setOrderFormOrderAddressStreet(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformorderaddressstreet.click();
			orderformorderaddressstreet.clear();
			orderformorderaddressstreet.sendKeys(string);
		}
	}
	
	// Заполнить поле Дом/корпус
	public void setOrderFormOrderAddressHouse(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformorderaddresshouse.click();
			orderformorderaddresshouse.clear();
			orderformorderaddresshouse.sendKeys(string);
		}
	}
}
