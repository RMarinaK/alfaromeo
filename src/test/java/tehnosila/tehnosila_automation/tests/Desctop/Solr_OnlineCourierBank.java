/**
 * 
 */
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
 * @author MRasstrigina
 *
 */
// Доставка онлайн оплата Юридическое лицо
public class Solr_OnlineCourierBank extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Solr_OnlineCourierBank.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"OnlineCourierBank.xls",
                "OnlineCourierBank", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String street, String house, String paymentName, String paymentNameGO, String deliveryName,
			String inn, String kpp, String nameCompany, String companyAddress, String companyAddressFact, String companyAccount,
			String bik, String accountCorr, String bankName, String city) throws Exception{ 

		Log.info("***QA: Доставка онлайн оплата Юридическое лицо Solr_OnlineCourierBank");

		
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_17 + NavigationBase.psolrand + 
				NavigationBase.psolrpriceValue_0_1000 + NavigationBase.psolrand + NavigationBase.psolrdeliveryAvailabilityTyp + NavigationBase.psolrtail);
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
		pageorder.clickRBank(paymentName);
		pageorder.setOrderFormOrderContactCompanyInn(inn);
		pageorder.setOrderFormOrderContactCompanyKpp(kpp);
		pageorder.setOrderFormOrderContactNameCompany(nameCompany);
		pageorder.setOrderFormOrderContactCompanyAddress(companyAddress);
		pageorder.setOrderFormOrderContactCompanyAddressFact(companyAddressFact);
		pageorder.setOrderFormOrderContactCompanyAccount(companyAccount);
		pageorder.setOrderFormOrderContactCompanyBik(bik);
		pageorder.setOrderFormOrderContactCompanyAccountCorr(accountCorr);
		pageorder.setOrderFormOrderContactCompanyBankName(bankName);
		pageorder.setOrderFormOrderContactCompanyCity(city);
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
