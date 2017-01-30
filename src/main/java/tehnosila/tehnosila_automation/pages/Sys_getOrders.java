package tehnosila.tehnosila_automation.pages;

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

public class Sys_getOrders extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Sys_getOrders.class);

	@Override
	void tryToOpen() {
		// TODO Auto-generated method stub
		
	}
	
	@FindBy(xpath="//Payment/Name") //
	private WebElement paymentname; // Тип оплаты
	
	// вытягивание типа оплаты
		public String getPaymentName(){
				return paymentname.getText();
		//	return paymentname.getAttribute("text");
			}
		
		//	проверка отображения типа оплаты 
		public void assertPaymentName(String paymentName) throws Exception {
			try {
				Assert.assertEquals(paymentName, getTextByJavascript(paymentname)); 
				Log.info("***QA: Тип оплаты " +  getTextByJavascript(paymentname));
			}
		    catch(Exception e) {      
		    	Log.info("Element Not Found");     
	           ScreenShot.takeScreenShot();       
	        }    
		}	
	
	
}
