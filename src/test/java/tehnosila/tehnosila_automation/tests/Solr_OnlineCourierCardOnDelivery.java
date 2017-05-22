/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Cart;
import tehnosila.tehnosila_automation.pages.Page_Order;
import tehnosila.tehnosila_automation.pages.Page_OrderSuccess;
import tehnosila.tehnosila_automation.pages.Page_Product;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.Sys_getOrders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
// Доставка онлайн оплата банковской картой
public class Solr_OnlineCourierCardOnDelivery extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Solr_OnlineCourierCardOnDelivery.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"OnlineCourierCardOnDelivery.xls",
                "OnlineCourierCardOnDelivery", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email,  String street, String house, String paymentName, String deliveryName) throws Exception{

		Log.info("***QA: Доставка онлайн оплата банковской картой Solr_OnlineCourierCardOnDelivery");
			
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_17 + NavigationBase.psolrand + NavigationBase.psolrpriceValue_0_1000 + NavigationBase.psolrand 
				+ NavigationBase.psolrdeliveryAvailabilityTyp + NavigationBase.psolrtail);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		commonmetods.getHTTPResponseCode();
		app.getNavigationHelper().getCodeString();
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		pagetehnosila.getPage();
		commonmetods.getHTTPResponseCode();
		Page_Product pageproduct = MyPageFactory.getPage(Page_Product.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		pageproduct.logItemprop();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		commonmetods.getHTTPResponseCode();
		pagecart.clickRCourierDelivery();
		pagecart.waitCartLoadingLayer();
		pagecart.clickButtonOrdering();
		commonmetods.getHTTPResponseCode();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.setMetro();
		pageorder.setOrderFormOrderAddressStreet(street);
		pageorder.setOrderFormOrderAddressHouse(house);
		pageorder.clickROnlineCardOnDelivery(paymentName);
		pageorder.clickButtonSubmitOrder();
		commonmetods.getHTTPResponseCode();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		commonmetods.getHTTPResponseCode();
		sysgetorders.assertPaymentName(paymentName);
		sysgetorders.assertDeliveryName(deliveryName);
	}
	
}
