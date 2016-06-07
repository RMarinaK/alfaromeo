package tehnosila.tehnosila_automation.AppManager;
/**
 * @author RasstriginaMK
 *
 */
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenShot {
	
	public static void takeScreenShot() throws Exception {
		WebDriver driver = ApplicationManager.getInstance().getWebDriverHelper().getDriver();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
		String destDir = "target/surefire-reports/screenshots";
		new File(destDir).mkdirs();
		String destFile = dateFormat.format(new Date()) + ".png";
 
        try {
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved <a href=../screenshots/" + destFile + ">Screenshot</a>");
	}
	
}
