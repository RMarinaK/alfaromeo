 
package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author RasstriginaMK
 *
 */
public class PageArea_Header extends PagesBase {

//	private static Logger Log = LoggerFactory.getLogger(PageArea_Header.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL();
	
	//DSE: page elements

	@FindBy(id = "header")
	public WebElement header;
	
	@FindBy(id = "logo")
	private WebElement logo;

	@FindBy(xpath = "//i[@class='icon-20 i-tool-add']")
	public WebElement iconAdd;

	@FindBy(xpath = "//a[@ui-sref='documents-in']")
	private WebElement linkDocumentsIn;
	
	@FindBy(xpath = "//a[@ui-sref='documents-out']")
	private WebElement linkDocumentsOut;
	
	@FindBy(xpath = "//a[@ui-sref='documents-draft']")
	private WebElement linkDocumentsDraft;
	
	@FindBy(xpath = "//a[@ng-click='logout()']")
	private WebElement linkLogout;
	
	@FindBy(xpath = "//span[@class='alt name ng-binding']")
	private WebElement blockUserName;
	
	@Override
    void tryToOpen() {
		driver.get(this.URL_MATCH);
    }
	
    @Override
	public boolean isOnThisPage(){
    	NavigationHelper nh = app.getNavigationHelper();
    	nh.waitPresense(By.id("header"), 10);
    //	if((!driver.getCurrentUrl().contains(this.URL_MATCH))||(!nh.isDisplayedAfter(driver.findElement(By.id("header")), 1))){
    	if((!driver.getCurrentUrl().contains(this.URL_MATCH))){
    		return false;
    	}
    	return true;
    }

    //DSE: custom methods
    
    public void moveMouseToHeader(){
    	new Actions(driver).moveToElement(header).perform();
    }
    
	public Page_LoginPage logout() {
				
		try {
			new Actions(driver).moveToElement(blockUserName).perform();
			int counter = 1;
			while(!app.getNavigationHelper().waitVisible(linkLogout, 1)){
				blockUserName.click();
				counter++;
				if (counter>25){
					break;
				}
			}
			linkLogout.click();
			} 
		catch (StaleElementReferenceException e) {
			    return null;  
		}
		
		return MyPageFactory.getPage(Page_LoginPage.class);
	}

	public boolean isHeaderEnabled() {
		return driver.findElement(By.id("header")).isEnabled();
	}
	
	public boolean isProfileNamePresence(){
		return app.getNavigationHelper().waitPresense(By.xpath("//span[@class='block name ng-binding']"), 5);		
	}
	

}
