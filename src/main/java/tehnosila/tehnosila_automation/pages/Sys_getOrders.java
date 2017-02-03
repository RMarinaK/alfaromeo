package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
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
	
	@FindBy(xpath="//Invoice")
	private WebElement invoicegid; // Номер заказа
	
	@FindBy(xpath="//Payment/Name") //
	private WebElement paymentname; // Тип оплаты
	
	@FindBy(xpath="//Delivery/name") //
	private WebElement deliveryname; // Тип доставки
	
	
	// вытягивание номера заказа
	public String getID(){
		return invoicegid.getAttribute("gid");
	}
	
	//	проверка отображения типа оплаты 
	public void assertOrders() throws Exception {
		try {
			Assert.assertEquals(NavigationBase.pnumberorder, getID()); 
			Log.info("***QA: Номер заказа "+ getID());

		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
           ScreenShot.takeScreenShot();       
        }      
	}	
	
	//	проверка отображения типа оплаты 
	public void assertPaymentName(String paymentName) throws Exception {
		try {
			//String ppaymentName = NavigationBase.prcardondelivery;
			Assert.assertEquals(paymentName, getTextByJavascript(paymentname)); 
			Log.info("***QA: Тип оплаты " +  getTextByJavascript(paymentname));
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
	          ScreenShot.takeScreenShot();       
	    }    
	}	
	
	//	проверка отображения типа доставки
	public void assertDeliveryName(String deliveryName) throws Exception {
		try {
			Assert.assertEquals(deliveryName, getTextByJavascript(deliveryname)); 
			Log.info("***QA: Тип доставки " +  getTextByJavascript(deliveryname));
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
	          ScreenShot.takeScreenShot();       
	    }    
	}	
	
	
}
