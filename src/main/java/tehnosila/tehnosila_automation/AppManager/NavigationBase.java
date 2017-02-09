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

}
