package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * @author MRasstrigina
 *
 */

// Класс общих методов
public class CommonMetods extends Page_AreaMenu {
	private static Logger Log = LoggerFactory.getLogger(CommonMetods.class);

	@Override
	public boolean isOnThisPage(){
		return true;
	}

	@FindBy(linkText = "ссылке")
	public WebElement linkText; // ссылка "ссылке" в информационном сообщении
	
//	@FindBy(xpath = "//div[@id='viewModel']/div/h3") 
//	private WebElement title; // заголовок на страницах
	
	@FindBy(xpath = "//div[@class='filter-tool']/h3") 
	private WebElement titlefilter; // заголовок фильтра на страницах реестров документов
	
	@FindBy(id = "logo")
	public WebElement logo; // логотип
	
	@FindBy(xpath = "//title") 
	private WebElement title; // title на страницах
	
	@FindBy(xpath = "//h1") 
	private WebElement header; // заголовок на страницах
	
	@FindBy(xpath="//a[@class='button show-all-items']")
	private WebElement actioncatalog; // Кнопка Перейти к покупкам
	
	@FindBy(xpath="//div[@class='item'][2]")
	private WebElement clickactioncatalogitem; // Выбор второго блока товаров в акции честные цены
	
	@FindBy(xpath="//a[@class='item first-in-row']")
	private WebElement clickcatalogitemincategory; // Выбор первого блока товаров в категории
	
    // жмаканье на ссылку "ссылке" в информационном сообщении
    public void clickLinkText() {
    	linkText.click();  	
    //	app.getNavigationHelper().waitInvisible(By.xpath("//div[@class='loading']"), 10);
	}
    
	// вытягивание названия страницы
	public String getTitleClient(){
		return title.getText();
	}
	//*[@id="promoword-banner"]/span
	//*[@id="tab-promoWord"]/span/text()
	// вытягивание заголовка фильтра страницы
	public String getTitleFilterClient(){
		return titlefilter.getText();
	}
	
	// ожидание пока отработает прелоэдер
	public void Waiting(){
		app.getNavigationHelper().waitVisible(driver.findElement(By.xpath("//div[@id='menu_popup']")), 10);
	}
	
	public void WaitingMobile(){
		app.getNavigationHelper().waitVisible(driver.findElement(By.xpath("//a[@id='popup-button-to-cart']")), 10);
	}

	public void RefreshTerm(){
		app.getNavigationHelper().refreshPage();
	}
	
	// жмаканье на логотип
	public void clickLogo() throws Exception {
		try {
			logo.click(); 
			Log.info("Логотип");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// вытягивание названия страницы
	public String getTitle(){
		return driver.getTitle();
	}
	
	// вытягивание заголовка страницы
	public String getHeader(){
		return header.getText();
	}
	
	public void scrollPage() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," + "document.body.scrollHeight,document.documentElement.clientHeight));");
	}
	
	// скрол страницы вниз (не изменять кол-во пикселей)
	public void scrolling() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)","");
	}
	
	// проверка отображения Title
	public void assertTitle(String title) throws Exception {
		try {
			Log.info("***QA: Title "+ getTitle());
			Assert.assertEquals(title, getTitle()); // проверка отображения Title
			Log.info("***QA: Title "+ getTitle());
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
		
	// проверка отображения Header
	public void assertHeader(String header) throws Exception {
		try {
			Assert.assertEquals(header, getHeader()); // проверка отображения Header
			Log.info("***QA: Header "+ getHeader());
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// жмаканье на "Перейти к покупкам"
	public void clickActionRassrochkaCatalog() throws Exception{
		try {
			actioncatalog.click(); 
			Log.info("жмаканье на Перейти к покупкам");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }  
	}
	
	// жмаканье на второй блок товаров в акции честные цены
	public void clickActionCatalogItem() throws Exception{
		try {
			clickactioncatalogitem.click(); 
			Log.info("жмаканье на второй блок товаров в акции честные цены");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }  
	}
	
	// жмаканье на первый блок товаров в категории 
	public void clickCatalogItemInCategory() throws Exception{
		try {
			clickcatalogitemincategory.click(); 
			Log.info("жмаканье на первый блок товаров в категории");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }  
	}
}
