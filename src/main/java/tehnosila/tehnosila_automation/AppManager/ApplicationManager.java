/**
 * 
 */
package tehnosila.tehnosila_automation.AppManager;

/**
 * @author RasstriginaMK
 *
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import tehnosila.tehnosila_automation.WebDriver.WebDriverHelper;

public class ApplicationManager{

	private static ApplicationManager singleton;
	private WebDriverHelper webDriverHelper;
	private NavigationHelper navigationHelper;
	private GetDataHelper getDataHelper;
	private LoginHelper loginHelper;
	
	private Properties props;

	
	public static ApplicationManager getInstance() {
		if (singleton == null){
			singleton = new ApplicationManager();
		}
		return singleton;
	}

	public WebDriverHelper getWebDriverHelper() {
		if (webDriverHelper == null) {
			webDriverHelper = new WebDriverHelper(this);
		} 
		return webDriverHelper;
	}
	
	public NavigationHelper getNavigationHelper() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public void setProperties(Properties props) {
		this.props = props;
	}
	
	public String getProperty(String key){
		return props.getProperty(key);
	}

	public String getProperty(String key, String defaultValue){
		return props.getProperty(key,defaultValue);
	}

	public GetDataHelper getGetDataHelper() {
		if (getDataHelper == null) {
			getDataHelper = new GetDataHelper(this);
		}
		return getDataHelper;	}
	
	public LoginHelper getLoginHelper(){
		if (loginHelper == null) {
			loginHelper = new LoginHelper(this);
		}
		return loginHelper;	}
	

	
	
	
	public String getNowTime(){
		Date date = new Date();
		SimpleDateFormat stime = new SimpleDateFormat("HH:mm:ss");
		return stime.format(date);
	}
	
	public String getToday(String format){
		Date date = java.util.Calendar.getInstance().getTime();
		SimpleDateFormat ddmmyyyyFormat = new SimpleDateFormat(format);
		return ddmmyyyyFormat.format(date);
	}
	
	public String addDays(String dayNum, String dateFormat){
		Calendar cal = Calendar.getInstance();
		int num = Integer.parseInt(dayNum);
		cal.add(Calendar.DAY_OF_MONTH, num);	
		Date date = cal.getTime();
		SimpleDateFormat ddmmyyyyFormat = new SimpleDateFormat(dateFormat);
		return 	ddmmyyyyFormat.format(date);
	
	}

	
}