package tehnosila.tehnosila_automation.tests.Mobile;

import java.io.File;
import org.testng.Assert;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_Cart;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Mobile.Mobile_Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.tests.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author ZhukovDU
 *
 */

//Изменение количества товара
public class Mobile_Change_Goods extends TestBase{
	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"SmokeTests"+File.separator+"SmokeTests.xls",
                "SmokeTests", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void loginTest(String fio, String phone, String email, String paymentName, String deliveryName) throws Exception{
		Log.info("***QA: Изменение количества товара Mobile_Change_Goods");
		
		Page_AreaMenu areamenu = MyPageFactory.getPage(Page_AreaMenu.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID mobilepagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Mobile_Page_CatalogTv_i_videoTelevizoryTelevizory mobilepagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Mobile_Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Mobile_Page_Cart mobilepagecart = MyPageFactory.getPage(Mobile_Page_Cart.class);
		Mobile_Page_Tehnosila pagetehnosilamobile = MyPageFactory.getPage(Mobile_Page_Tehnosila.class);
		
		commonmetods.getHTTPResponseCode();
		commonmetods.getHTTPResponseCode();
		Assert.assertTrue(areamenu.isLogo());
		app.getNavigationHelper().refreshPage();
		pagetehnosilamobile.clickMenuTrigger();
		pagetehnosilamobile.clickCatalog();
		pagetehnosilamobile.clickTVAudioVideo();
		pagetehnosilamobile.clickTV();
		pagetehnosilamobile.clickLEDTV();
		mobilepagecatalogtvivideotelevizorytelevizory.clickItemInFowrapFirst();
//		mobilepagecatalogtvivideotelevizorytelevizoryid.closeIFrame();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		commonmetods.WaitingMobile();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		mobilepagecart.itemPrice();
		mobilepagecart.getCartPrice();
//		mobilepagecart.clickServiceCheckBox();
		mobilepagecart.waitCartLoadingLayer();
//		mobilepagecart.getServicePrice();
//		mobilepagecart.getItemPlusServicePrice();
//		mobilepagecart.assertCartItemPlusService();
		mobilepagecart.clickButtonAccessories();
		mobilepagecart.waitCartLoadingLayer();
		mobilepagecart.firstAccessories();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		commonmetods.WaitingMobile();
		mobilepagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		mobilepagecart.itemPrice();
		mobilepagecart.secondItemPrice();
		mobilepagecart.getCartPrice();
		mobilepagecart.assertCart();
		mobilepagecart.clickButtonPlus();
		mobilepagecart.waitCartLoadingLayer();
		mobilepagecart.itemPrice();
		mobilepagecart.secondItemPrice();
		mobilepagecart.getCartPrice();
		mobilepagecart.assertCart();
		mobilepagecart.clickButtonMinus();
		mobilepagecart.waitCartLoadingLayer();
		mobilepagecart.itemPrice();
		mobilepagecart.secondItemPrice();
		mobilepagecart.getCartPrice();
		mobilepagecart.assertCart();
		mobilepagecart.clickButtonDelete();
		mobilepagecart.waitCartLoadingLayer();
		mobilepagecart.clickButtonDelete();
		mobilepagecart.emptycart();
	}
}
