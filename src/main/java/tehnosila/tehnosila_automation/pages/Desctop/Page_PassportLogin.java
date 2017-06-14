package tehnosila.tehnosila_automation.pages.Desctop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.PagesBase;

/**
 * @author MRasstrigina
 *
 */

public class Page_PassportLogin extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_PassportLogin.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/cart";
	
	@FindBy(id="email-login")
	private WebElement inputemaillogin; // поле E-mail
	
	@FindBy(id="password")
	private WebElement inputpassword; // поле Пароль
	
	@FindBy(xpath = "//form[@id='login-form']/div/div/a[contains(text(),'Войти')]")
	private WebElement buttonenter; //Кнопка "Войти"
		
	
	@Override
	protected
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
	
	// ввод E-mail
	public void setEmail(String string) {
		if(isNecessaryToChangeParam(string)){
			inputemaillogin.click();
			inputemaillogin.clear();
			inputemaillogin.sendKeys(string);
		}
	}
	
	// ввод Пароль
	public void setPassword(String string) {
		if(isNecessaryToChangeParam(string)){
			inputpassword.click();
			inputpassword.clear();
			inputpassword.sendKeys(string);
		}
	}
	
	// жмаканье на "Войти"
	public void clickButtonEnter() throws Exception {
		try {
			buttonenter.click(); 
			Log.info("жмаканье на Войти");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	 
}
