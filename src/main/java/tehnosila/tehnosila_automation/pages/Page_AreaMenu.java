package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MRasstrigina
 *
 */

public class Page_AreaMenu extends PagesBase {
	private static Logger Log = LoggerFactory.getLogger(Page_AreaMenu.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	//DSE: page elements

//	@FindBy(xpath = "//a[@class='menu new']")
//	private WebElement menunew; // меню
	
	@FindBy(xpath = "//div[@id='userlogin']/div[1]/span")
	private WebElement username; // имя и фамилия пользователя
	
	@FindBy(xpath = "//div[@id='userlogin']/div[2]/a")
	private WebElement suborgname; // название и ИНН организации
	
	
	@Override
    void tryToOpen() {
		driver.get(this.URL_MATCH);
    }
	
    @Override
	public boolean isOnThisPage(){
    	NavigationHelper nh = app.getNavigationHelper();
    	nh.waitPresense(By.id("header"), 10);
    	if((!driver.getCurrentUrl().contains(this.URL_MATCH))){
    		return false;
    	}
    	return true;
    }
 
 // проверка наличия логотипчика Техносила
    public boolean isLogo() {
    	  boolean flag = app.getNavigationHelper().waitPresense(By.xpath("//img[@alt='Техносила']"), 10);
    	  Log.info("***QA: Is the logo displayed? "+ flag);
    	  return flag;
    	 }	
    
	// вытягивание ФИО пользователя
	public String getUserName(){
		return username.getText();
	}
    
	// вытягивание ИНН и наименования абонента
	public String getSuborgName(){
		return suborgname.getText();
	}
	
} 
