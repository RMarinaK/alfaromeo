package tehnosila.tehnosila_automation.tests;

import java.io.File;


import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Action;
import tehnosila.tehnosila_automation.pages.Page_Cart;
import tehnosila.tehnosila_automation.pages.Page_Catalog;
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
 * @author DZhukov
 *
 */

// Тест акции "Скидка 5% при онлайн-оплате"
public class Action_Sale5 extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Action_Sale5.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"OnlineSelfDeliveryCardOnDelivery.xls",
                "OnlinSelfDeliveryCardOnDelivery", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String deliveryName) throws Exception{ //3
	
		Log.info("***QA: Акция Скидка 5% при онлайн-оплате");
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_Action pageaction = MyPageFactory.getPage(Page_Action.class);
		Page_Catalog pagecatalog = MyPageFactory.getPage(Page_Catalog.class);
		Page_Product pageproduct = MyPageFactory.getPage(Page_Product.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		pagetehnosila.clickActions();
		pageaction.clickActionSale5();
		pagecatalog.clickOpenSelfDeliveryDescription();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		pagecart.waitCartLoadingLayer();
		pagecart.clickButtonOrdering();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.clickFirstPoint();
		pageorder.findDiscountSize();
		pageorder.clickROnlineCardOnDelivery(paymentName);
		pageorder.assertDiscount();
		pageorder.clickButtonSubmitOrder();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		sysgetorders.assertPaymentName(paymentName);
		sysgetorders.assertDeliveryName(deliveryName);
		pagetehnosila.delCookies();  
	}
	
}
