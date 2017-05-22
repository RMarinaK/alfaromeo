package tehnosila.tehnosila_automation.tests;

import java.io.File;

//import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.NavigationHelper;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
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

// Тест акции "Честные цены"
public class Action_FairPrice extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Action_FairPrice.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"CourierCash.xls",
                "CourierCash", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String street, String house, String paymentName, String paymentNameGO, String deliveryName) throws Exception{ //3
		String chestnye_ceny = "ЧЕСТНЫЕ ЦЕНЫ";
		String cheaperlink = "http://www.eldorado.ru/cat/detail/71215852/";
		Log.info("***QA: Акция " + chestnye_ceny);
		
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
	//	pagetehnosila.clickActions();
		commonmetods.getHTTPResponseCode();
		app.getNavigationHelper().refreshPage();
		pageactions.clickActionFairPrice();
		commonmetods.getHTTPResponseCode();
		app.getNavigationHelper().refreshPage();
		commonmetods.scrolling();
		pageaction.clickActionCatalogItem();
		commonmetods.getHTTPResponseCode();
		pageaction.clickCatalogItemInCategory();
		commonmetods.getHTTPResponseCode();
		pagecatalog.clickOpenSelfDeliveryDescription();
		commonmetods.getHTTPResponseCode();
		pageproduct.clickButtonBuyCheaper();
		pageaction.getСheaperPrice();
		pageproduct.setActonBuyCheaperFio(fio);
		pageproduct.setActonBuyCheaperPhone(phone);
		pageproduct.setActonBuyCheaperEmail(email);
		pageproduct.setActonBuyCheaperLink(cheaperlink);
		pageproduct.setActonBuyCheaperPrice(NavigationHelper.cheaperprice);
		pageproduct.clickButtonSubmit();
		pageproduct.clickButtonClose();
		app.getNavigationHelper().refreshPage();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		pagecart.waitCartLoadingLayer();
		commonmetods.getHTTPResponseCode();
		pagecart.clickPromoCodeField();
		pagecart.clickButtonOrdering();
		commonmetods.getHTTPResponseCode();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
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
