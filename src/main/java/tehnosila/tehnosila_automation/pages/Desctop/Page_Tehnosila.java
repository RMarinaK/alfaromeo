package tehnosila.tehnosila_automation.pages.Desctop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.PagesBase;

//import java.util.ArrayList;
//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author MRasstrigina
 *
 */

public class Page_Tehnosila extends PagesBase {
	private static Logger Log = LoggerFactory.getLogger(Page_Tehnosila.class);
	// DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();

	@FindBy(id = "all-goods")
	public WebElement allgoods; // Каталог товаров
	
	@FindBy(id = "footer-links")
	public WebElement footerlinks; // Каталог товаров
	
	@FindBy(xpath = "//div[@id='main-categories']/div/ul/li/a[contains(text(),'Телевизоры, аудио, видео')]")//
	private WebElement tvaudiovideo; // Телевизоры, аудио, видео

	@FindBy(xpath = "//div[@id='category-172']/ul/li/a[contains(text(),'   Телевизоры ')]")//
	private WebElement tv; // Телевизоры

	//@FindBy(xpath = "//div[@id='category-364']/ul/li/a[contains(text(),'LED телевизоры')]")//
//	@FindBy(xpath = "//li[@data-submenu-id='category-198']/a[contains(text(),'LED телевизоры')]")
	@FindBy(xpath = "//a[@class='item first-in-row']")
	private WebElement ledtv; // LED телевизоры
	
		@FindBy(xpath = "//div[@class='list']/a")
	private WebElement firstitem; // первая категория

	@FindBy(xpath = "//a[contains(text(),'О компании Техносила')]")
	private WebElement aboutTC; // О компании Техносила

	@FindBy(xpath = "//a[contains(text(),'Новости компании')]")
	private WebElement newsTC; // Новости компании

	@FindBy(xpath = "//a[contains(text(),'Вакансии')]")
	private WebElement career; // Вакансии

	@FindBy(xpath = "//a[contains(text(),'Пресс-релизы')]")
	private WebElement press; // Пресс-релизы

	@FindBy(xpath = "//a[contains(text(),'Контактная информация')]")
	private WebElement contacts; // Контактная информация

	@FindBy(xpath = "//a[contains(text(),'Адреса магазинов')]")
	private WebElement stores; // Адреса магазинов

	@FindBy(xpath = "//a[contains(text(),'Арендодателям')]")
	private WebElement lease; // Арендодателям

	@FindBy(xpath = "//a[contains(text(),'Юридическая информация')]")
	private WebElement legal; // Юридическая информация

	@FindBy(xpath = "//a[contains(text(),'Как сделать заказ на сайте')]")
	private WebElement howtobuy; // Как сделать заказ на сайте

	@FindBy(xpath = "//a[contains(text(),'Оплата')]")
	private WebElement payment; // Оплата

	@FindBy(xpath = "//a[contains(text(),'Доставка')]")
	private WebElement delivery; // Доставка

	@FindBy(xpath = "//a[contains(text(),'Получение в магазине')]")
	private WebElement pickup; // Получение в магазине

	@FindBy(xpath = "//a[contains(text(),'Возврат и обмен')]")
	private WebElement exchange; // Возврат и обмен

	@FindBy(xpath = "//a[contains(text(),'Правила работы')]")
	private WebElement guide; // Правила работы

	@FindBy(xpath = "//a[contains(text(),'Обратная связь')]")
	private WebElement feedback; // Обратная связь

	@FindBy(xpath = "//a[contains(text(),'Покупка в кредит')]")
	private WebElement purchaseOnCredit; // Покупка в кредит

	@FindBy(xpath = "//li[@class='footer-column-item']/a[contains(text(),'Бонусная программа')]")
	private WebElement bonusProgramm; // Бонусная программа

	@FindBy(xpath = "//a[contains(text(),'Настройка и установка')]")
	private WebElement setupAndInstallation; // Настройка и установка

