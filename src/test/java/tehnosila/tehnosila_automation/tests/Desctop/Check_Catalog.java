package tehnosila.tehnosila_automation.tests.Desctop;
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
import tehnosila.tehnosila_automation.pages.Desctop.Page_Catalog;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.tests.TestBase;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

// Тест изменения количества товаров в корзине, удаление товара из корзины
public class Check_Catalog extends TestBase{
	public List<String> numbersSubcategories = new ArrayList<>(); // массив кол-ва товаров по категориям
	public List<String> numbersSubSubcategories = new ArrayList<>(); // массив кол-ва товаров по подкатегориям
	private int maxnumber = 9000;
	@Test
	public void loginTest() throws Exception{
		app.getNavigationHelper().getURL(NavigationBase.papicatalog);
		app.getGetDataHelper().getTotalNumberCatalogOnline();
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		Page_Catalog pagecatalog = MyPageFactory.getPage(Page_Catalog.class);
		app.getNavigationHelper().refreshPage();
		pagetehnosila.getPageCatalog();
		app.getNavigationHelper().refreshPage();
		pagecatalog.summAllProducts(numbersSubcategories);
		//сюда добавить взятие кол-ва товаров со страницы каталога главного 
		pagecatalog.AllSubcategories(numbersSubSubcategories);
		pagecatalog.checkPtotatlnumber(maxnumber);
	}
}
