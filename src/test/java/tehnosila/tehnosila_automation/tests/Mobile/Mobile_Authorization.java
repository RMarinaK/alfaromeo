package tehnosila.tehnosila_automation.tests.Mobile;

import java.io.File;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_PassportLogin;

/**
 * @author ZhukovDU
 *
 */

// Авторизация
public class Mobile_Authorization extends TestBase{
	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"Authorization"+File.separator+"Authorization.xls",
                "Authorization", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String email, String password) throws Exception{
		
		Mobile_Page_Tehnosila pagetehnosilamobile = MyPageFactory.getPage(Mobile_Page_Tehnosila.class);
		Mobile_Page_PassportLogin pagepasswordlogin = MyPageFactory.getPage(Mobile_Page_PassportLogin.class);
		
		app.getNavigationHelper().refreshPage();
		pagetehnosilamobile.clickMenuTrigger();
		pagetehnosilamobile.clickMenuCabinet();
		pagepasswordlogin.setEmail(email);
		pagepasswordlogin.setPassword(password);
		
	}
}
