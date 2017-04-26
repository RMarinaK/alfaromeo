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
	public static String psolrassortmentLevelValues_11 = "assortmentLevelValues_int%3A%2211%22";
	public static String psolrassortmentLevelValues_1 = "assortmentLevelValues_int%3A%221%22";
	public static String psolrand = "+AND+";
	public static String psolrpickupAvailabilityTyp = "pickupAvailabilityType_1_string%3A%22AVAILABLE%22";
	public static String psolrdeliveryAvailabilityTyp = "deliveryAvailabilityType_1_string%3A%22AVAILABLE%22";
	public static String psolrtail = "&wt=json&indent=true&fl=code_string";
	public static String psolrpriceValue_0_1000 = "priceValue_1_rub_double%3A%5B*+TO+"+1000+"%5D";
	public static String psolrpriceValue_3000_3500 = "priceValue_1_rub_double%3A%5B"+3000+"+TO+"+3500+"%5D";
	
	public static String psale;
	public static int pdiscountresult;
	public static int psalesize;
	public static String pheader;
	
	public static String papiparserpath = "http://hybris.tehnosila.ru/api/v2/ts/1/catalogs/parsePath?path=";
	public static String papiend = "&query=&currentPage=0&pageSize=32&fields=FULL";
	public static String pactionsale5url = "action/sale_5";
	public static String ptotatlnumber;
	public static String ptotalnumbersite;
	
	
	
}