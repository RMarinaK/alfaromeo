/**
 * 
 */
package tehnosila.tehnosila_automation.tests.Old;

import java.io.File;


import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Cart;
import tehnosila.tehnosila_automation.pages.Desctop.Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Desctop.Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Order;
import tehnosila.tehnosila_automation.pages.Desctop.Page_OrderSuccess;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.Sys_getOrders;
import tehnosila.tehnosila_automation.tests.TestBase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
// Самовывоз оплата Юридическое лицо
public class OnlineSelfDeliverBank extends TestBase{
		
//	private static Logger Log = LoggerFactory.getLogger(OnlineSelfDeliverBank.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"OnlineSelfDeliverBank.xls",
                "OnlineSelfDeliverBank", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String paymentNameGO, String deliveryName,
			String inn, String kpp, String nameCompany, String companyAddress, String companyAddressFact, String companyAccount,
			String bik, String accountCorr, String bankName, String city) throws Exception{ //
		// авторизация
	//	Log.info("***QA: SmokeTests:loginTest() starteClientTaxAdddocumentnfd. Login with parameters: "+senderLogin+", "+password);
	//	app.getLoginHelper().login(senderLogin,password); 
//		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
	//	Assert.assertTrue(areamenu.isLogo()); // проверка наличия логотипчика
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_CatalogTv_i_videoTelevizoryTelevizory pagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Page_CatalogTv_i_videoTelevizoryTelevizoryID pagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		Page_Order pageorder = MyPageFactory.getPage(Page_Order.class);
		Page_OrderSuccess pageordersuccess = MyPageFactory.getPage(Page_OrderSuccess.class);		
		Sys_getOrders sysgetorders = MyPageFactory.getPage(Sys_getOrders.class);
		
		pagetehnosila.clickTVVA();
		pagecatalogtvivideotelevizorytelevizory.getWaitPage();
		pagetehnosila.clickTV();
		pagecatalogtvivideotelevizorytelevizory.clickOpenSelfDeliveryDescription();
		pagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		pagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
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
	}
	
}
