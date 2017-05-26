package tehnosila.tehnosila_automation.tests;
/**
 * @author MRasstrigina
 *
 */

import tehnosila.tehnosila_automation.AppManager.NavigationBase;

/**
 * @author DZhukov
 *
 */

import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_Catalog;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import org.testng.annotations.Test;

// Тест изменения количества товаров в корзине, удаление товара из корзины
public class Check_Catalog extends TestBase{
		
	@Test
	public void loginTest() throws Exception{
		app.getNavigationHelper().getURL(NavigationBase.papicatalog);
		app.getGetDataHelper().getTotalNumberCatalogOnline();
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_Catalog pagecatalog = MyPageFactory.getPage(Page_Catalog.class);
		app.getNavigationHelper().refreshPage();
		pagetehnosila.getPageCatalog();
		app.getNavigationHelper().refreshPage();
	//	pagecatalog.AllSubcategories();
	//	pagecatalog.summAllProducts();
		pagecatalog.checkPtotatlnumber();
	//	pagecatalog.checkPresult();
		
		
	//	pagecatalog.assertCount();
	//	pagecatalog.AllSubcategories();
	}
}
