/**
 * 
 */
package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ApplicationManager;

/**
 * @author RasstriginaMK
 *
 */
public abstract class PagesBase {
	private static Logger Log = LoggerFactory.getLogger(PagesBase.class);
	protected ApplicationManager app;
	protected WebDriver driver;
	
	public PagesBase(){
		app = ApplicationManager.getInstance();
		driver = app.getWebDriverHelper().getDriver();
		
	}
	
	@FindBy(xpath="//div[@class='preloader']/i")
	public WebElement loader;
	
	
	@FindBy(xpath = "//div[@class='Notification']/a[contains(text(),'Нет')]")
	private WebElement noNotification; // форма запроса "Включить уведомления о скидках и акциях"
	
	public String getBaseURL(){
		return app.getWebDriverHelper().getBaseURL();
	}
	
/*	public String getBaseURLnotHttp(){
		return app.getWebDriverHelper().getBaseURLnotHttp();
	}*/
	
    public boolean isOnThisPage(){
        return true;
    }

    
    
    public void handleAlertIfExists(){
    	//Log.debug("handleAlertIfExists() called");
    	if(isAlertPresent()){
    		String sAlertText = getAlertText();
    		if(!verifyIfAlertCorrect(sAlertText)){
    			Log.warn("***QA: There is unhandeled alert with text: " + sAlertText);
    			acceptAlert(false);
    		}
    		acceptAlert(true);
     	}
    }
    
    public void handlePopUpIfExists(){
    	Log.debug("handlePopUpIfExists() called");
    	if(app.getNavigationHelper().waitPresense(By.xpath("//div[@class='dlg-contentin dlg-icon dlg-icon-error']"), 0)){
    	String sPopUpText = getPopUpText();
    		if(!verifyIfAlertCorrect(sPopUpText)){
    			Log.warn("***QA: There is unhandeled PopUp with text: " + sPopUpText);
    		}
    		closePopUp();
     	}
    }
    
    
    // Получение текста скрытого элемента
    public String getTextByJavascript(final WebElement element) {
        String script = "var element = arguments[0];" 
		        + "return element.textContent;"
                        ;
        return (String) ((JavascriptExecutor)driver).executeScript(script, element);
    }

    
	private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }
	
	private void acceptAlert(boolean bAcceptOrDismiss){
    	Alert alert = driver.switchTo().alert();
    	if(!bAcceptOrDismiss){
    		alert.dismiss();
    	}
    	alert.accept();
    }
    
	public void closePopUp(){
		driver.findElement(By.xpath("//a[@class='button close']")).click();		
	}
	
	
	
	public void refreshPage(){
		driver.navigate().refresh();
		//driver.get(driver.getCurrentUrl());
	}
	
//    DSE: every page must implement this method
    abstract void tryToOpen();
    
    public boolean verifyIfAlertCorrect(String sAlertText){
    	//Log.debug(sAlertText);
    	String[] aErrMasks = {"ORA00","Exception","Internet","Asp.Net"};
    	for(int i = 0; i<aErrMasks.length; i++){
    		if(sAlertText.contains(aErrMasks[i])){
    			return false;
    		}
    	}
    	Log.info("***QA: Alert with text catched: " + sAlertText);
    	return true;
    }

    public Alert waitForAlert(int timeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout).ignoring(NullPointerException.class);
        Alert alert = wait.until(new ExpectedCondition<Alert>() {
            @Override
            public Alert apply(WebDriver driver) {
                Alert alert = driver.switchTo().alert();
                alert.getText();
                return alert;
            }
        });
        return alert;
    }
    
    public WebElement waitForPopUp(int timeout) {
    	By xpath = By.xpath("//div[@class='dlg-contentin dlg-icon dlg-icon-error']");
    	if(app.getNavigationHelper().waitPresense(xpath,1)){
    		return driver.findElement(xpath);
    	}
        return null;
    }
    
    public Alert getAlert(){
        return waitForAlert(1);
    }
    
    public WebElement getPopUp(){
    	return waitForPopUp(1);
    }
     
    public String getAlertText() {
        return getAlert().getText();
    }
    
    public String getPopUpText() {
        return getPopUp().findElement(By.xpath("//div[@class = 'dlg-contentin dlg-icon dlg-icon-error']")).getText();
    }

	protected void sendKeysViaActions(String value) {
		Actions actions = new Actions(driver);
		value = value.replaceAll("\\(", Keys.chord(Keys.SHIFT,"9")); 
		value = value.replaceAll("\\#", Keys.chord(Keys.SHIFT,"3")); 
		value = value.replaceAll("\\-", Keys.SUBTRACT.toString()); 
		value = value.replaceAll("\\}", Keys.chord(Keys.SHIFT,"]")); 
		actions.sendKeys(value); 
		actions.perform();
	}
	
	protected void sendKeysReplaceSpecSymbols(WebElement elem, String value) {
		value = value.replaceAll("\\(", Keys.chord(Keys.SHIFT,"9")); 
		value = value.replaceAll("\\#", Keys.chord(Keys.SHIFT,"3")); 
		value = value.replaceAll("\\-", Keys.SUBTRACT.toString()); 
		value = value.replaceAll("\\}", Keys.chord(Keys.SHIFT,"]")); 
		elem.sendKeys(value); 
	}
	
	public void acceptConfirm() throws Exception {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try{
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			handleAlertIfExists();
		}catch(WebDriverException e){
			
		}		
	}
	
}
