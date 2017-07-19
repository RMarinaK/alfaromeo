package tehnosila.tehnosila_automation.pages.Desctop;
import java.util.ArrayList;
import java.util.List;



import org.openqa.selenium.By;

/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.PagesBase;

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

	//private List<String> numbersSubcategories = new ArrayList<>(); // массив кол-ва товаров по категориям
	private static int presult;
	//private List<String> subcategories = new ArrayList<>(); // массив подкатегорий в catalog
	private List<Integer> numberList = new ArrayList<>();
	
	@Override
	protected
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

	// Формирование ArrayList из кол-ва товаров по верхнеуровневым каталогам
	public void AllProducts(List<String> list) {
		List<WebElement> itemsCount = driver.findElements(By.xpath("//div[@class='count']"));
		for(WebElement count: itemsCount)
		{	
			String countstr = count.getAttribute("innerHTML");
			list.add(countstr);
		}
		Log.info("***QA: Массив numbers " + list);
		
	}

	// Суммирование всех товаров из массива numbers
	public void summAllProducts(List<String> list) {
		AllProducts(list);
		presult = 0;
		for(String number : list) {
			int intnumber = Integer.parseInt(number);
			numberList.add(intnumber); 
			presult = presult + intnumber;
		}
		Log.info("***QA: summAllProducts in catalog " + presult);
	}
		
	// Общее кол-во товаров по API < maxnumber или кол-во товаров в каталоге < maxnumber или Разница кол-ва товаров в каталоге и в API < 10%
	public void checkPtotatlnumber(int maxnumber) {		
		try {
			int checkptotatlnumber = Integer.valueOf(NavigationBase.ptotatlnumber);
			int checkpresult = Integer.valueOf(presult);
			if (checkptotatlnumber < maxnumber || checkpresult < maxnumber) {
				throw new NullPointerException("Общее кол-во товаров по API < " + maxnumber + " или кол-во товаров в каталоге < " + maxnumber);
			} else {
				try {
					double percentageofaverage = ((checkptotatlnumber + checkpresult)/2)*0.1;
					int intpercentageofaverage = (int)Math.ceil(percentageofaverage);
					Log.info("***QA: percentage of average " + intpercentageofaverage);
					int difference = Math.abs(checkpresult-checkptotatlnumber);
					Log.info("***QA: difference " + difference);
					if (difference > intpercentageofaverage) {
						throw new NullPointerException("Разница кол-ва товаров в каталоге и в API < 10%");
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
	
	@FindBy(xpath = "//h1") 
	private WebElement header; // заголовок на страницах
	
	//Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
	// проход по каждому подкаталогу catalogа и формирование массива subcategories
	public void AllSubcategories(List<String> list) {
		/*List<WebElement> itemsHtef = driver.findElements(By.xpath("//div[@class='subcategories']/div[@class='list']/a"));
		for(WebElement hrefItems: itemsHtef)
		{	
			String hrefstr = hrefItems.getAttribute("href");
			subcategories.add(hrefstr);
			Log.info("***QA: Массив subcategories" + hrefstr);
		}*/
		List<WebElement> itemsHtef = driver.findElements(By.xpath("//div[@class='subcategories']/div[@class='list']/a"));
		for (int i =1; i<itemsHtef.size()+1; i++) {
			WebElement we = driver.findElement(By.xpath("//div[@class='subcategories']/div[@class='list']/a["+i+"]"));
			
			Log.info("Ссылка на категорию " + we.getAttribute("href"));
			we.click();
			Log.info("title " + header.getText());
			summAllProducts(list);

			driver.navigate().back();
			/*	driver.findElement(By.xpath("//div[@class='subcategories']/div[@class='list']/a["+i+"]")).click();
				Log.info(" " + driver.findElement(By.xpath("//div[@class='subcategories']/div[@class='list']/a["+i+"]")).getText());
				summAllProducts(list);
				driver.navigate().back();*/
			    
			  }
		
	}

	
	
	
}