	@FindBy(xpath = "//li[@class='footer-column-item']/a[contains(text(),'Подарочные карты')]")
	private WebElement giftCards; // Подарочные карты

	@FindBy(xpath = "//a[contains(text(),'Программа Сервис+')]")
	private WebElement servicePlus; // Сервис плюс

	@FindBy(xpath = "//a[contains(text(),'Страхование техники')]")
	private WebElement insuranceTechniques; // Страхование техники

	@FindBy(xpath = "//a[contains(text(),'Программа Технотренд')]")
	private WebElement tehnotrendProgram; // Программа Технотренд
	
	@FindBy(xpath = "//a[contains(text(),'Онлайн и рядом 24/7')]")
	private WebElement onlineAndNear; // Онлайн и рядом 24/7
	
	@FindBy(xpath = "//a[contains(text(),'Оплата кредита')]")
	private WebElement creditPayment; // Оплата кредита

	@FindBy(xpath = "//a[contains(text(),'Гарантийное обслуживание')]")
	private WebElement warrantyService; // Гарантийное обслуживание

	@FindBy(xpath = "//a[contains(text(),'Адреса сервисных центров')]")
	private WebElement serviceCenterLocations; // Адреса сервисных центров
	
	@FindBy(xpath = "//div[@id='authorization']/span")
	public WebElement authorization; // Кабинет
	
	@FindBy(xpath = "//a[contains(text(),'Войти на сайт')]")
	private WebElement login; // Адреса сервисных центров

	@FindBy(id="search-field")
	public WebElement searchfield; // Строка поиска
	
	@FindBy(xpath="//div[@class='item-info']")
	private WebElement openproduct; //  Товар
	
	@FindBy(xpath = "//a[contains(text(),'Акции')]")
	private WebElement actions; //  Акции
	
	@FindBy(xpath="//a[@class = 'reject button gray-flat pressable']")
	public WebElement cityPopap; // Попап "Выбрать другой"   @author EDanilova	
	
	@FindBy(id="current-region")
	public WebElement currentRegion; // Текущий город   @author EDanilova	

	@Override
	public void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	protected boolean isNecessaryToChangeParam(String param) {
		if(param.equals(" ")||param.equals("")){
			return false;
		}else{
			return true;
		}		
	}
	
	// ожидание пока страница прогрузится и проверка соответствия номер заказа
	public void getPage() {
		driver.navigate().to(getBaseURL()+"item?code=" + NavigationBase.psolrarticle); 
		Log.info("***QA: "+getBaseURL());
	}
	
	// ожидание пока страница прогрузится и проверка соответствия номер заказа
	public void getPageCatalog() {
		driver.navigate().to(getBaseURL()+"catalog");
		Log.info("***QA: "+ driver.getCurrentUrl());
	}
	
	// ожидание пока главная страница прогрузится 
	public void getPageBase() {
		driver.navigate().to(getBaseURL());
		Log.info("***QA: "+getBaseURL());
	}
	
	// ожидание пока страница прогрузится 
	public void getPageActionCatalog(){
		String currenturl = driver.getCurrentUrl();
		driver.navigate().to(currenturl + "catalog");  // / перед каталог
		Log.info("***QA: "+getBaseURL());
	}
		
	public void setSearchField() {
		if(isNecessaryToChangeParam(NavigationBase.psolrarticle)){
			searchfield.click();
			searchfield.clear();
			searchfield.sendKeys(NavigationBase.psolrarticle);
		}
		searchfield.sendKeys(Keys.ENTER);
	}
	
	public void clickProduct() throws Exception {
		try {
			app.getNavigationHelper().waitVisible(openproduct,10);
			openproduct.click(); 
			Log.info("Зашли в товар");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         } 
	}	
	
