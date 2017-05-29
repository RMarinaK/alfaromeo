package tehnosila.tehnosila_automation.pages;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.google.common.collect.Lists;

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

	private List<String> numbers = new ArrayList<>(); // массив кол-ва товаров по категориям
	private static int presult;
	
	private List<String> subcategories = new ArrayList<>(); // массив подкатегорий в catalog
	
	private List<Integer> numberList = new ArrayList<>();
	
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
	
	@FindBy(xpath="//div[@class='list']/a[4]/div[@class='count']")//
	private WebElement count1; // Количество товаров

	// Формирование ArrayList из кол-ва товаров по верхнеуровневым каталогам и подсчет суммы кол-ва товаров
	public void AllProducts() {
		List<WebElement> items = driver.findElements(By.xpath("//div[@class='count']"));
		for(WebElement count: items)
		{	
			String countstr = count.getAttribute("innerHTML");
		//	int countint = Integer.valueOf(countstr);
			numbers.add(countstr);
		}
		Log.info("***QA: Массив numbers " + numbers);
		
	}
	
	// Суммирование всех товаров
	public void summAllProducts() {
		AllProducts();
		presult = 0;
		for(String number : numbers) {
			int intnumber = Integer.parseInt(number);
			numberList.add(intnumber); 
			presult = presult + intnumber;
		}
		Log.info("***QA: summAllProducts in catalog " + presult);
	/*	for (int i = 0; i < numberList.size(); i++)
		 {
		 Integer x =  numberList.get(i);
		 presult = presult + x;
		 
		}
		Log.info("***QA: summAllProducts " + presult);*/
		
	/*	int[] myArray = {}; // конвертируем ArrayList в массив
		myArray = ArrayUtils.toPrimitive(numbers.toArray(new Integer[numbers.size()]));
		presult = 0;
		for(int i = 0; i < myArray.length; i ++){
			int countint = Integer.valueOf(myArray[i]);
			presult = presult + countint;
		}
		Log.info("***QA: summAllProducts " + presult);*/
	}
	
	// сравнение общего кол-ва товаров
	public void assertCount() {
		try {
			Assert.assertEquals(presult, NavigationBase.ptotatlnumber); 
			Log.info("***QA: Общее кол-во товаров "+ NavigationBase.ptotatlnumber);
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
	     //     ScreenShot.takeScreenShot();       
	    }      
	}  
	
	// проход по каждому подкаталогу catalogа
	public void AllSubcategories() {

		List<WebElement> items = driver.findElements(By.xpath("//div[@class='subcategories']/div[@class='list']/a"));
		for(WebElement count: items)
		{	
			String countstr = count.getAttribute("innerHTML");
		//	int countint = Integer.valueOf(countstr);
			numbers.add(countstr);
	
		/*	Pattern pattern = Pattern.compile("\\d+");
			String stringdiscountpsize = countstr; // мой пример строки
			Matcher matcher = pattern.matcher(stringdiscountpsize);
			int start = 0;
			StringBuilder builderpsale = new StringBuilder();
			while (matcher.find(start)) {
				String substringdiscountpsize = stringdiscountpsize.substring(matcher.start(), matcher.end());
				start = matcher.end();
				countstr =builderpsale.append(substringdiscountpsize).toString();
				Log.info("***QA: countstr "+ countstr);
				subcategories.add(countstr);
			}*/	
		}
		Log.info("***QA: Массив subcategories" + subcategories);
	}
	
	// Если количество товаров из апи ptotatlnumber < 40 0000, выводить ошибку
	public void checkPtotatlnumber() {		
		try {
			int checkptotatlnumber = Integer.valueOf(NavigationBase.ptotatlnumber);
			int checkpresult = Integer.valueOf(presult);
			if (checkptotatlnumber < 39000 || checkpresult < 40000) {
				throw new NullPointerException("Общее кол-во товаров по API <39000 или кол-во товаров в каталоге < 40 000");
			} else {
				try {
					double percentageofaverage = ((checkptotatlnumber + checkpresult)/2)*0.05;
					int intpercentageofaverage = (int)Math.ceil(percentageofaverage);
					Log.info("***QA: intpercentageofaverage " + intpercentageofaverage);
					int difference = Math.abs(checkpresult-checkptotatlnumber);
					Log.info("***QA: difference " + difference);
					if (difference > intpercentageofaverage) {
						throw new NullPointerException("Разница кол-ва товаров в каталоге и в API < 5%");
					}
				}
				catch(NullPointerException e) {     
			    	 throw e; 
			    }      
			}
		}
	    catch(NullPointerException e) {     
	    	 throw e; 
	    }      
	}  
	
	// Если количество товаров из апи presult < 40 0000, выводить ошибку
	public void checkPresult() {		
		try {
			if (presult < 40000) { throw new NullPointerException("Общее кол-во товаров на сайте " + presult + " < 40 000");} 
		}
	    catch(NullPointerException e) {     
	    	 throw e;     
	    }      
	}  
	
	
	
}
