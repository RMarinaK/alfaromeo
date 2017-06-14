/**
 * 
 */
package tehnosila.tehnosila_automation.tests.Desctop;

import java.io.File;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Cart;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Order;
import tehnosila.tehnosila_automation.pages.Desctop.Page_OrderSuccess;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Product;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.Sys_getOrders;
import tehnosila.tehnosila_automation.tests.TestBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
// Самовывоз оплата в кредит
public class Solr_SelfDeliveryCreditInStore extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Solr_SelfDeliveryCreditInStore.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"SelfDeliveryCreditInStore.xls",
                "SelfDeliveryCreditInStore", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String paymentNameGO, String deliveryName) throws Exception{ 
		Log.info("***QA: Самовывоз оплата в кредит Solr_SelfDeliveryCreditInStore");
		
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_1 +
				NavigationBase.psolrand +  NavigationBase.psolrpriceValue_3000_3500 + NavigationBase.psolrand 
				+ NavigationBase.psolrpickupAvailabilityTyp + NavigationBase.psolrtail);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		commonmetods.getHTTPResponseCode();
		app.getGetDataHelper().getCodeString();
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		pagetehnosila.getPage();
		commonmetods.getHTTPResponseCode();
		Page_Product pageproduct = MyPageFactory.getPage(Page_Product.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		pageproduct.logItemprop();
		app.getNavigationHelper().refreshPage();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		pagecart.clickButtonOrdering();
		commonmetods.getHTTPResponseCode();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.clickFirstPoint();
		pageorder.clickRCreditInStore(paymentName);
		commonmetods.getCookieSession();
		pageorder.clickButtonSubmitOrder();
		commonmetods.getCookieSession();
		commonmetods.getHTTPResponseCode();
		app.getNavigationHelper().refreshPage();
		commonmetods.getCookieSession();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		commonmetods.getHTTPResponseCode();
		sysgetorders.assertPaymentName(paymentNameGO);
		sysgetorders.assertDeliveryName(deliveryName);
		app.getNavigationHelper().delCookies();
	}
	
}
