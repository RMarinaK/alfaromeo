package tehnosila.tehnosila_automation.tests;

import java.io.File;
import tehnosila.tehnosila_automation.pages.CommonMetods;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Page_HelpFeedback;
import tehnosila.tehnosila_automation.pages.Page_Tehnosila;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author MRasstrigina
 *
 */
public class Links_Footer extends TestBase{
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
			String helpfeedback,  String purchaseOnCredit, String bonusProgramm, String setupAndInstallation,
			String giftCards, String servicePlus, String insuranceTechniques, String tehnotrendProgram,
			String warrantyService, String serviceCenterLocations, String inputname, String inputemail, String inputphone,
			String inputcity, String textareamessage, String message) throws Exception{ 
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		CommonMetods commonmetods = MyPageFactory.getPage(CommonMetods.class);
		Page_HelpFeedback pagehelpfeedback = MyPageFactory.getPage(Page_HelpFeedback.class);
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
		// Отправка feedback
		pagehelpfeedback.setName(inputname);
		pagehelpfeedback.setEmail(inputemail);
		pagehelpfeedback.setPhone(inputphone);
		pagehelpfeedback.setCity(inputcity);
		pagehelpfeedback.setMessage(textareamessage);
		pagehelpfeedback.clickButtonSend();
		pagehelpfeedback.assertMessage(message);
		commonmetods.clickLogo();
		
		//---------------
		//УСЛУГИ И СЕРВИС (Пестерев Д.О. 14.06.2016)
		//---------------
		
		//Покупка в кредит
		pagetehnosila.clickPurchaseOnCredit();
		commonmetods.assertTitle(purchaseOnCredit);
		commonmetods.assertHeader(purchaseOnCredit);
		commonmetods.clickLogo();
		//Бонусная программа - нужна доработка
		pagetehnosila.clickBonusProgramm();	
		commonmetods.assertTitle(bonusProgramm);
		pagetehnosila.tryToOpen();
		//Настройка и установка
		pagetehnosila.clickSetupAndInstallation();
		commonmetods.assertTitle(setupAndInstallation);
		commonmetods.assertHeader(setupAndInstallation);
		commonmetods.clickLogo();
		//Подарочные карты
	/*	pagetehnosila.clickGiftCards();
		commonmetods.assertTitle(giftCards);
		commonmetods.assertHeader(giftCards);
		commonmetods.clickLogo();*/
		//Сервис плюс
		pagetehnosila.clickServicePlus();
		commonmetods.assertTitle(servicePlus);
		commonmetods.assertHeader(servicePlus);
		commonmetods.clickLogo();
		//Страхование техники
		pagetehnosila.clickInsuranceTechniques();
		commonmetods.assertTitle(insuranceTechniques);
		commonmetods.assertHeader(insuranceTechniques);
		commonmetods.clickLogo();
		//Программа Технотренд
		pagetehnosila.clickTehnotrendProgram();
		commonmetods.assertTitle(tehnotrendProgram);
		commonmetods.assertHeader(tehnotrendProgram);
		commonmetods.clickLogo();
		//Гарантийное обслуживание
		pagetehnosila.clickWarrantyService();
		commonmetods.assertTitle(warrantyService);
		commonmetods.assertHeader(warrantyService);
		commonmetods.clickLogo();
		//Адреса сервисных центров
		pagetehnosila.clickServiceCenterLocations();
		String tittleServiceCenterLocations = "Cервисные центры"; //Говнохардкод, т.к. какая-то беда с кодировкой/раскладкой, при чтении из LinksFooter.xls
		commonmetods.assertTitle(tittleServiceCenterLocations);
		commonmetods.assertHeader(serviceCenterLocations);
		commonmetods.clickLogo();
	
	}
	
}
