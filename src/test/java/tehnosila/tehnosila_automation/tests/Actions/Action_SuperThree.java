package tehnosila.tehnosila_automation.tests.Actions;

import java.io.File;

import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Action;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Actions;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Cart;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Catalog;
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
 * @author DZhukov
 *
 */

// Тест Акции "Супертройка"
public class Action_SuperThree extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Action_SuperThree.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"CourierCash.xls",
                "CourierCash", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String street, String house, String paymentName, String paymentNameGO, String deliveryName) throws Exception{ //3
	
		Log.info("***QA: Акция Супертройка");
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_Actions pageactions = MyPageFactory.getPage(Page_Actions.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		Page_Catalog pagecatalog = MyPageFactory.getPage(Page_Catalog.class);
		Page_Product pageproduct = MyPageFactory.getPage(Page_Product.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		Page_Action pageaction = MyPageFactory.getPage(Page_Action.class);
		commonmetods.getHTTPResponseCode();
		pagetehnosila.clickActions();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		pageactions.clickActionSuperThree();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		commonmetods.scrollPage();
		pageaction.clickActionCatalog();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		pagecatalog.clickOpenSelfDeliveryDescription();
		commonmetods.getHTTPResponseCode();
		pageproduct.logItemprop();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		commonmetods.getHTTPResponseCode();
		pagecart.waitCartLoadingLayer();
		pagecart.clickButtonOrdering();
		commonmetods.getHTTPResponseCode();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.clickFirstPoint();
	//	pageorder.clickRCash(paymentName);
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
	//	sysgetorders.assertPaymentName(paymentNameGO);
	}
	
}
