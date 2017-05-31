package tehnosila.tehnosila_automation.AppManager;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author RasstriginaMK
 *
 */
/**
 * 
 * This class will be used as main pool of custom navigation methods.
 * It will contain all page methods that can be used more than for 1 page object. 
 * Some methods can be aggregated to separated helpers in future.
 * This class extends NavigationBase where simple navigation methods are implemented.
 *
 *
 */

//TODO: move simple custom methods such as "softClick()" to NavigationBase class

public class NavigationHelper extends NavigationBase{
	private static Logger Log = LoggerFactory.getLogger(NavigationHelper.class);
	
	public NavigationHelper(ApplicationManager appManager){
		super(appManager);
	}
	
	public void setText(WebElement ele, String sValue){
		ele.clear();
		ele.click();
		ele.sendKeys(sValue);
	}

	public boolean waitVisible(WebElement ele, int timeoutSeconds){
		try {
		(new WebDriverWait(driver, timeoutSeconds))
			  .until(ExpectedConditions.visibilityOf(ele));
		return true;
			}
		catch (Exception e) {
			Log.error("***QA: the target element is not visible after timeout = "+timeoutSeconds+" seconds: "+ ele);
			e.printStackTrace();
			return false;
		}
	}
	//*[@id="checkout-total-wrapper"]/div/div/ul[3]/li[2]

	public boolean waitInvisible(By located, int timeoutSeconds){
		try {
		(new WebDriverWait(driver, timeoutSeconds))
			  .until(ExpectedConditions.invisibilityOfElementLocated(located));
		return true;
			}
		catch (Exception e) {
			Log.error("***QA: the target element is not invisible after timeout = "+timeoutSeconds+" seconds: "+ located);
			e.printStackTrace();
			return false;
		}
	}	
	
	public boolean isDisplayedAfter(WebElement ele, int timeoutSeconds){
		waitVisible(ele, timeoutSeconds);
		return ele.isDisplayed();
	}
	
	protected void findElement(By linkText) {
		driver.findElement(linkText);
	}

    protected void clickByXpath(String sXpath){
        driver.findElement(By.xpath(sXpath)).click();
    }
    
    protected void clearByID(String sID){
		driver.findElement(By.id(sID)).clear();
    }
    
    public void softClickByID(String sID){
    	if(verifyByID(sID)==1){
			click(By.id(sID));
		}
    }
    
    public void softClickByXpath(String sXpath){
    	if(verifyByXpath(sXpath)==1){
			click(By.xpath(sXpath));
		}
    }
    
    protected void sendKeysByID(String sID, String sKeys){
    	driver.findElement(By.id(sID)).sendKeys(sKeys);
    }
    
    protected void clickByClassName(String sClassName){
        driver.findElement(By.className(sClassName)).click();
    }

    protected void clickByCssSelector(String css) {
		driver.findElement(By.cssSelector(css)).click();
	}

	public void OpenUrl(String string) {
		driver.get(string);
	}

	public void click(By id) {
		waitPresense(id,2);
		driver.findElement(id).click();
	}
	
	public void filterClickAndWait(){
		click(By.id("tb-filter"));
		try{
		(new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(By.id("filter")));
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			filterClickAndWait();
		}
	}
	
	public void filterSubmitAndWait(){
		click(By.id("filter"));
		try{
			(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.id("filter-panel")));
			}
		catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	public boolean waitPresense(By locator, int timeout){
		//Log.debug("waitPresense() called");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
		(new WebDriverWait(driver, timeout))
			  .until(ExpectedConditions.presenceOfElementLocated(locator));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		}
		catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return false;
		}
	}
		
	public int verifyByID(String sID){
		if (!(driver.findElements(By.id(sID)).size() != 0)) {
            Log.error("the element with id " + sID + " cannot be found");
            return -1;
        }
		return 1;
	}
	
	public int verifyByXpath(String sXpath){
		if (driver.findElements(By.xpath(sXpath)).size() == 0) {
            Log.error("the element with xpath " + sXpath + " cannot be found");
            return -1;
        }
		return 1;
	}		
	
	protected boolean veryfyTextByLocator(By locator, String sText){
		if (!driver.findElement(locator).getText().contains(sText)) {
			Log.error("veryfyTextByLocator("+locator+", "+sText+") failed");
            
            return false;
		}
		else{
			Log.info("veryfyTextByLocator("+locator+", "+sText+") passed");
			return true;
		}
	}
	
	protected boolean veryfyStateByLocator(By locator, boolean isChecked){
		if (!driver.findElement(locator).isSelected()) {
            Log.error("veryfyStateByLocator("+locator+", "+isChecked+") failed");
            
            return false;
		}
		else{
			Log.info("veryfyStateByLocator("+locator+", "+isChecked+") passed");
			return true;
		}
	}
	
	public List<WebElement> findElementsList(By locator){
		List<WebElement> elements = driver.findElements(locator);
	      Log.debug("Number of elements: "+elements.size());
	      //Display their links and text
//	      for(WebElement ele : elements){
//	        Log.debug(ele.getAttribute("href"));
//	        Log.debug(ele.getText());
//	      }
	      return elements;	
	}	
	
	public boolean isElementPresent(By by){
		try{
			
			driver.findElement(by);
			return true;
		}catch(Exception e){
			return false;
			}
		}
	
	public void refreshPage(){
		driver.navigate().refresh();
	}
	
	public void getPage(String URL){
		driver.get(URL);
	}
	
	// Открытие новой вкладки в браузере
	public void openInNewWindow(String url) {
	    ((JavascriptExecutor) driver)
	                 .executeScript("window.open(arguments[0])", url);
	}
	
	public String getBaseURL(){
		return getBaseURL();
	}
	
	// открытие страницы по URL
	public void getURL(String URL){ //String URL
		//openInNewWindow(URL);
		//driver.switchTo().window(URL);
		driver.navigate().to(URL);
	}
	
	// читска кук
	public void delCookies(){ 
		driver.manage().deleteAllCookies();
	}
	
	
}
