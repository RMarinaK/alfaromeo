/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Action;
import tehnosila.tehnosila_automation.pages.Page_ActionRassrochka;
import tehnosila.tehnosila_automation.pages.Page_Cart;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Page_Order;
import tehnosila.tehnosila_automation.pages.Page_OrderSuccess;
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
// Доставка оплата Наличными
public class Action_Installment extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Action_Installment.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"CourierCash.xls",
                "CourierCash", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String street, String house, String paymentName, String paymentNameGO, String deliveryName) throws Exception{ //3
		// авторизация
		Log.info("***QA: Акция Рассрочка");
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_Action pageaction = MyPageFactory.getPage(Page_Action.class);
		Page_ActionRassrochka pageactionrassrochka = MyPageFactory.getPage(Page_ActionRassrochka.class);
		Page_CatalogTv_i_videoTelevizoryTelevizory pagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Page_CatalogTv_i_videoTelevizoryTelevizoryID pagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		pagetehnosila.clickActions();
		pageaction.clickActionRassrochka();
		pageactionrassrochka.clickActionRassrochkaCatalog();
		pagecatalogtvivideotelevizorytelevizory.clickOpenSelfDeliveryDescription();
		pagecatalogtvivideotelevizorytelevizoryid.logItemprop();
		pagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		pagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		pagecart.clickRCourierDelivery();
		pagecart.waitCartLoadingLayer();
		pagecart.clickButtonOrdering();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.setMetro();
		pageorder.setOrderFormOrderAddressStreet(street);
		pageorder.setOrderFormOrderAddressHouse(house);
		pageorder.clickRCash(paymentName);
		pageorder.clickButtonSubmitOrder();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		sysgetorders.assertPaymentName(paymentNameGO);
		sysgetorders.assertDeliveryName(deliveryName);
		pagetehnosila.delCookies();
	}
	
}
