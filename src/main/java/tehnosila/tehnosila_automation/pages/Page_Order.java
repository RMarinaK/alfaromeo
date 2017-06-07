package tehnosila.tehnosila_automation.pages;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

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
	
	@FindBy(xpath="//ul[@class='checkout-total__item checkout-total__item_discount']/li[@class='checkout-total__value']")
	private WebElement getdiscountpsize;	// 
	
	@FindBy(xpath = "//span[@class = 'take-bonus']") //Поле с количеством начисляемых бонусов в оформлении заказа
	private WebElement bonusAccrueOffer;

	@FindBy(id = "set-card") //Кнопка "Ввести номер карты"
	private WebElement setCard;	
	
	@FindBy(id = "cardNumber") //Поле для номера привязываемой карты
	private WebElement cardNumber;
	
	@FindBy(id = "applyCard") //Кнопка Применить
	private WebElement applyCard;
	
	@FindBy(id = "just-all") //Кнопка Применить
	private WebElement justAll;
	
	@FindBy(xpath = "//span[@class = 'give-card']") //Номер привязанной карты
	private WebElement giveCard;

	
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
	
	@FindBy(id="OrderForm_orderAddress_city")
	private WebElement orderformorderaddresscity; // Поле "Населенный пункт"
	
	// Выбор метро
	public void setMetro() throws Exception {
		if (moscow.isDisplayed()) {
			try {
				metrostation.click(); 
				Log.info("жмаканье на выпадашку выбора метро");
				firstmetrostation.click();
				Log.info("жмаканье на первое по списку метро");
			}
			catch(Exception e) {      
				Log.info("Element Not Found");     
				ScreenShot.takeScreenShot();       
			}    
		}/* else {
		
				orderformorderaddresscity.click();
				orderformorderaddresscity.clear();
				orderformorderaddresscity.sendKeys("г. Санкт-Петербург");
			
		}*/
	}	
	
	// вводи названия улицы
	public void setOrderFormOrderAddressStreet(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformorderaddressstreet.click();
			orderformorderaddressstreet.clear();
			orderformorderaddressstreet.sendKeys(string);
		}
	}

	// ввод номера дома
	public void setOrderFormOrderAddressHouse(String string) {
		if(isNecessaryToChangeParam(string)){
			orderformorderaddresshouse.click();
			orderformorderaddresshouse.clear();
			orderformorderaddresshouse.sendKeys(string);
		}
	}
	
	// жмаканье первый пункт самовывоза
	public void clickFirstPoint() throws Exception {
	//	try {  	@FindBy(xpath="//div[@id='listPoints']/ul/li[@class='as-tab__shops-cell as-tab__shops-cell_date']/button")
		//private WebElement buttonselect; // Кнопка "Выбрать"
			firstpoint.click(); 
			Log.info("жмаканье на первый пункт самовывоза");
			if (driver.findElement(By.xpath("//div[@id='listPoints']/ul/li[@class='as-tab__shops-cell as-tab__shops-cell_date']/button")).isDisplayed()) {
				buttonselect.click();
				Log.info("жмаканье на Выбрать");
			}

			
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	
	
	// Наличными
	public void clickRCash(String paymentName) throws Exception {
	//	try {
			rcash.click();
			NavigationBase.prcash = paymentName;
			Log.info("жмаканье на Наличными");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/  
	}	
	
	// Банковской картой
	public void clickRCardOnDelivery(String paymentName) throws Exception {
	//	try {
			rcardondelivery.click(); 
			NavigationBase.prcardondelivery = paymentName;
			Log.info("жмаканье на Банковской картой");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/  
	}	
	
	// Рассрочка или кредит в магазине
	public void clickRCreditInStore(String paymentName) throws Exception {
	//	try {
			rcreditinstore.click(); 
			NavigationBase.prcardondelivery = paymentName;
			Log.info("жмаканье на Рассрочка или кредит в магазине");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/  
	}	
	
	
	// Онлайн Банковской картой
		public void assertROnlineCardOnDelivery(String paymentName){
			//try {
			if(ronlainecardondelivery.getAttribute("class").equals("radiobox active"))
				Log.info("radiobox " + paymentName +" active");
		    else 
		    	Log.info("radiobox " + paymentName +" unactive");

			/*	app.getNavigationHelper().waitVisible(ronlainecardondelivery,10);
				ronlainecardondelivery.click(); 
				NavigationBase.prcardondelivery = paymentName;
				Log.info("жмаканье на Онлайн Банковской картой");*/
			/*}
		    catch(Exception e) {      
		    	Log.info("Element Not Found");     
	            ScreenShot.takeScreenShot();       
	         }*/  
		}
	
	// Онлайн Банковской картой
	public void clickROnlineCardOnDelivery(String paymentName) throws Exception {
		//try {
			app.getNavigationHelper().waitVisible(ronlainecardondelivery,10);
			ronlainecardondelivery.click(); 
			NavigationBase.prcardondelivery = paymentName;
			Log.info("жмаканье на Онлайн Банковской картой");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/  
	}
	
	// По счету (для юр. лиц)
	public void clickRBank(String paymentName) throws Exception {
		//try {
			rbank.click();
			NavigationBase.prbank = paymentName;
			Log.info("жмаканье на По счету (для юр. лиц)");
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/  
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
	//	try {
			app.getNavigationHelper().waitVisible(buttonsubmitorder,10);
			buttonsubmitorder.click(); 
			Log.info("жмаканье на Завершить оформление");
			//commonmetods.refreshPage();
			//Waiting();
		//	Log.info("Номер заказа " + message.getText());
	//		WebElement webElement = driver.findElement(By.id("test_order-number"));
		//	webElement.getText();
		/*}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }*/    
	}	
/*	// вытягивание скидки из поля "Скидка:"
	public String getDiscountPSize() { 
		return driver.findElement(By.xpath("//ul[@class='checkout-total__item checkout-total__item_discount']/li[@class='checkout-total__value']")).getText();
	} закоментировано до првоерки!!! */
	
	// обрезание скидки из поля "Скидка:"
	public void DiscountSize(){
		Pattern pattern = Pattern.compile("\\d+");
		String stringdiscountpsize = getdiscountpsize.getText(); // мой пример строки
		Matcher matcher = pattern.matcher(stringdiscountpsize);
		int start = 0;
		StringBuilder builderpsale = new StringBuilder();
		while (matcher.find(start)) {
			String substringdiscountpsize = stringdiscountpsize.substring(matcher.start(), matcher.end());
			start = matcher.end();
			NavigationBase.psale = builderpsale.append(substringdiscountpsize).toString();
			Log.info("***QA: psale "+ NavigationBase.psale);
		}
	}

	// вытягивание цены товара
	public String getPrice(){ 
		return driver.findElement(By.xpath("//*[@id='totalCostRequested']")).getText();   //li[@id='cart-total-price']  //*[@id="checkout-total-wrapper"]/div/div/ul[2]/li[2]
	}
	
	public String getDefaultDuscount() {
		return driver.findElement(By.xpath("//*[@id='checkout-total-wrapper']/div/div/ul[2]/li[2]")).getText();
	}
	
	// расчёт скидки для проверки с отображаемой на сайте 
	public void getDiscount(int salesize) {
		String price = getPrice();
		String grouprice = price.replaceAll(" ", "");
		String onlyprice = grouprice.substring(0, grouprice.indexOf('Р'));
		float floatprice = Float.parseFloat(onlyprice);
		Log.info("***QA: Всего к оплате "+ floatprice);
		float floatpriceprc = (floatprice/100);
		Log.info("***QA: процент от всего к оплате "+ floatpriceprc);
		float discount = floatpriceprc*salesize;
		NavigationBase.pdiscountresult = (int)Math.ceil(discount); 
		Log.info("***QA: 5% скидон полученый от всего к оплате "+ NavigationBase.pdiscountresult);
		String defdisc = getDefaultDuscount();
		String groupdefdisc = defdisc.replaceAll(" ", "");
		String onlydefdisc = groupdefdisc.substring(0, groupdefdisc.indexOf('Р'));
		float floatdefdisc = Float.parseFloat(onlydefdisc);
		NavigationBase.fpdiscountresult = (int)Math.ceil(floatdefdisc);
		Log.info("***QA: скидон который уже есть у товара "+ NavigationBase.fpdiscountresult);
		int finskidon = NavigationBase.pdiscountresult + NavigationBase.fpdiscountresult;
		NavigationBase.finpdiscountresult = (int)Math.ceil(finskidon);
		Log.info("***QA: итоговый скидон "+ NavigationBase.finpdiscountresult);
	} 
	
	// сравнение скидки рассчитанной и взятой со страницы
	public void assertDiscount(int salesize) throws InterruptedException {
		Thread.sleep(2000); // esli chto udalit'
		app.getNavigationHelper().waitVisible(driver.findElement(By.xpath("//li[contains(text(),'Скидка:')]")), 10);
		DiscountSize();
		int psale = Integer.valueOf(NavigationBase.psale);
		try {
			Assert.assertEquals(psale, NavigationBase.finpdiscountresult); 
			Log.info("***QA: Скидон "+ NavigationBase.psale);
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
      //     ScreenShot.takeScreenShot();       
        }      
	}  
	
	// Поиск элемента <p class="description-red">Скидка 5 % </p>
	public void findDiscountSize() {
		driver.findElement(By.xpath("//p[contains(text(),'Скидка 5 %')]"));
		Log.info("***QA: Текст Скидка 5 %");
	}
	

//--------------------------------------------------------------------------------------------------
// Проверка количества начисляемых бонусов     @author EDanilova
	
	//Получение количества бонусов к начислению
	public void bonusSteal(int f) throws Exception{	
			String resivedStr = bonusAccrueOffer.getAttribute("innerHTML");
			if (f == 0){
				String[] cutStr = resivedStr.split (" ");
				String resultCutStr = cutStr[0];
				Log.info("***QA: Количество начисляемых бонусов ДО привязки карты: " + resultCutStr);
				NavigationBase.bonusAccOffer0 = Integer.parseInt(resultCutStr);
			}else {
				Log.info("***QA: Количество начисляемых бонусов ПОСЛЕ привязки карты: " + resivedStr);
				NavigationBase.bonusAccOffer1 = Integer.parseInt(resivedStr);
			}
	}	
	
	//Клик по "Ввести номер карты"
	public void clickButtonSetCard() throws Exception{
		try {
			setCard.click();
			Log.info("***QA: клик по Ввести номер карты");
		}
		catch(Exception e) {      
			Log.info("Element Not Found");     
			ScreenShot.takeScreenShot();       
		} 
	}
	
	//Заполнение поля номер бонусной карты
	public void setOrderFromOrderContactCard(String string) {
			cardNumber.click();
			cardNumber.clear();
			cardNumber.sendKeys(string);
	}
	
	//Клик по "Применить" после ввода номера бонусной карты
	public void clickButtonApplyCard() throws Exception{
		try {
			applyCard.click();
			Log.info("***QA: клик по Применить");
		}
		catch(Exception e) {      
			Log.info("Element Not Found");     
			ScreenShot.takeScreenShot();       
		} 
	}	
	
	//Ожидаем, пока обновится информация о привязанной крате и кол-ве бонусов
/*	public void waitForInfoText() {   
		//To wait for element visible
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("just-all")));
		Log.info("***QA: появилась информация о номере привязанной карты и начисялемых бонусах");    
	 }*/
	
	//Получение номера привязанной бонусной карты
	public void getGiveCardNumber() throws Exception{
		app.getNavigationHelper().waitVisible(justAll, 5);
		Log.info("***QA: появилась информация о номере привязанной карты и начисялемых бонусах");    
		String resivedStr =  giveCard.getText().trim();
		String[] cutStr = resivedStr.split ("-");
		String cardNum;
		if(cutStr[2].contains(" ")){
			String[] cutStr2 = cutStr[2].split (" ");
			cardNum = cutStr[0] + cutStr[1] + cutStr2[0] + cutStr2[1];
		} else{
			cardNum = cutStr[0] + cutStr[1] + cutStr[2];
		}
		Log.info("***QA: Номер привязанной карты: " + cardNum);
		NavigationBase.bonusCard = cardNum;
	}		
	
}
