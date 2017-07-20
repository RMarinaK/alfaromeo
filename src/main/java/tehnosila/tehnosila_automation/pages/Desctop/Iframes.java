package tehnosila.tehnosila_automation.pages.Desctop;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.pages.PagesBase;


/**
 * @author MRasstrigina
 *
 */

public class Iframes extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Iframes.class);
	//DSE: url to check page
	
	
	@Override
	protected
		void tryToOpen() {
	//	driver.get(this.URL_MATCH);
	}
	
	// поиск всех iframes на странице, выход из iframe easyXDM_fl29372_provider
	public void exitIframes() {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		Log.info("Number of iframes on the page are " + numberOfFrames);
		//By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
		Log.info("The total number of iframes are " + iframeElements.size());
        
		for(WebElement count: iframeElements)
		{        
			if (count.isDisplayed())
			{ 
				if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id='easyXDM_fl21866_provider']")) == true) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='easyXDM_fl21866_provider']")));      
					driver.findElement(By.xpath("//a[@fl-track-ga='click-on-no']")).click();
				}
				if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id='easyXDM_fl29372_provider']")) == true) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='easyXDM_fl29372_provider']")));      
					driver.findElement(By.xpath("//div[@id='close']/img")).click();
					//driver.switchTo().defaultContent();	
				}
				
				break;
        }
    }
	}
	
	// поиск всех iframes на странице, выход из iframe, для мобилки
	public void exitMobileIframes() {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		Log.info("Number of iframes on the page are " + numberOfFrames);
		//By finding all the web elements using iframe tag
		List<WebElement> iframeElements = driver.findElements(By.tagName("iframe"));
		Log.info("The total number of iframes are " + iframeElements.size());
        
		for(WebElement count: iframeElements)
		{        
			if (count.isDisplayed())
			{ 
				if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id='easyXDM_fl29629_provider']")) == true) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='easyXDM_fl29629_provider']")));      
					driver.findElement(By.xpath("//div[@id='close']/img")).click();
				}
				if (app.getNavigationHelper().isElementPresent(By.xpath("//*[@id='easyXDM_fl29372_provider']")) == true) {
					driver.switchTo().defaultContent();
					driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='easyXDM_fl29372_provider']")));      
					driver.findElement(By.xpath("//div[@id='close']/img")).click();
					//driver.switchTo().defaultContent();	
				}
				
				break;
        }
    }
	}
	
}
/*/html/body/div/div[3]/a[1]
		body > div > div.Notification-buttons > a.Notification-button.Notification-buttonBlock
		<a fl-close="" fl-track-ga="click-on-no" class="Notification-button Notification-buttonBlock">Нет</a>
		<iframe name="easyXDM_fl21866_provider" id="easyXDM_fl21866_provider" src="https://api.flocktory.com/u_widget/get-widget?body=%7B%22version-id%22%3A%22616a81bd-85bb-4a2d-9933-a73a75d47878%22%2C%22locale%22%3A%22ru%22%2C%22site-id%22%3A%221012%22%2C%22campaign-id%22%3A%2221866%22%2C%22flags%22%3A%5B%5D%2C%22params%22%3A%5B%7B%22key%22%3A%22attach_key%22%2C%22value%22%3A%22campaign%22%7D%2C%7B%22key%22%3A%22attach_value%22%2C%22value%22%3A%2221866%22%7D%5D%7D&amp;ns=flocktory&amp;xdm_e=http%3A%2F%2Fwww.tehnosila.ru&amp;xdm_c=fl21866&amp;xdm_p=1" frameborder="0" class="flockapi-insular-widget" style="width: 410px; height: 208px; border: 0px; margin-top: 353.5px; margin-bottom: 353.5px;"></iframe>
		
easyXDM_fl21866_provider*/
