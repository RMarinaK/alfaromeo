package tehnosila.tehnosila_automation.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ApplicationManager;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	@FindBy(xpath = "//div[@id='main-categories']/div/ul/li/a[contains(text(),'Телевизоры, аудио, видео')]")//
	private WebElement tvaudiovideo; // Телевизоры, аудио, видео

	@FindBy(xpath = "//div[@id='category-172']/ul/li/a[contains(text(),'   Телевизоры ')]")//
	private WebElement tv; // Телевизоры

	//@FindBy(xpath = "//div[@id='category-364']/ul/li/a[contains(text(),'LED телевизоры')]")//
//	@FindBy(xpath = "//li[@data-submenu-id='category-198']/a[contains(text(),'LED телевизоры')]")
	@FindBy(xpath = "//a[@class='item first-in-row']")
	private WebElement ledtv; // LED телевизоры

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

	@FindBy(xpath = "//a[contains(text(),'Бонусная программа')]")
	private WebElement bonusProgramm; // Бонусная программа

	@FindBy(xpath = "//a[contains(text(),'Настройка и установка')]")
	private WebElement setupAndInstallation; // Настройка и установка

	@FindBy(xpath = "//a[contains(text(),'Подарочные карты')]")
	private WebElement giftCards; // Подарочные карты

	@FindBy(xpath = "//a[contains(text(),'Программа Сервис+')]")
	private WebElement servicePlus; // Сервис плюс

	@FindBy(xpath = "//a[contains(text(),'Страхование техники')]")
	private WebElement insuranceTechniques; // Страхование техники

	@FindBy(xpath = "//a[contains(text(),'Программа Технотренд')]")
	private WebElement tehnotrendProgram; // Программа Технотренд

	@FindBy(xpath = "//a[contains(text(),'Гарантийное обслуживание')]")
	private WebElement warrantyService; // Гарантийное обслуживание

	@FindBy(xpath = "//a[contains(text(),'Адреса сервисных центров')]")
	private WebElement serviceCenterLocations; // Адреса сервисных центров
	
	@FindBy(xpath = "//div[@id='authorization']/span")
	public WebElement authorization; // Кабинет
	
	@FindBy(xpath = "//a[contains(text(),'Войти на сайт')]")
	private WebElement login; // Адреса сервисных центров

	@Override
	public void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// жмаканье на кнопку "Телевизоры LED"
	public void clickTV() throws Exception {
		try {
			ledtv.click();
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
			
			Actions builder = new Actions(driver);
			Action dragAndDrop = builder
					.moveToElement(allgoods)
					.moveToElement(tvaudiovideo)
					.click(tvaudiovideo)
					.release(tvaudiovideo)
					.build();
			dragAndDrop.perform();
			Log.info("tvaudiovideo");
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// ПОДВАЛ

	// жмаканье на ссылку "О компании Техносила"
	public void clickAboutTC() throws Exception {
		try {
			aboutTC.click();
			Log.info("О компании Техносила");
			ScreenShot.takeScreenShot();
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}

	// жмаканье на ссылку "Новости компании"
	public void clickNewsTC() throws Exception {
		try {
			newsTC.click();
			Log.info("Новости компании");
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();

		}
	}

	// УСЛУГИ И СЕРВИС

	// клик по ссылке "Покупка в кредит"
	public void clickPurchaseOnCredit() throws Exception {
		try {
			purchaseOnCredit.click();
			Log.info("Покупка в кредит");
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
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
			ScreenShot.takeScreenShot();
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}
	
	//клик по Войти на сайт
	public void clickLogIn() throws Exception {
		try {
			login.click();
			Log.info("Войти на сайт");
			ScreenShot.takeScreenShot();
		} catch (Exception e) {
			Log.info("Element Not Found");
			ScreenShot.takeScreenShot();
		}
	}
}