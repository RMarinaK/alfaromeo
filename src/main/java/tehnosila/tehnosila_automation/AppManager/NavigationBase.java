/**
 * 
 */
package tehnosila.tehnosila_automation.AppManager;

/**
 * @author RasstriginaMK
 *
 */
import org.openqa.selenium.WebDriver;

public class NavigationBase {

	protected final ApplicationManager appManager;
	protected WebDriver driver;
	
	public NavigationBase (ApplicationManager appManager){
		this.appManager = appManager;
		driver = appManager.getWebDriverHelper().getDriver();
		
	}
	
	public static String pnumberorder;
	public static String prcash;
	public static String prcardondelivery;
	public static String prcreditinstore;
	public static String prbank;
	public static String psolrarticle;
	public static String psolrurl = "http://10.9.1.226:8983/solr/master_technosila_Product_default/select?q=";
	public static String psolrassortmentLevelValues_17 = "assortmentLevelValues_int%3A%2217%22";
	public static String psolrand = "+AND+";
	public static String psolrpickupAvailabilityTyp = "pickupAvailabilityType_1_string%3A%22AVAILABLE%22";
	public static String psolrdeliveryAvailabilityTyp = "deliveryAvailabilityType_1_string%3A%22AVAILABLE%22";
	public static String psolrtail = "&wt=json&indent=true&fl=code_string";
	public static String psolrpriceValue = "priceValue_1_rub_double%3A%5B*+TO+"+1000+"%5D";
	
	
}