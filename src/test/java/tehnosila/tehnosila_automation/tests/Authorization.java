/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;

import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_PassportLogin;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
public class Authorization extends TestBase{
		
	//private static Logger Log = LoggerFactory.getLogger(Authorization.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"Authorization"+File.separator+"Authorization.xls",
                "Authorization", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String email, String password) throws Exception{ //3
		// авторизация
	//	Log.info("***QA: SmokeTests:loginTest() starteClientTaxAdddocumentnfd. Login with parameters: "+senderLogin+", "+password);
	//	app.getLoginHelper().login(senderLogin,password); 
//		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
	//	Assert.assertTrue(areamenu.isLogo()); // проверка наличия логотипчика
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_PassportLogin pagepasswordlogin = MyPageFactory.getPage(Page_PassportLogin.class);
		pagetehnosila.clickMenuCabinet();
		pagetehnosila.clickLogIn();
		pagepasswordlogin.setEmail(email);
		pagepasswordlogin.setPassword(password);
		pagepasswordlogin.clickButtonEnter();

	}
	
}
