package tehnosila.tehnosila_automation.tests.Desctop;


/**
 * @author DZhukov
 *
 */

import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Cart;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import tehnosila.tehnosila_automation.tests.TestBase;

import org.testng.annotations.Test;

// Тест изменения количества товаров в корзине, удаление товара из корзины
public class Change_Goods extends TestBase{
		
	@Test
	public void loginTest() throws Exception{
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_CatalogTv_i_videoTelevizoryTelevizory pagecatalogtvivideotelevizorytelevizory = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizory.class);
		Page_CatalogTv_i_videoTelevizoryTelevizoryID pagecatalogtvivideotelevizorytelevizoryid = MyPageFactory.getPage(Page_CatalogTv_i_videoTelevizoryTelevizoryID.class);
		Page_Cart pagecart = MyPageFactory.getPage(Page_Cart.class);
		
		
		pagetehnosila.getPageCatalog();
	//	pagetehnosila.clickTVVA();
		pagecatalogtvivideotelevizorytelevizory.getWaitPage();
		pagetehnosila.clickTV();
		app.getNavigationHelper().refreshPage();
		pagecatalogtvivideotelevizorytelevizory.clickOpenCourierDescription();
		pagecatalogtvivideotelevizorytelevizoryid.clickButtonBuy();
		pagecatalogtvivideotelevizorytelevizoryid.clickPopupButtonToCart();
		pagecart.waitCartLoadingLayer();
		pagecart.itemPrice();
		pagecart.getCartPrice();
		pagecart.clickServiceCheckBox();
		pagecart.waitCartLoadingLayer();
		pagecart.getServicePrice();
		pagecart.getItemPlusServicePrice();
		pagecart.assertCartItemPlusService();
		pagecart.clickButtonAccessories();
		pagecart.waitCartLoadingLayer();
		pagecart.clickAccessoriesButtonCart(); //
		pagecart.waitCartLoadingLayer();
		pagecart.itemPrice();
		pagecart.secondItemPrice();
		pagecart.getCartPrice();
		pagecart.assertCart();
		pagecart.clickButtonPlus();
		pagecart.waitCartLoadingLayer();
		pagecart.itemPrice();
		pagecart.secondItemPrice();
		pagecart.getCartPrice();
		pagecart.assertCart();
		pagecart.clickButtonMinus();
		pagecart.waitCartLoadingLayer();
		pagecart.itemPrice();
		pagecart.secondItemPrice();
		pagecart.getCartPrice();
		pagecart.assertCart();
		pagecart.clickButtonDelete();
		pagecart.waitCartLoadingLayer();
		pagecart.clickButtonDelete();
		pagecart.emptycart();
	}
}
