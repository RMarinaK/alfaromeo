/**
 * @author RasstriginaMK
 *
 */
package tehnosila.tehnosila_automation.pages.Desctop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.PageArea_Header;
import tehnosila.tehnosila_automation.pages.PagesBase;


public class Page_LoginPage extends PagesBase{

	private static Logger Log = LoggerFactory.getLogger(Page_LoginPage.class);
	//DSE: url to check page
	private String URL_MATCH = app.getWebDriverHelper().getBaseURL(); //+ /*"openam/UI/Login";*/ "account/logon?ReturnUrl=%2F";
	
	//DSE: page elements
    @FindBy(xpath=".//div[@class='box txt-center']") // форма авторизации
    public WebElement loginForm;

    @FindBy(id = "text-username") // поле ввода логина
    public WebElement inputLogin;

    @FindBy(id = "text-password") // поле ввода пароля
    public WebElement inputPassword;

    @FindBy(id="button-ok") // кнопка Войти
    public WebElement buttonLogon;

	@Override
	protected
    void tryToOpen() {
		driver.get(this.URL_MATCH);
    }
	
    @Override // если на странице нет формы авторизации, то разлогируемся, иначе остаемся на странице
	public boolean isOnThisPage()  {    	
    	if((!driver.getCurrentUrl().contains(this.URL_MATCH))||(!loginForm.isDisplayed())){    		
    		Log.info("***QA: trying to logout");
    		PageArea_Header header = MyPageFactory.getPage(PageArea_Header.class);
    		header.logout();
    		return false;
    	}
    	return true;
    }
    
  	//DSE: custom methods
	public void openLoginPage() { // заходим в систему
		driver.get(app.getProperty("baseUrl"));
		//app.getNavigationHelper().OpenUrl(app.getProperty("baseUrl"));
	}

	public PageArea_Header login(String login, String password) { // общий метод логирования
		app.getNavigationHelper().setText(inputLogin, login);
		app.getNavigationHelper().setText(inputPassword, password);
		buttonLogon.click();
		return MyPageFactory.getPage(PageArea_Header.class);
	}
	
}
