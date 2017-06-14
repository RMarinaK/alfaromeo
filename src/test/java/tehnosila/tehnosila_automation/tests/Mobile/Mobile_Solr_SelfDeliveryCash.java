package tehnosila.tehnosila_automation.tests.Mobile;

import java.io.File;
import org.testng.Assert;
import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_Cart;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_Order;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.pages.Desctop.Page_OrderSuccess;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.Sys_getOrders;
import tehnosila.tehnosila_automation.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */

//Самовывоз оплата наличными
public class Mobile_Solr_SelfDeliveryCash extends TestBase{
	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"SmokeTests.xls",
                "SmokeTests", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String deliveryName) throws Exception{
		Log.info("***QA: Самовывоз оплата в кредит Mobile_Solr_SelfDeliveryCash");
		
		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID mobilepagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Mobile_Page_Cart mobilepagecart = MyPageFactory.getPage(Mobile_Page_Cart.class);
		Mobile_Page_Order mobilepageorder = MyPageFactory.getPage(Mobile_Page_Order.class);
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_1 +
				NavigationBase.psolrand + NavigationBase.psolrpriceValue_0_1000 + NavigationBase.psolrand 
				+ NavigationBase.psolrpickupAvailabilityTyp + NavigationBase.psolrtail);
		commonmetods.getHTTPResponseCode();
		app.getGetDataHelper().getCodeString();
		pagetehnosila.getPage();
		commonmetods.getHTTPResponseCode();
		Assert.assertTrue(areamenu.isLogo());
		app.getNavigationHelper().refreshPage();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		commonmetods.WaitingMobile();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		mobilepagecart.clickButtonOrder();
		mobilepageorder.setOrderFromOrderContactFio(fio);
		mobilepageorder.setOrderFromOrderContactPhone(phone);
		mobilepageorder.setOrderFromOrderContactEmail(email);
		mobilepageorder.clickRadioButtonDelivery();
		mobilepageorder.clickFirstDeliveryButton();
		mobilepageorder.clickButtonSubmitOrder();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		commonmetods.getHTTPResponseCode();
		sysgetorders.assertPaymentName(paymentName);
		sysgetorders.assertDeliveryName(deliveryName);
	}
}
