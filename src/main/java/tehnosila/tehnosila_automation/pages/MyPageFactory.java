/**
 * 
 */
package tehnosila.tehnosila_automation.pages;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tehnosila.tehnosila_automation.AppManager.ApplicationManager;

/**
 * @author RasstriginaMK
 *
 */

public class MyPageFactory {
	private static Logger Log = LoggerFactory.getLogger(MyPageFactory.class);

    public static <T extends PagesBase> T getPage(Class<T> pageClass) throws  Error {
        WebDriver driver = ApplicationManager.getInstance().getWebDriverHelper().getDriver();
        T page = instantiatePage(driver, pageClass);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), page);
        page.driver = driver;
        if (!page.isOnThisPage()) {
            page.tryToOpen();
            if (! page.isOnThisPage()) {
                throw new Error("Can't open page " + pageClass);
            }
        }
        page.handleAlertIfExists();
        page.handlePopUpIfExists();
        //page.handleOnPageIfExists();
        
        Log.info("***QA: We are on the {} page", driver.getCurrentUrl());
        return page;
    }

    public static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


}
