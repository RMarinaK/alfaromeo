package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;

/**
 * @author MRasstrigina
 *
 */

public class Page_HelpFeedback extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_HelpFeedback.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(id="name")
	private WebElement inputname; // поле Имя
	
//	@FindBy(css ="div.field > #email']")
//	private WebElement inputemail; // поле E-mail
	
//	@FindBy(id="phone")
//	private WebElement inputphone; // поле Телефон
	
	@FindBy(id="city")
	private WebElement inputcity; // поле Город
	
	@FindBy(id="message")
	private WebElement textareamessage; // поле Текст сообщения
	
	@FindBy(xpath="//form[@id='feedback_form']/div[@class='button-holder row']/a[contains(text(),'Отправить')]")
	private WebElement buttonsend; // кнопка Отправить
	
	@FindBy(xpath="//div[contains(text(),'Спасибо за Ваше обращение.')]")
	private WebElement message; // кнопка Спасибо за Ваше обращение.
	
	@FindBy(linkText = "Вернуться на главную")
	public WebElement linkText; // ссылка "ссылке" в информационном сообщении
	
	@FindBy(linkText ="Вернуться на главную")
	private WebElement buttonback; // кнопка Вернуться на главную
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	protected boolean isNecessaryToChangeParam(String param){
		if(param.equals(" ")||param.equals("")){
			return false;
		}else{
			return true;
		}		
	}
	
	// ввод Имя
	public void setName(String string) {
		if(isNecessaryToChangeParam(string)){
			inputname.click();
			inputname.clear();
			inputname.sendKeys(string);
		}
	}
	
	// ввод E-mail
	public void setEmail(String string) {
		WebElement inputemail =  driver.findElement(By.cssSelector("div.field > #email"));
		if(isNecessaryToChangeParam(string)){
			inputemail.click();
			inputemail.clear();
			inputemail.sendKeys(string);
		}
	}
	
	// ввод Телефон
	public void setPhone(String string) {
		WebElement inputphone =  driver.findElement(By.cssSelector("div.field > #phone"));
		if(isNecessaryToChangeParam(string)){
			inputphone.click();
			inputphone.clear();
			inputphone.sendKeys(string);
		}
	}
	
	// ввод Город
	public void setCity(String string) {
		if(isNecessaryToChangeParam(string)){
			inputcity.click();
			inputcity.clear();
			inputcity.sendKeys(string);
		}
	}
	
	// ввод Текст сообщения
	public void setMessage(String string) {
		if(isNecessaryToChangeParam(string)){
			textareamessage.click();
			textareamessage.clear();
			textareamessage.sendKeys(string);
		}
	}
	
	// жмаканье на "Отправить"
	public void clickButtonSend() throws Exception {
		try {
			buttonsend.click(); 
			Log.info("жмаканье на Отправить");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// вытягивание сообщения "Спасибо за Ваше обращение."
	public String getMessage(){
		return message.getText();
	}
	
	// проверка отправки feedback
	public void assertMessage(String string) throws Exception {
		try {
			Assert.assertEquals(string, getMessage()); // проверка отображения сообщения
			Log.info("***QA: Message "+ getMessage());
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// жмаканье на "Вернуться на главную"
	public void clickButtonBack() throws Exception {
		try {
			driver.findElement(By.linkText("Вернуться на главную")).click();
		//	WebElement linkText =  driver.findElement(By.linkText("Вернуться на главную"));
		//	linkText.click(); 
			Log.info("жмаканье на Вернуться на главную");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
}
