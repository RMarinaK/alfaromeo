/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.pages.Page_Cart;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizory;
import tehnosila.tehnosila_automation.pages.Page_CatalogTv_i_videoTelevizoryTelevizoryID;
import tehnosila.tehnosila_automation.pages.Page_Order;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
public class Links_Footer extends TestBase{
		
	private static Logger Log = LoggerFactory.getLogger(Links_Footer.class);

	
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"LinksFooter"+File.separator+"LinksFooter.xls",
                "LinksFooter", "Data");
        return(retObjArr);
    }
	
	
	@Test (dataProvider = "DP1")
	public void loginTest(String infoabout, String newsinfo, String infocareer, String infopress,
			String infocontacts, String infostores, String infolease, String infolegal,
			String webshophowtobuy, String webshoppayment, String webshopdelivery,
			String webshoppickup, String webshopexchange, String webshopguide,
			String helpfeedback) throws Exception{ 
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		// О компании Техносила
		pagetehnosila.clickAboutTC();
		commonmetods.assertTitle(infoabout);
		commonmetods.assertHeader(infoabout);
		commonmetods.clickLogo();
		//Новости компании
		pagetehnosila.clickNewsTC();
		commonmetods.assertTitle(newsinfo);
		commonmetods.assertHeader(newsinfo);
		commonmetods.clickLogo();
		// Вакансии
		pagetehnosila.clickCareer();
		commonmetods.assertTitle(infocareer);
		commonmetods.assertHeader(infocareer);
		commonmetods.clickLogo();
		//Пресс-релизы	
		pagetehnosila.clickPress();
		commonmetods.assertTitle(infopress);
		commonmetods.assertHeader(infopress);
		commonmetods.clickLogo();
		//Контактная информация
		pagetehnosila.clickContacts();
		commonmetods.assertTitle(infocontacts);
		commonmetods.assertHeader(infocontacts);
		commonmetods.clickLogo();	
		// Адреса магазинов
		pagetehnosila.clickStores();
		commonmetods.assertTitle(infostores);
		commonmetods.assertHeader(infostores);
		commonmetods.clickLogo();
		// Арендодателям
		pagetehnosila.clickLease();
		commonmetods.assertTitle(infolease);
		commonmetods.assertHeader(infolease);
		commonmetods.clickLogo();
		// Юридическая информация
		pagetehnosila.clickLegal();
		commonmetods.assertTitle(infolegal);
		commonmetods.assertHeader(infolegal);
		commonmetods.clickLogo();
		//Как сделать заказ на сайте
		pagetehnosila.clickHowToBuy();
		commonmetods.assertTitle(webshophowtobuy);
		commonmetods.assertHeader(webshophowtobuy);
		commonmetods.clickLogo();
		// Оплата
		pagetehnosila.clickPayment();
		commonmetods.assertTitle(webshoppayment);
		commonmetods.assertHeader(webshoppayment);
		commonmetods.clickLogo();
		// Доставка
		pagetehnosila.clickDelivery();
		commonmetods.assertTitle(webshopdelivery);
		commonmetods.assertHeader(webshopdelivery);
		commonmetods.clickLogo();
		// Получение в магазине
		pagetehnosila.clickPickup();
		commonmetods.assertTitle(webshoppickup);
		commonmetods.assertHeader(webshoppickup);
		commonmetods.clickLogo();
		// Возврат и обмен
		pagetehnosila.clickExchange();
		commonmetods.assertTitle(webshopexchange);
		commonmetods.assertHeader(webshopexchange);
		commonmetods.clickLogo();
		// Правила работы
		pagetehnosila.clickGuide();
		commonmetods.assertTitle(webshopguide);
		commonmetods.assertHeader(webshopguide);
		commonmetods.clickLogo();
		// Обратная связь
		pagetehnosila.clickFeedback();
		commonmetods.assertTitle(helpfeedback);
		commonmetods.assertHeader(helpfeedback);
		commonmetods.clickLogo();
	}
	
}
