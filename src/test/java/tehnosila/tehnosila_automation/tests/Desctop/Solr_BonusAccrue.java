package tehnosila.tehnosila_automation.tests.Desctop;

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
import tehnosila.tehnosila_automation.tests.TestBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author EDanilova
 *
 */
// Самовывоз оплата Наличными
public class Solr_BonusAccrue extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Solr_BonusAccrue.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"BonusAccrue.xls",
                "SelfDeliverCash", "Data");
        return(retObjArr);
    }

	public String getBaseURL(){
		return getBaseURL();
	}
	
	@Test (dataProvider = "DP1")
	public void BonusTest(String fio, String phone, String email, String paymentName, String paymentNameGO, String deliveryName, String cardNumber) throws Exception{

		Log.info("***QA: Самовывоз оплата Наличными Бонусы Solr_BonusAccrue");
	
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_1 +
				NavigationBase.psolrand + NavigationBase.psolrpriceValue_0_1000 + NavigationBase.psolrand 
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
		pageproduct.bonusSteal();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		commonmetods.getHTTPResponseCode();
		pagecart.clickButtonOrdering();
		commonmetods.getHTTPResponseCode();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.clickFirstPoint();
		pageorder.clickRCash(paymentName);
		pageorder.bonusSteal(0);
	//	commonmetods.bonusAmountCheck(NavigationBase.bonusAccCard, NavigationBase.bonusAccOffer0); 
		pageorder.clickButtonSetCard();
		pageorder.setOrderFromOrderContactCard(cardNumber);
		pageorder.clickButtonApplyCard();
		//pageorder.waitForInfoText();
		pageorder.getGiveCardNumber();
		commonmetods.bonusCardCheck(NavigationBase.bonusCard, cardNumber);
		pageorder.bonusSteal(1);
		//commonmetods.bonusAmountCheck(NavigationBase.bonusAccOffer0, NavigationBase.bonusAccOffer1);
		pageorder.clickButtonSubmitOrder();
		commonmetods.getHTTPResponseCode();
		app.getNavigationHelper().refreshPage();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		commonmetods.getHTTPResponseCode();

	//	sysgetorders.assertPaymentName(paymentNameGO);
	//	sysgetorders.assertDeliveryName(deliveryName);
		app.getNavigationHelper().delCookies();
	}
	
}
