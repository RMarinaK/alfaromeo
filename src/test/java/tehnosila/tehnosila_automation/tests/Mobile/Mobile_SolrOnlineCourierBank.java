package tehnosila.tehnosila_automation.tests.Mobile;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.Mobile_Page_Cart;
import tehnosila.tehnosila_automation.pages.Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Mobile_Page_Order;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.pages.Page_OrderSuccess;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.Sys_getOrders;
import tehnosila.tehnosila_automation.tests.TestBase;

/**
 * @author ZhukovDU
 *
 */

//Доставка онлайн оплата банковской картой
public class Mobile_SolrOnlineCourierBank extends TestBase{

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
		Log.info("***QA: Доставка онлайн оплата Юридическое лицо OnlineCourierBank");
		
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		Mobile_Page_Order mobilepageorder = MyPageFactory.getPage(Mobile_Page_Order.class);
		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
		Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID mobilepagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Mobile_Page_Cart mobilepagecart = MyPageFactory.getPage(Mobile_Page_Cart.class);
			
		app.getNavigationHelper().getURL(NavigationBase.psolrurl + NavigationBase.psolrassortmentLevelValues_17 + NavigationBase.psolrand + 
				NavigationBase.psolrpriceValue_0_1000 + NavigationBase.psolrand + NavigationBase.psolrdeliveryAvailabilityTyp + NavigationBase.psolrtail);

		commonmetods.getHTTPResponseCode();
		app.getGetDataHelper().getCodeString();
		pagetehnosila.getPage();
		commonmetods.getHTTPResponseCode();
		Assert.assertTrue(areamenu.isLogo());
		app.getNavigationHelper().refreshPage();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		commonmetods.WaitingMobile();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		mobilepagecart.clickRCourierDelivery();
		mobilepagecart.waitCartLoadingLayer();
		mobilepagecart.clickButtonOrder();
		mobilepageorder.setOrderFromOrderContactFio(fio);
		mobilepageorder.setOrderFromOrderContactPhone(phone);
		mobilepageorder.setOrderFromOrderContactEmail(email);
		mobilepageorder.setMetro();
		mobilepageorder.setOrderFormOrderAddressStreet(street);
		mobilepageorder.setOrderFormOrderAddressHouse(house);
		mobilepageorder.clickRBank(paymentName);
		mobilepageorder.setOrderFormOrderContactCompanyInn(inn);
		mobilepageorder.setOrderFormOrderContactCompanyKpp(kpp);
		mobilepageorder.setOrderFormOrderContactNameCompany(nameCompany);
		mobilepageorder.setOrderFormOrderContactCompanyAddress(companyAddress);
		mobilepageorder.setOrderFormOrderContactCompanyAddressFact(companyAddressFact);
		mobilepageorder.setOrderFormOrderContactCompanyAccount(companyAccount);
		mobilepageorder.setOrderFormOrderContactCompanyBik(bik);
		mobilepageorder.setOrderFormOrderContactCompanyAccountCorr(accountCorr);
		mobilepageorder.setOrderFormOrderContactCompanyBankName(bankName);
		mobilepageorder.setOrderFormOrderContactCompanyCity(city);
		commonmetods.getCookieSession();
		mobilepageorder.clickButtonSubmitOrder();
		commonmetods.getCookieSession();
		commonmetods.getHTTPResponseCode();
		app.getNavigationHelper().refreshPage();
		commonmetods.getCookieSession();
		pageordersuccess.getOrders();
		sysgetorders.assertOrders();
		commonmetods.getHTTPResponseCode();
		sysgetorders.assertPaymentName(paymentNameGO);
		sysgetorders.assertDeliveryName(deliveryName);
	}
}
