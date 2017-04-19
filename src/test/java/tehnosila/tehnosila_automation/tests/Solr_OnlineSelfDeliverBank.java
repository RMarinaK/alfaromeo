/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
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
// Самовывоз оплата Юридическое лицо
public class Solr_OnlineSelfDeliverBank extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Solr_OnlineSelfDeliverBank.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"OnlineSelfDeliverBank.xls",
                "OnlineSelfDeliverBank", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String paymentNameGO, String deliveryName,
			String inn, String kpp, String nameCompany, String companyAddress, String companyAddressFact, String companyAccount,
			String bik, String accountCorr, String bankName, String city) throws Exception{ 

		Log.info("***QA: Самовывоз онлайн оплата Юридическое лицо");
		
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_11 + NavigationBase.psolrand + NavigationBase.psolrpriceValue_0_1000 + NavigationBase.psolrand 
				+ NavigationBase.psolrpickupAvailabilityTyp + NavigationBase.psolrtail);
		app.getNavigationHelper().assertPRE();
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		pagetehnosila.getPage();
		Page_Product pageproduct = MyPageFactory.getPage(Page_Product.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		pageproduct.logItemprop();
		pageproduct.clickButtonBuy();
		pageproduct.clickPopupButtonToCart();
		pagecart.clickButtonOrdering();
		pageorder.setOrderFromOrderContactFio(fio);
		pageorder.setOrderFromOrderContactPhone(phone);
		pageorder.setOrderFromOrderContactEmail(email);
		pageorder.clickFirstPoint();
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
		pageorder.clickButtonSubmitOrder();
		pageordersuccess.assertTitle();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		sysgetorders.assertPaymentName(paymentNameGO);
		sysgetorders.assertDeliveryName(deliveryName);
		pagetehnosila.delCookies();
	}
	
}
