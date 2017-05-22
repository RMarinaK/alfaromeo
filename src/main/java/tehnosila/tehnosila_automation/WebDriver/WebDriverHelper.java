/**
 * 
 */
package tehnosila.tehnosila_automation.WebDriver;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.security.UserAndPassword;

import tehnosila.tehnosila_automation.AppManager.ApplicationManager;
import tehnosila.tehnosila_automation.util.Browser;
import tehnosila.tehnosila_automation.util.PropertyLoader;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author RasstriginaMK
 *
 */

public class WebDriverHelper {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	private final ApplicationManager appManager;
	protected Browser browser;
	protected String gridHubUrl;
	protected String baseUrl;
	protected String cookies;
	
	public WebDriverHelper(ApplicationManager appManager){
		this.appManager = appManager;

			baseUrl = PropertyLoader.loadProperty("site.url");
			gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
			cookies = PropertyLoader.loadProperty("cookies");

			browser = new Browser();
			browser.setName(PropertyLoader.loadProperty("browser.name"));
			browser.setVersion(PropertyLoader.loadProperty("browser.version"));
			browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));
			

		DesiredCapabilities capabilities;
		switch (browser.getName()) {
		case "firefox":
			capabilities = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile(new File("src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"ff-profile"));			
			//DSE: do faster things like sendKeys
			profile.setEnableNativeEvents(false);
	        //DSE: solves the problem with active content that tried to be load from cache
			profile.setPreference("browser.cache.disk.enable", false);
			//profile.setPreference("browser.cache.memory.enable", false);
			//profile.setPreference("browser.cache.offline.enable", false);
			//profile.setPreference("network.http.use-cache", false);
//			try {
//			      File firebug = new File("firebug-1.12.6-fx.xpi");
//			      File firepath = new File("firepath-0.9.7-fx.xpi");
////			      File xpathChecker = new File("C:\\FFPlugins\\xpath_checker-0.4.4-fx.xpi");
//			      profile.addExtension(firebug);
//			      profile.addExtension(firepath);
//			      profile.setPreference("extensions.firebug.currentVersion", "1.12.6");// Avoid startup screen
////			      profile.addExtension(xpathChecker);
////			      profile.setPreference("extensions.xpath_checker.currentVersion", "0.4.4");
//			    } catch(IOException e) {
//			      e.printStackTrace();
//			    }

			capabilities.setCapability(CapabilityType.ENABLE_PROFILING_CAPABILITY,true);
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);  
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver","src/main/resources/IEDriverServer.exe");
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			break;
		case "chromemobile":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", "Google Nexus 5");  //
			Map<String, Object> chromeOptions = new HashMap<String, Object>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			break;
		case "chrometerminal":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");	
			
	/*		Map<String, String> mobileEmulation1 = new HashMap<String, String>();
			mobileEmulation1.put("deviceName", "Apple iPad");
			Map<String, Object> chromeOptions1 = new HashMap<String, Object>();
			chromeOptions1.put("mobileEmulation", mobileEmulation1);
			
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions1);
			capabilities.setCapability("rotatable", true);*/
			
			// КОСТЫЛЬ
			Map<String, Object> deviceMetrics = new HashMap<String, Object>();
			deviceMetrics.put("width", 1024);
			deviceMetrics.put("height", 840);
			deviceMetrics.put("pixelRatio", 3.0);
			Map<String, Object> mobileEmulation11 = new HashMap<String, Object>();

			mobileEmulation11.put("deviceMetrics", deviceMetrics);
			mobileEmulation11.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

			Map<String, Object> chromeOptions11 = new HashMap<String, Object>();
			chromeOptions11.put("mobileEmulation", mobileEmulation11);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions11);
		//	WebDriver driver = new ChromeDriver(capabilities);
			
			
			

		//	capabilities = DesiredCapabilities.chrome();
			
		//	capabilities.setCapability("deviceName", "GT-I9300");
		//	capabilities.setCapability("rotatable", "true");
	
			
			
			//	capabilities.setCapability("rotatable", "true");
	//		capabilities.setCapability(CapabilityType.ROTATABLE, true);
			
			// Try and rotate
	  //      Augmenter augmenter = new Augmenter();
	//        augmenter.addDriverAugmentation(CapabilityType.ROTATABLE, new AddRotatable());
	//       WebDriver augmentedDriver = augmenter.augment(driver);
	//       ((Rotatable) augmentedDriver).rotate(ScreenOrientation.LANDSCAPE);
	//       ScreenOrientation currentOrientation = ((Rotatable) augmentedDriver).getOrientation();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions desktopChromeOptions = new ChromeOptions();
			desktopChromeOptions.addArguments("excludeSwitches", "ignore-certificate-errors");
			capabilities.setCapability(ChromeOptions.CAPABILITY, desktopChromeOptions);
			break;	
		case "phantomjs":
			System.setProperty("phantomjs.binary.path", "src/main/resources/phantomjs.exe");
			capabilities = DesiredCapabilities.phantomjs();
			break;	
		case "opera":
			capabilities = DesiredCapabilities.opera();
			break;
		default:
			capabilities = DesiredCapabilities.firefox();
			break;
		}
		
		if(!gridHubUrl.equals("")) {
			driver = WebDriverFactory.getDriver(gridHubUrl, capabilities);
			driver.manage().window().maximize();
		}
		else
			driver = WebDriverFactory.getDriver(capabilities);
		
 		
 		/*An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an element
 		 * or elements if they are not immediately available. The default setting is 0.
 		 * Once set, the implicit wait is set for the life of the WebDriver object instance.*/
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("http://"+baseUrl);
	    driver.manage().deleteAllCookies();
	    try{			
	        File file = new File("src/main/resources/Cookies.data");							
	        FileReader fileReader = new FileReader(file);							
			BufferedReader Buffreader = new BufferedReader(fileReader);							
	        String strline;			
	        while((strline=Buffreader.readLine())!=null){	
	        StringTokenizer token = new StringTokenizer(strline, ";");		
	        while(token.hasMoreTokens()){					
	        String name = token.nextToken();					
	        String value = token.nextToken();					
	     //   String domain = token.nextToken();					
	   //     String path = token.nextToken();					
	 /*       Date expiry = null;					
	        		
	        String val;			
	        if(!(val=token.nextToken()).equals("null"))
			{		
	        	expiry = new Date(val);					
	        }		
	        Boolean isSecure = new Boolean(token.nextToken()).								
	        booleanValue();		*/
	        Cookie ck = new Cookie(name,value);	
	        System.out.println("***QA: Cookies: " + name + ";" + value);
	        driver.manage().addCookie(ck); // This will add the stored cookie to your current session					
	        }		
	        }		
	        }catch(Exception ex){					
	        ex.printStackTrace();			
	        }		
	   
	    
	/*    driver.get("http://"+baseUrl);
	    driver.manage().deleteAllCookies();
	    Cookie cookie = new Cookie("558hyblist54V22", "1");   
	     driver.manage().addCookie(cookie);*/


	//    (new WebDriverWait(driver, 1))
    //    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("easyXDM_flocktory_default7546_provider"));
	    
	    
	    try {
	    	Alert alertAuth = driver.switchTo().alert();
	    	if (alertAuth != null && alertAuth.getText().length() > 1) {
	    
	    	   alertAuth.authenticateUsing(new UserAndPassword("admin","yficfqn"));
		    return;
	    	}
	    } catch (NoAlertPresentException e) {

	    } 
	    

	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setBaseUrl(String url){
		this.baseUrl = url;
	}
	
	public String getBaseURL() {
		return "http://"+baseUrl;
	}
	
	public String getBaseURLnotHttp() {
		return baseUrl;
	}
	
	public ApplicationManager getAppManager() {
		return appManager;
	}

	public void stop() throws InterruptedException {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
	
	public void warmUp(int timeout){
		driver.get(getBaseURL());
		//THERE MUST IMPLICITY WAITS SHOOT

//		try {
//			wait(timeout);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
