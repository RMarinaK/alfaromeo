package tehnosila.tehnosila_automation.tests;

import java.io.File;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.NavigationHelper;
import tehnosila.tehnosila_automation.pages.CommonMetods;
//import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Nameplates;
import tehnosila.tehnosila_automation.pages.Page_Action;
import tehnosila.tehnosila_automation.pages.Page_Actions;
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

// Тест акции "Экономить - просто!"
public class Action_SaveMoney extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Action_SaveMoney.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"CourierCash.xls",
                "CourierCash", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String street, String house, String paymentName, String paymentNameGO, String deliveryName) throws Exception{ 
		String discount_world = "Экономить - просто!";
		Log.info("***QA: Акция " + discount_world);
		
		app.getNavigationHelper().getURL(NavigationBase.papiparserpath + NavigationBase.pactiondiscount_worldurl + NavigationBase.papiend);
		app.getNavigationHelper().getTotalNumber();
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		pagetehnosila.getPageBase();
		Page_Actions pageactions = MyPageFactory.getPage(Page_Actions.class);
		Page_Catalog pagecatalog = MyPageFactory.getPage(Page_Catalog.class);
		Page_Product pageproduct = MyPageFactory.getPage(Page_Product.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		Page_Action pageaction = MyPageFactory.getPage(Page_Action.class);
		Nameplates nameplates = MyPageFactory.getPage(Nameplates.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		commonmetods.getHTTPResponseCode();
		pagetehnosila.clickActions();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		pageactions.clickActionSaveMoney();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		pagetehnosila.getPageActionCatalog();	
		pageaction.getTotalNubmer();
		pageaction.assertTotalNumber();
		pagetehnosila.clickActions();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		pageactions.clickActionSaveMoney();
		app.getNavigationHelper().refreshPage();
		commonmetods.getHTTPResponseCode();
		pagecatalog.clickOpenSelfDeliveryDescription();
		commonmetods.getHTTPResponseCode();
		pageaction.getCode();
		nameplates.checkPromowordDiscount();
		app.getNavigationHelper().refreshPage();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		commonmetods.getHTTPResponseCode();
		pagecart.waitCartLoadingLayer();
		pagecart.clickPromoCodeField();
		pagecart.setСartPromoСode(NavigationHelper.promocode);
		pagecart.clickButtonApplyBonus();
	//	app.getNavigationHelper().refreshPage();
		pagecart.clickButtonOrdering();
		commonmetods.getHTTPResponseCode();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.assertDiscount(NavigationBase.pcode);	
		pageorder.clickFirstPoint();
	//	pageorder.clickRCash(paymentName);
		pageorder.clickButtonSubmitOrder();
		commonmetods.getHTTPResponseCode();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		commonmetods.getHTTPResponseCode();
	//	sysgetorders.assertPaymentName(paymentNameGO);
		pagetehnosila.delCookies();
	}
	
}
