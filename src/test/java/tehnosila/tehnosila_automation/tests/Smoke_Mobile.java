/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.Mobile_Page_Cart;
import tehnosila.tehnosila_automation.pages.Mobile_Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Mobile_Page_Order;
import tehnosila.tehnosila_automation.pages.Mobile_Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
public class Smoke_Mobile extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Smoke_Mobile.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"SmokeTests.xls",
                "SmokeTests", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email) throws IOException, InterruptedException{ //3
		// авторизация
	//	Log.info("***QA: SmokeTests:loginTest() starteClientTaxAdddocumentnfd. Login with parameters: "+senderLogin+", "+password);
	//	app.getLoginHelper().login(senderLogin,password); 
		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
		Assert.assertTrue(areamenu.isLogo()); // проверка наличия логотипчика
		
		Mobile_Page_Tehnosila mobilepagetehnosila = MyPageFactory.getPage(Mobile_Page_Tehnosila.class);
		CommonMetods CommonMetods = MyPageFactory.getPage(CommonMetods.class);
		Mobile_Page_CatalogTv_i_videoTelevizoryTelevizory mobilepagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID mobilepagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Mobile_Page_Cart mobilepagecart = MyPageFactory.getPage(Mobile_Page_Cart.class);
		
		Mobile_Page_Order mobilepageorder = MyPageFactory.getPage(Mobile_Page_Order.class);
		mobilepagetehnosila.clickMenuTrigger();
		CommonMetods.Waiting();
		mobilepagetehnosila.clickCatalog();
		mobilepagetehnosila.clickTVAudioVideo();
		mobilepagetehnosila.clickTV();
		mobilepagetehnosila.clickLEDTV();
		
	//	mobilepagecatalogtvivideotelevizorytelevizory.getWaitPage();
		mobilepagecatalogtvivideotelevizorytelevizory.clickItemInFowrapFirst();
		
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		CommonMetods.WaitingMobile();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		
		mobilepagecart.clickButtonOrder();

		mobilepageorder.setOrderFromOrderContactFio(fio);
		mobilepageorder.setOrderFromOrderContactPhone(phone);
		mobilepageorder.setOrderFromOrderContactEmail(email);
		mobilepageorder.clickRadioButtonDelivery();
		mobilepageorder.clickButtonSubmitOrder();	
	}
}
