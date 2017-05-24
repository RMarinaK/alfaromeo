package tehnosila.tehnosila_automation.pages;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;

public class Page_Catalog extends PagesBase{
	private static Logger Log = LoggerFactory.getLogger(Page_Catalog.class);
	//DSE: url to check page
//	protected String URL_MATCH = super.getBaseURL();
	
	@FindBy(xpath="//a[@id='open-self-delivery-description']/../../../../..")//a[@id='open-courier-description']
	private WebElement openselfdeliverydescription; // Первый товар самовывоз
	
	@FindBy(xpath="//a[@id='open-courier-description']/../../../../..")//a[@id='open-courier-description']
	private WebElement opencourierdescription; // Первый товар  с доставкой
	
	@FindBy(xpath="//div[@class='count']")//
	private WebElement count; // Количество товаров

	
	@Override
	void tryToOpen() {
	//	driver.get(this.URL_MATCH);
	}
	
	public void clickOpenSelfDeliveryDescription() throws Exception {
		try {
			openselfdeliverydescription.click(); 
			Log.info("Первый товар самовывоз");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         } 
	}	
	
	// жмаканье на Первый телевизор с доставкой
	public void clickOpenCourierDescription() throws Exception {
		try {
			opencourierdescription.click(); 
			Log.info("Первый товар с доставкой");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         } 
	}
	
	// Формирование ArrayList из кол-ва товаров по верхнеуровневым каталогам и подсчет суммы кол-ва товаров
	public void AllProducts() {
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='count']"));
		List<Integer> numbers = new ArrayList<>();
		for(WebElement count: items)
		{	
			String countstr = count.getText();
			int countint = Integer.valueOf(countstr);
			numbers.add(countint);
		}
		Log.info(" " + numbers);

		int[] myArray = {}; // конвертируем ArrayList в массив
		myArray = ArrayUtils.toPrimitive(numbers.toArray(new Integer[numbers.size()]));

		NavigationBase.presult = 0;
		for(int i = 0; i < myArray.length; i ++){
			int countint = Integer.valueOf(myArray[i]);
			NavigationBase.presult = NavigationBase.presult + countint;
		}
		Log.info("presult " + NavigationBase.presult);
	}
	
	// сравнение общего кол-ва товаров
		public void assertCount() {
			try {
				Assert.assertEquals(NavigationBase.presult, NavigationBase.ptotalnumber); 
				Log.info("***QA: Общее кол-во товаров "+ NavigationBase.ptotalnumber);
			}
		    catch(Exception e) {      
		    	Log.info("Element Not Found");     
	      //     ScreenShot.takeScreenShot();       
	        }      
		}  
	
}
