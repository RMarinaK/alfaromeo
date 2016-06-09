/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.pages.Page_Cart;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Page_Order;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
public class Smoke extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Smoke.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"SmokeTests.xls",
                "SmokeTests", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email) throws Exception{ //3
		// авторизация
	//	Log.info("***QA: SmokeTests:loginTest() starteClientTaxAdddocumentnfd. Login with parameters: "+senderLogin+", "+password);
	//	app.getLoginHelper().login(senderLogin,password); 
//		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
	//	Assert.assertTrue(areamenu.isLogo()); // проверка наличия логотипчика
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_CatalogTv_i_videoTelevizoryTelevizory pagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Page_CatalogTv_i_videoTelevizoryTelevizoryID pagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		pagetehnosila.clickLEDTV();
		pagecatalogtvivideotelevizorytelevizory.getWaitPage();
		pagecatalogtvivideotelevizorytelevizory.clickItemInFowrapFirst();
		pagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		pagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		pagecart.clickButtonOrdering();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.clickRadioButtonDelivery();
		pageorder.clickButtonSubmitOrder();
	}
	
}
