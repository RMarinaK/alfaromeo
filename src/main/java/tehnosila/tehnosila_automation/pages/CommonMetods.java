package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

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
		
	// жмаканье на ссылку "ссылке" в информационном сообщении
    public void clickLinkText() {
    	linkText.click();  	
    //	app.getNavigationHelper().waitInvisible(By.xpath("//div[@class='loading']"), 10);
	}
    
	// вытягивание названия страницы
	public String getTitleClient(){
		return title.getText();
	}

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

	// вытаскивание pactionurl
/*	public void getActionURL(){
	//	NavigationBase.pheader = getHeader();
	//	Log.info("***QA: nameaction " +getHeader());
		try {
			String currenturl = driver.getCurrentUrl();
			int s = 0;
			for (int x=1; x< currenturl.indexOf("action/"); x++){
				s = x++;}
			Log.info("***QA: s "+ s);
			NavigationBase.pactionurl = currenturl.substring(s + 1);
			Log.info("***QA: pactionurl "+ NavigationBase.pactionurl);
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
         }  
	}*/	
	
	// Получение HTTP response code
	public void getHTTPResponseCode() throws IOException {
		String currenturl = driver.getCurrentUrl();
		URL url = new URL(currenturl);
		Log.info("***QA: текущая страница: " + currenturl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
		connection.connect();
		int code = connection.getResponseCode();
	    Log.info("***QA: HTTP response code " + code);
	}

	
	// 
	public void getCookieSession() {
		 Log.info("***QA: session " + driver.manage().getCookieNamed("session"));
		
	}	
	// ----------------------------------------------------------------------------------------------------------------------------
	
	//Проверка количества начисляемых бонусов   @author EDanilova	
	public void bonusAmountCheck(int i, int j) throws Exception{
		try {
			Assert.assertEquals(i, j); // проверка отображения Header
			Log.info("***QA: Количество начисляемых бонусов совпадает");
		}
		catch(Exception e) {      
			Log.info("***QA: Ошибка! Количество начисляемых бонусов НЕ совпадает");     
			ScreenShot.takeScreenShot();       
		}  				
	}
	
	//Проверка номера привязанной бонусной карты   @author EDanilova				
	public void bonusCardCheck(String i, String j) throws Exception{
		try {
			Assert.assertEquals(i, j); // проверка отображения Header
			Log.info("***QA: Номера бонусных карт совпадают");
		}
		catch(Exception e) {      
			Log.info("***QA: Ошибка! Номера бонусных карт НЕ совпадают");     
			ScreenShot.takeScreenShot();       
		}  				
	}
	

}
