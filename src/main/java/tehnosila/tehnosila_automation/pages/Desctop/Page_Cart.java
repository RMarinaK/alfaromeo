package tehnosila.tehnosila_automation.pages.Desctop;



import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.PagesBase;

/**
 * @author MRasstrigina
 *
 */

public class Page_Cart extends PagesBase{

	private static Logger Log = LoggerFactory.getLogger(Page_Cart.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(xpath="//div[@id='button-buy-in-order']/a[@id='ordering']")  //[contains(text(),'Оформить заказ')]
//	@FindBy(xpath="//div[@id='page-wrapper']/div[@id='content-wrapper']/div[@class='cart-wrap']/div[@id='cart-contents']/div[@id='cart-items']/form[@id='cart-form']/div[@id='cart-right-menu']/div[@class='button-holder']/div[@id='button-buy-in-order']/a")  //div[@id='button-buy-in-order']
	////div[@id='page-wrapper']/div[@id='content-wrapper']/div[@class='cart-wrap']/div[@id='cart-contents']/div[@id='cart-items']/form[@id='cart-form']/div[@id='cart-right-menu']/div[@class='button-holder']/div[@id='button-buy-in-order']/a
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
	
	@FindBy(xpath="//div[1]/div[1]/div[3]/div/a[@class='option remove gtm-process-remove-from-cart']")
	private WebElement buttondelete; // Кнопка удалить, удаляет товар из корзины
	
	@FindBy(xpath="//input[@class='stub text']")
	private WebElement quantityitem; // Количество товара
	
	@FindBy(xpath="//div[contains(text(),'Ваша корзина пуста')]")
	private WebElement emptycart; // Блок с текстом "Ваша корзина пуста"
	
	@FindBy(xpath="//a[@class='open-accessories-popup button white-flat']")
	private WebElement accessories; // Кнопка "Подобрать аксессуары"
	
	@FindBy(xpath="//div[1]/div/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div/div/div/div/span/a/span[@class='icon-n order']")
	private WebElement minibuttoncart; // Иконка корзины аксессуаров
	
	@FindBy(xpath="//div[1]/div/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div/div/div/div/span/a/span[@class='icon-n cart']")
	private WebElement otherminibuttoncart;
	
	@FindBy(xpath="//div[1]/div[1]/div[3]/div[2]/div[1][@class='totals-price']")
	private WebElement itemprice; // Цена первого товара
	
	@FindBy(xpath="//div[1]/div[1]/div[3]/div[2]/div[1][@class='totals-price']/div[2]")
	private WebElement newitemprice; // Новая цена товара первого товара
	
	@FindBy(xpath="//div[2]/div[1]/div[3]/div[2]/div[1][@class='totals-price']")
	private WebElement seconditemprice; // Цена второго товара твоара
	
	@FindBy(xpath="//div[2]/div[1]/div[3]/div[2]/div[1][@class='totals-price']/div[2]")
	private WebElement newseconditemprice; // Новая цена второго товара
	
	@FindBy(xpath="//*[@id='cart-total-price']")
	private WebElement carttotalprice; // Сумма всей корзины
	
	@FindBy(xpath="//div[@class='service-row'][1]/span[1]")
	private WebElement servicecheckbox; // Чекбокс услуги
	
	@FindBy(xpath="//a[@class='open-services-list']")
	private WebElement servicepriceel; // Цена выбранных услуг
	
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
	Iframes iframes = MyPageFactory.getPage(Iframes.class);
	// жмаканье на "Офорить заказ"
	public void clickButtonOrdering() throws InterruptedException {
		if (buttonordering.isEnabled()) {
			iframes.exitIframes();
		}
		int y = -100;
		String code = "window.scroll(" + (buttonordering.getLocation().x) + "," + (buttonordering.getLocation().y + y) + ");";
	    ((JavascriptExecutor)driver).executeScript(code, buttonordering);
			Log.info(" " + buttonordering.getText());
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
	
	// жмаканье на Подобрать аксессуары
	public void clickButtonAccessories() {
		try {
			accessories.click();
			Log.info("жмаканье на Подобрать аксессуары");
		} catch (Exception e){
			Log.info("Не жмакнулось на Подобрать аксессуары");
		}
	}
	
	// жмаканье на иконку корзины аксессуаров с api
	public void clickMiniButtonCart() {
		try {
			minibuttoncart.click();
			Log.info("жмаканье на иконку корзины аксессуаров");
		} catch (Exception e) {
			Log.info("не жмаканулось на иконку корзины аксессуаров");
		}
	}
	
	// жмаканье на иконку корзины аксессуаров без api
	public void clickOtherMiniButtonCart() {
		try {
			otherminibuttoncart.click();
			Log.info("жмаканье на иконку корзины аксессуаров");
		} catch (Exception e) {
			Log.info("не жмаканулось на иконку корзины аксессуаров");
		}
	}
	
	// Метод жмаканья по корзинке аксессуаров в попапе
	public void clickAccessoriesButtonCart() {
		if (minibuttoncart.isDisplayed()) {
			clickMiniButtonCart();
		} else {
			clickOtherMiniButtonCart();
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
	
	// Метод получение цены первого товара
	public void itemPrice() {
		if (app.getNavigationHelper().waitPresense(By.xpath("//div[1]/div[1]/div[3]/div[2]/div[1][@class='totals-price']/div[2]"), 3)) {
			newGetItemPrice();
		} else {
			getItemPrice();
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
	
	// Метод получение цены второго товара
	public void secondItemPrice() {
		if (app.getNavigationHelper().waitPresense(By.xpath("//div[2]/div[1]/div[3]/div[2]/div[1][@class='totals-price']/div[2]"), 3)) {
			secondNewGetItemPrice();
		} else {
			secondGetItemPrice();
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
		
	// првоерка цены корзины с суммой цен товаров
	public void assertCart() {
		int tp = Integer.parseInt(ctprice);
		int fi = Integer.parseInt(iprice);
		int si = Integer.parseInt(siprice);
		int summitem = fi + si;
		if (summitem == tp) Log.info("Стоимость корзины равна сумме стоимости товаров " + summitem); else Log.info("Стоимость корзины не равна сумме стоимости товаров");
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
		int start = 45;
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
}
