package tehnosila.tehnosila_automation.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.testng.Assert;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;

/**
 * @author MRasstrigina
 *
 */

public class Term_Page_Order extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Term_Page_Order.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/order";
	
	@FindBy(xpath="//button[@class='button button--grey button--small tap openPopup']")
	private WebElement buttonsetpersonal;
	
	@FindBy(id = "OrderForm_orderContact_fio")
	private WebElement orderfromordercontactfio;
	
	@FindBy(xpath = "//input[@name='OrderForm[orderContact][phone]']")
	private WebElement orderfromordercontactphone;
	
	@FindBy(xpath = "//input[@name='OrderForm[orderContact][email]']")
	private WebElement orderfromordercontactemail;
	
	@FindBy(xpath = "//label/span")
	private WebElement confirm;
	
	@FindBy(xpath="//a[@class='button button--black checkout__add']")
	private WebElement buttonsubmitPersonal; // Кнопка "Добавить"
		
	@FindBy(xpath="//button[@class='button button--yellow button--bold button--full_width checkout__next tap']")
	private WebElement buttonsubmitorder; // Кнопка "Завершить оформление"
	
	@FindBy(id="leave")
	private WebElement buttonleave; // Кнопка "Завершить оформление"
	
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
	
	//клик по "Добавить" в оформлении заказа
	public void setPersonal() throws Exception {
		try {
			buttonsetpersonal.click();
		}
		catch (TimeoutException ignore) {
			Log.info("Element Not Found");     
		    ScreenShot.takeScreenShot();   
		}
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
	
	//Клик по чек-боксу
	public void clickCheckBox() throws Exception {
		try {
			confirm.click();
		}
		catch (TimeoutException ignore) {
			Log.info("Element Not Found");     
		    ScreenShot.takeScreenShot();   
		}
	}
	
	//Клик на "Добавить" в подтвеждении персональных данных
	public void clickButtonSubmitPersonal() throws Exception {
		try {
			buttonsubmitPersonal.click();
		}
		catch (TimeoutException ignore) {
			Log.info("Element Not Found");     
		    ScreenShot.takeScreenShot();   
		}
	}

	// жмаканье на "Завершить оформление"
	public void clickButtonSubmitOrder() throws Exception {
		try {
			buttonsubmitorder.click();
		}
		catch (TimeoutException ignore) {
			Log.info("Element Not Found");     
		    ScreenShot.takeScreenShot();   
		}
	}
	
	// закрытие окна печати
	public void closePrintWindow() throws AWTException {
		
		try {Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		 } catch (org.openqa.selenium.TimeoutException te) {
		        ((JavascriptExecutor)driver).executeScript("window.stop();");
		    } catch (UnhandledAlertException uae) {
		        Alert alert = driver.switchTo().alert();
		        alert.accept();
		    }
		// переключаемся в новое окно
	//	driver.switchTo().window("Печать");
		// закрываем его
	//	driver.close();

	//	Alert printDialog = driver.switchTo().alert();
		 
 // закрыли системное окно

	}
	
}
