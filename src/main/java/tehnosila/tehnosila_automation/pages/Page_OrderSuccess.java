package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;

public class Page_OrderSuccess extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_OrderSuccess.class);
	
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/order";
	
	@FindBy(xpath="//div[contains(text(),'Спасибо! Ваш заказ принят!')]")
	private WebElement message; // Сообщение Спасибо! Ваш заказ принят!
	
//	@FindBy(xpath="//a[contains(text(),'Детали заказа')]")
//	private WebElement buttonorderdetails; // Кнопка Детали заказа
	

	@FindBy(id="test_order-number")
	private WebElement ordernumber; // Номер заказа
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage(URL_MATCH);
	}
	
	// вытягивание Спасибо! Ваш заказ принят!
	public String getMessage(){
			return message.getText();
		}
	
	// вытягивание номера заказа
	public String getNumber(){
		return ordernumber.getAttribute("data-order-number");
	}
	
	
	// проверка отображения Спасибо! Ваш заказ принят!
	public void assertTitle() throws Exception {
		try {
			Assert.assertEquals("СПАСИБО! ВАШ ЗАКАЗ ПРИНЯТ!", getMessage()); // проверка отображения Cпасибо! Ваш заказ принят!
			Log.info("***QA: Message "+ getMessage());
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
	
	// ожидание пока страница прогрузится и проверка соответствия номер заказа
	public void getOrders() throws Exception{
		NavigationBase.pnumberorder = getNumber();
		app.getNavigationHelper().getURL("http://admin:yficfqn@www."+getBaseURLnotHttp()+"sys/getOrders?gID="+NavigationBase.pnumberorder+"&show_test=1");
		Log.info("***QA: http://admin:yficfqn@www."+getBaseURLnotHttp()+"sys/getOrders?gID="+NavigationBase.pnumberorder+"&show_test=1");
	}
	
	
	// жмаканье на "Завершить оформление"
	/*public void clickButtonSubmitOrder() throws Exception {
		try {
			
			buttonorderdetails.click(); 
			Log.info("жмаканье на Детали заказа");
			
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}*/
	
	

}
