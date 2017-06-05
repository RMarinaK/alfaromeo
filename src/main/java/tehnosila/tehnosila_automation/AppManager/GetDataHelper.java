package tehnosila.tehnosila_automation.AppManager;
/**
 * @author RasstriginaMK
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * driver is available
 * appManager is available
 *
 */

public class GetDataHelper extends NavigationHelper{
	
	private static Logger Log = LoggerFactory.getLogger(GetDataHelper.class);
	
	public GetDataHelper (ApplicationManager appManager){
		super(appManager);
	}
	
	// вытягивание текста из тега pre
	public String getPre(){ 
		return driver.findElement(By.xpath("//pre")).getText();
	}	
		
	// вытягивание артикула товара из solr
	public void getCodeString() {
		String stringpre = getPre();
		int start = stringpre.indexOf("\"code_string\":\"");
		NavigationBase.psolrarticle = "";
		if (start > 0) {
			String productpart = stringpre.substring(start + 15);
			NavigationBase.psolrarticle = productpart.substring(0, productpart.indexOf("\"}"));
		}
		Log.info("***QA: Артикул товара: "+ NavigationBase.psolrarticle);
	}
		
	// вытягивание кол-ва товаров из апи
	public void getTotalNumber() {
		String stringpre = getPre();
		int start = stringpre.indexOf("totalResults\" : ");
		ptotatlnumber = "";
		if (start > 0) {
			String productpart = stringpre.substring(start + 16);
			ptotatlnumber = productpart.substring(0, productpart.indexOf("}")-3);
		}
		Log.info("***QA: ptotatlnumber '"+ ptotatlnumber +"'");
	}	
		
	// вытягивание количество товаров из апи technosilaProductCatalog/Online
	public void getCatalogOnline() {
		String stringpre = getPre();
	//	int start1 = stringpre.indexOf("totalNumber\" :");
		int start1 = stringpre.indexOf("totalResults\" :");
		ptotatlnumber = "";
		if (start1 > 0) {
			String productpart = stringpre.substring(start1);
			ptotatlnumber = productpart.substring(0, productpart.indexOf("}"));		
		}
		Log.info("***QA: ptotatlnumber '"+ ptotatlnumber +"'");

	}	
	
	// вытягивание количество товаров из getTotalNumberCatalogOnline
	public void getTotalNumberCatalogOnline() {
		getCatalogOnline();
		Pattern pattern = Pattern.compile("\\d+");
		String stringdiscountpsize = ptotatlnumber; // мой пример строки
		Matcher matcher = pattern.matcher(stringdiscountpsize);
		int start = 0;
		StringBuilder builderpsale = new StringBuilder();
		while (matcher.find(start)) {
			String substringdiscountpsize = stringdiscountpsize.substring(matcher.start(), matcher.end());
			start = matcher.end();
			ptotatlnumber =builderpsale.append(substringdiscountpsize).toString();
			Log.info("***QA: totalNumber "+ ptotatlnumber);
		}
	}
	
}
