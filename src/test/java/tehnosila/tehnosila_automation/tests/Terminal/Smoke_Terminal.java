/**
 * 
 */
package tehnosila.tehnosila_automation.tests.Terminal;


import java.io.File;


import tehnosila.tehnosila_automation.pages.CommonMetods;

import tehnosila.tehnosila_automation.pages.MyPageFactory;

import tehnosila.tehnosila_automation.pages.Term_Page_Cart;
import tehnosila.tehnosila_automation.pages.Term_Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Term_Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Term_Page_Order;
import tehnosila.tehnosila_automation.pages.Term_Page_Tehnosila;
import tehnosila.tehnosila_automation.tests.TestBase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
public class Smoke_Terminal extends TestBase{
		
//	private static Logger Log = LoggerFactory.getLogger(Smoke_Terminal.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"SmokeTests.xls",
                "SmokeTests", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String deliveryName) throws Exception{ //3
		// авторизация
	//	Log.info("***QA: SmokeTests:loginTest() starteClientTaxAdddocumentnfd. Login with parameters: "+senderLogin+", "+password);
	//	app.getLoginHelper().login(senderLogin,password); 
		
		
	//	Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
	//	Assert.assertTrue(areamenu.isLogo()); // проверка наличия логотипчика
		
		Term_Page_Tehnosila termpagetehnosila = MyPageFactory.getPage(Term_Page_Tehnosila.class);
		CommonMetods CommonMetods = MyPageFactory.getPage(CommonMetods.class);
		Term_Page_CatalogTv_i_videoTelevizoryTelevizory termpagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Term_Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Term_Page_CatalogTv_i_videoTelevizoryTelevizoryID termpagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Term_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Term_Page_Order termpageorder = MyPageFactory.getPage(Term_Page_Order.class);
		
		Term_Page_Cart termpagecart = MyPageFactory.getPage(Term_Page_Cart.class);
		termpagetehnosila.clickTVAudioVideo();
		termpagetehnosila.clickTV();
		termpagetehnosila.clickLEDTV();
		
    	termpagecatalogtvivideotelevizorytelevizory.clickItemInFowrapFirst();
		
		termpagecatalogtvivideotelevizorytelevizoryid.clickAddToCart();
		// КОСТЫЛЬ
		CommonMetods.RefreshTerm();
		termpagecatalogtvivideotelevizorytelevizoryid.clickButtonToCart();
        
		termpagecart.clickButtonOrder();
		termpageorder.setPersonal();
		termpageorder.setOrderFromOrderContactFio(fio);
		termpageorder.setOrderFromOrderContactPhone(phone);
		termpageorder.setOrderFromOrderContactEmail(email);
		//termpageorder.clickButtonSubmitOrder();
		//termpageorder.closePrintWindow();

	}
}
