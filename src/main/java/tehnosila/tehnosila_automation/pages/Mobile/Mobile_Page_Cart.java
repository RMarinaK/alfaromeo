package tehnosila.tehnosila_automation.pages.Mobile;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.PagesBase;


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
	private WebElement buttonapply; // Кнопка "Применить"
	
	@FindBy(xpath="//*[@class='new-price']")
	private WebElement newitemprice; // Новая цена первого товара
	
	@FindBy(xpath="//*[@class='totals-price']")
	private WebElement itemprice; // Цена первого товара
	
	@FindBy(xpath="//*[@id='overallPrice']")
	private WebElement carttotalprice; // Сумма всей корзины
	
	@FindBy(xpath="//*[@class='form-group check-group'][1]/label")
	private WebElement servicecheckbox; // Чекбокс услуги
	
	@FindBy(xpath="//div[1]/label[1]/span[1][@class='product__add-price']")
	private WebElement servicepriceel; // Цена выбраной услуги (выбирается первая услуга и цена первой услуги)
	
	@FindBy(xpath="//div[1]/h4/a[@class='collapsed']")
	private WebElement accessories; // Кнопка "Подобрать аксессуары"
	
	@FindBy(xpath="//li[1]/div/div/div[@class='card-name']")
	private WebElement firstaccessories; // Клик по первому аксессуару
	
	@FindBy(xpath="//*[@class='new-price']")
	private WebElement newseconditemprice; // Новая цена второго товара
	
	@FindBy(xpath="//div[2]/div[2]/div/div/div[@class='totals-price']")
	private WebElement seconditemprice; // Цена второго товара твоара
	
	@FindBy(xpath="//div[1]/div/div/div/input[@class='form-control plain-input cart-item__col']")
	private WebElement quantityitem; // Количество товара
	
	@FindBy(xpath="//div[1]/div/div/div/a[@class='cart-item__plus']")
	private WebElement buttonplus; // Кнопка плюс, увеличение кол-ва товара
	
	@FindBy(xpath="//div[1]/div/div/div/a[@class='cart-item__minus']")
	private WebElement buttonminus; // Кнопка минус, уменьшение кол-ва товара
	
	@FindBy(xpath="//div[1]/div/div/a[@class='cart-item_delete']")
	private WebElement buttondelete; // Кнопка удалить, удаляет товар из корзины
	
	@FindBy(xpath="//*[contains(text(),'Ваша корзина пуста')]")
	private WebElement emptycart; // Блок с текстом "Ваша корзина пуста"
	
	private static String iprice;
	private static String siprice;
	private static String ctprice;
	private static int serviceprice;
	private static int serviceplusitemprice;
	
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
	
	// Метод получение цены первого товара
	public void itemPrice() {
		if (app.getNavigationHelper().waitPresense(By.xpath("//*[@class='new-price']"), 3)) {
			newGetItemPrice();
		} else {
			getItemPrice();
		}
	}
	
	// Поулчение новой цены первого товара 
	public void newGetItemPrice() {
		if (app.getNavigationHelper().waitVisible(newitemprice,5)); {
			Pattern pattern = Pattern.compile("\\d+");
			String ipriceel = newitemprice.getText();
			Matcher matcher = pattern.matcher(ipriceel);
			int start = 0;
			StringBuilder builderitemprice = new StringBuilder();
			while (matcher.find(start)) {
				String substringiprice = ipriceel.substring(matcher.start(), matcher.end());
				start = matcher.end();
				iprice = builderitemprice.append(substringiprice).toString();
			}
			Log.info("***QA: Новая цена первого товара "+ iprice);
		}
	}
	
	// Поулчение цены первого товара
	public void getItemPrice() {
		if (app.getNavigationHelper().waitVisible(itemprice,5)); {
			Pattern pattern = Pattern.compile("\\d+");
			String viprice = itemprice.getText();
			Matcher matcher = pattern.matcher(viprice);
			int start = 0;
			StringBuilder builderitemprice = new StringBuilder();
			while (matcher.find(start)) {
				String substringiprice = viprice.substring(matcher.start(), matcher.end());
				start = matcher.end();
				iprice = builderitemprice.append(substringiprice).toString();
			}
			Log.info("***QA: Цена первого товара "+ iprice);
		}
	}
	
	// Стоимость корзины
	public void getCartPrice() {
		Pattern patterns = Pattern.compile("\\d+");
		String ctpriceel = carttotalprice.getText();
		Matcher matcher = patterns.matcher(ctpriceel);
		int start = 0;
		StringBuilder buildersitemprice = new StringBuilder();
		while (matcher.find(start)) {
			String substringctprice = ctpriceel.substring(matcher.start(), matcher.end());
			start = matcher.end();
			ctprice = buildersitemprice.append(substringctprice).toString();
		}
		Log.info("***QA: Стоимость корзины "+ ctprice);
	}
	
	// Жмаканье на чекбокс услуги
	public void clickServiceCheckBox() {
		try{
			servicecheckbox.click();
			Log.info("жмаканье на чекбокс услуги");
		} catch(Exception e) {
			Log.info("не жмакнулся чекбокс услуги");
		}
	}
	
	// Вытягивание цены услуги
	public void getServicePrice() {
		Pattern pattern = Pattern.compile("\\d+");
		String serprice = servicepriceel.getText();
		serprice = serprice.replaceAll(" ", "");
		Matcher matcher = pattern.matcher(serprice);
		int start = 0;
		while (matcher.find(start)) {
		       String value = serprice.substring(matcher.start(), matcher.end());
		       int result = Integer.parseInt(value);
		       serviceprice = result;
		       start = matcher.end();
		}
		Log.info("Цена услуги " + serviceprice);
	}
	
	// Расчет цены товара + услуги
	public void getItemPlusServicePrice() {
		int item = Integer.parseInt(iprice);
		int service = serviceprice;
		int sum = item + service;
		serviceplusitemprice = sum;
		Log.info("Цена товара + услуги " + serviceplusitemprice);
	}
	
	// првоерка цены корзины с суммой цены товара + услуги
	public void assertCartItemPlusService() {
		int spi = serviceprice;
		int cp = Integer.parseInt(ctprice);
		int sum = spi + cp;
		if (sum == serviceplusitemprice) Log.info("Стоимость корзины равна сумме стоимости товара + услуги " + sum); else Log.info("Стоимость корзины не равна сумме стоимости товара + услуги");
	}
	
	// жмаканье на Подобрать аксессуары
	public void clickButtonAccessories() {
		try {
			accessories.click();
			Log.info("жмаканье на Подобрать аксессуары");
		} catch (Exception e){
			Log.info("Не жмакнулось на Подобрать аксессуары");
		}
	}
	
	// Клик по первому аксессуару из списка
	public void firstAccessories() {
		try {
			firstaccessories.click();
			Log.info("жмаканье на первый аксессуар");
		} catch(Exception e) {
			Log.info("не жмакнулся на первый аксессуар");
		}
	}
	
	// Метод получение цены второго товара
	public void secondItemPrice() {
		if (app.getNavigationHelper().waitPresense(By.xpath("//div[2]/div[1]/div[3]/div[2]/div[1][@class='totals-price']/div[2]"), 3)) {
			secondNewGetItemPrice();
		} else {
			secondGetItemPrice();
		}
	}
	
	// Поулчение новой цены второго товара 
	public void secondNewGetItemPrice() {
		if (app.getNavigationHelper().waitVisible(newseconditemprice,5)); {
			Pattern pattern = Pattern.compile("\\d+");
			String sniprice = newseconditemprice.getText();
			Matcher matcher = pattern.matcher(sniprice);
			int start = 0;
			StringBuilder buildersnitemprice = new StringBuilder();
			while (matcher.find(start)) {
				String substringsniprice = sniprice.substring(matcher.start(), matcher.end());
				start = matcher.end();
				siprice = buildersnitemprice.append(substringsniprice).toString();
			}
			Log.info("***QA: Новая цена второго товара  "+ siprice);
		}
	}
	
	// Поулчение цены второго товара 
	public void secondGetItemPrice() {
		if (app.getNavigationHelper().waitVisible(seconditemprice,5));
		Log.info("***QA: старая цена есть ");{
			Pattern pattern = Pattern.compile("\\d+");
			String sipriceel = seconditemprice.getText();
			Matcher matcher = pattern.matcher(sipriceel);
			int start = 0;
			StringBuilder buildersitemprice = new StringBuilder();
			while (matcher.find(start)) {
				String substringsiprice = sipriceel.substring(matcher.start(), matcher.end());
				start = matcher.end();
				siprice = buildersitemprice.append(substringsiprice).toString();
				Log.info("***QA: Цена второго товара "+ siprice);
			}
		}
	}
	
	// првоерка цены корзины с суммой цен товаров
	public void assertCart() {
		int tp = Integer.parseInt(ctprice);
		int fi = Integer.parseInt(iprice);
		int si = Integer.parseInt(siprice);
		int summitem = fi + si;
		if (summitem == tp) Log.info("Стоимость корзины равна сумме стоимости товаров " + summitem); else Log.info("Стоимость корзины не равна сумме стоимости товаров");
	}
	
	// жмаканье на "плюс" увеличение кол-ва товара
	public void clickButtonPlus() {
		try {
			String qi = quantityitem.getAttribute("value");
			buttonplus.click();
			Log.info("жмаканье на плюс");
			String qis = quantityitem.getAttribute("value");
			int a = Integer.parseInt(qi);
			int summ = a+1;
			int b = Integer.parseInt(qis);
			if (summ == b) Log.info("Количество товара равно " + qis); else Log.info("Не равно");
		} catch (Exception e){
			Log.info("Не жмакнулось на плюс");
		} 	
	}
	
	// жмаканье на "минус" уменьшение кол-ва товара
	public void clickButtonMinus() {
		try {
			String qi = quantityitem.getAttribute("value");
			buttonminus.click(); 
			Log.info("жмаканье на минус");
			String qis = quantityitem.getAttribute("value");
			int a = Integer.parseInt(qi);
			int summ = a-1;
			int b = Integer.parseInt(qis);
			if (summ == b) Log.info("Количество товара равно " + qis); else Log.info("Не равно");
		} catch (Exception e){
			Log.info("Не жмакнулось на минус");
		} 
	}
	
	// жмаканье на Удалить
	public void clickButtonDelete() {
		try {
			TimeUnit.SECONDS.sleep(2);
			buttondelete.click();
			Log.info("жмаканье на удалить");
		} catch (Exception e){
			Log.info("Не жмакнулось на Удалить");
		} 
	}
	
	// Проверка пустой корзины
	public void emptycart() {
		try{
			app.getNavigationHelper().waitVisible(emptycart,10);
			Log.info("корзина пуста");
		} catch (Exception e) {
			Log.info("корзина не пуста пуста");
		}
	}
}