	// жмаканье на первую категорию
	public void clickTV() throws Exception {
		try {
			firstitem.click();
			Log.info("Телевизоры");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на кнопку "tvaudiovideo"
	public void clickTVVA() throws Exception {
		try {
			/*Action dragAndDrop = builder.moveToElement(allgoods).moveToElement(tvaudiovideo).moveToElement(tv)
					.moveToElement(ledtv).click(ledtv).release(ledtv).build();*/
			
		/*	Actions builder = new Actions(driver);
			Action dragAndDrop = builder
					.moveToElement(allgoods)
					.moveToElement(tvaudiovideo)
					.click(tvaudiovideo)
					.release(tvaudiovideo)
					.build();
			dragAndDrop.perform();*/
			tv.click();
			Log.info("tvaudiovideo");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// ПОДВАЛ
	
	//Проход по всем ссылкам в подвале
	public void AllFooterLinks() throws Exception {
		
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		
		int counter = driver.findElement(By.xpath("//div[@class='content-area cf']")).findElements(By.tagName("ul")).size();
		int [] linksArr = new int[counter];
	    
		for(int i = 0, j = 1; i < linksArr.length; i++, j++){
			int items = driver.findElement(By.xpath("//ul[@class='footer-column footer-column_"+j+"']")).findElements(By.tagName("li")).size();
			linksArr[i] = items-1;
	    }
		
		for (int i = 0, f = 1; i < linksArr.length; i++, f++){
			for (int k = 2; k < linksArr[i]+2; k++) {
				WebElement link = driver.findElement(By.xpath("//ul[@class='footer-column footer-column_"+f+"']/li["+k+"]/a"));
				Log.info("ссылка " + link.getAttribute("href"));
				String linkTitle = link.getAttribute("innerHTML");
				link.click();
				Log.info("title " + driver.getTitle());
				commonmetods.assertFooterPages(linkTitle);
				driver.navigate().back();
			}
		}
	}
	
	

	// жмаканье на ссылку "О компании Техносила"
	public void clickAboutTC() throws Exception {
		try {
			//driver.findElement(By.linkText("О компании Техносила")).click();
			aboutTC.click();
			Log.info("О компании Техносила");
		} catch (Exception e) {
			Log.info("Element Not Found");
	//		ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Новости компании"
	public void clickNewsTC() throws Exception {
		try {
		//	driver.findElement(By.linkText("Новости компании")).click();
			newsTC.click();
			Log.info("Новости компании");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Вакансии"
	public void clickCareer() throws Exception {
		try {
			career.click();
			Log.info("Вакансии");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Пресс-релизы"
	public void clickPress() throws Exception {
		try {
			press.click();
			Log.info("Пресс-релизы");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Контактная информация"
	public void clickContacts() throws Exception {
		try {
			contacts.click();
			Log.info("Контактная информация");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Адреса магазинов"
	public void clickStores() throws Exception {
		try {
			stores.click();
			Log.info("Адреса магазинов");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Арендодателям"
	public void clickLease() throws Exception {
		try {
			lease.click();
			Log.info("Арендодателям");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Юридическая информация"
	public void clickLegal() throws Exception {
		try {
			legal.click();
			Log.info("Юридическая информация");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Как сделать заказ на сайте"
	public void clickHowToBuy() throws Exception {
		try {
			howtobuy.click();
			Log.info("Как сделать заказ на сайте");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Оплата"
	public void clickPayment() throws Exception {
		try {
			payment.click();
			Log.info("Оплата");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Доставка"
	public void clickDelivery() throws Exception {
		try {
			delivery.click();
			Log.info("Доставка");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Получение в магазине"
	public void clickPickup() throws Exception {
		try {
			pickup.click();
			Log.info("Получение в магазине");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Возврат и обмен"
	public void clickExchange() throws Exception {
		try {
			exchange.click();
			Log.info("Возврат и обмен");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Правила работы"
	public void clickGuide() throws Exception {
		try {
			guide.click();
			Log.info("Правила работы");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}

	}

	// жмаканье на ссылку "Обратная связь"
	public void clickFeedback() throws Exception {
		try {
			feedback.click();
			Log.info("Обратная связь");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();

		}
	}

	// УСЛУГИ И СЕРВИС
	
	// клик по ссылке "Оплата кредита"
	public void clickCreditPayment() throws Exception {
		try {
			creditPayment.click();
			Log.info("Оплата кредита");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Онлайн и рядом 24/7"
	public void clickOnlineAndNear() throws Exception {
		try {
			onlineAndNear.click();
			Log.info("Онлайн и рядом 24/7");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}	
	
	// клик по ссылке "Покупка в кредит"
	public void clickPurchaseOnCredit() throws Exception {
		try {
			purchaseOnCredit.click();
			Log.info("Покупка в кредит");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Бонусная программа"
	public void clickBonusProgramm() throws Exception {
		try {
			bonusProgramm.click();
			Log.info("Бонусная программа");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Настройка и установка"
	public void clickSetupAndInstallation() throws Exception {
		try {
			setupAndInstallation.click();
			Log.info("Настройка и установка");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Подарочные карты"
	public void clickGiftCards() throws Exception {
		try {
			giftCards.click();
			Log.info("Подарочные карты");	
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Сервис плюс"
	public void clickServicePlus() throws Exception {
		try {
			servicePlus.click();
			Log.info("Сервис плюс");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Страхование техники"
	public void clickInsuranceTechniques() throws Exception {
		try {
			insuranceTechniques.click();
			Log.info("Страхование техники");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Программа Технотренд"
	public void clickTehnotrendProgram() throws Exception {
		try {
			tehnotrendProgram.click();
			Log.info("Программа Технотренд");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Гарантийное обслуживание"
	public void clickWarrantyService() throws Exception {
		try {
			warrantyService.click();
			Log.info("Гарантийное обслуживание");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// клик по ссылке "Адреса сервисных центров"
	public void clickServiceCenterLocations() throws Exception {
		try {
			serviceCenterLocations.click();
			Log.info("Адреса сервисных центров");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------------------
	// Авторизация
	
	//клик по Кабинет
	public void clickMenuCabinet() throws Exception {
		try {
			authorization.click();
			Log.info("Меню Кабинет");
		} catch (Exception e) {
			Log.info("Element Not Found");
		//	ScreenShot.takeScreenShot();
		}
	}
	
	//клик по Войти на сайт
	public void clickLogIn() throws Exception {
		try {
			login.click();
			Log.info("Войти на сайт");
		} catch (Exception e) {
			Log.info("Element Not Found");
		//	ScreenShot.takeScreenShot();
		}
	}
	
	
	//чистка кук
	public void delCookies(){
		driver.manage().deleteAllCookies(); 
	}
		
	//переход в Акции
	public void clickActions(){
		actions.click(); 		
	}	

	// ----------------------------------------------------------------------------------------------------------------------------
	//Проверка смены города	@author EDanilova
	
	//клик по региону для вызова попапа смены города
		public void clickCityPopup() throws Exception {
			try {
				app.getNavigationHelper().refreshPage();
				currentRegion.click();
			} catch (Exception e) {
				Log.info("Element Not Found");
			}
		}	
	// ----------------------------------------------------------------------------------------------------------------------------
	
	public void clickHamburgers(String value){
		driver.findElement(By.xpath("//div[@id='content-wrapper']/div/div/div/a/div/div/div[contains(text(),'"+value+"')]")).click();

	}	
	
	public void clickSubSubSubcategory(String value) throws Exception{
	//	driver.findElement(By.xpath("//form[@id='filterForm']/div/div/div/div/h3/a[contains(text(),'"+value+"')]")).click();
			if (app.getNavigationHelper().isElementPresent(By.xpath("//div[@class='filters children-categoryes']/div/div/div/h3/a[contains(text(),'"+value+"')]")) == true) {
				driver.findElement(By.xpath("//div[@class='filters children-categoryes']/div/div/div/h3/a[contains(text(),'"+value+"')]")).click();	
			}
			else {
				Log.info("Not found " + value);	
			}
	}
	
	public void navigateback(){
		driver.navigate().back();
	//	driver.navigate().refresh();
	}	

}