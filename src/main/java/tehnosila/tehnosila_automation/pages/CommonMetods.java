package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.pages.Page_AreaMenu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author MRasstrigina
 *
 */

// Класс общих методов
public class CommonMetods extends Page_AreaMenu {
	private static Logger Log = LoggerFactory.getLogger(CommonMetods.class);
	
	@FindBy(linkText = "ссылке")
	public WebElement linkText; // ссылка "ссылке" в информационном сообщении
	
	@FindBy(xpath = "//div[@id='viewModel']/div/h3") 
	private WebElement title; // заголовок на страницах
	
	@FindBy(xpath = "//div[@class='filter-tool']/h3") 
	private WebElement titlefilter; // заголовок фильтра на страницах реестров документов
	
    // жмаканье на ссылку "ссылке" в информационном сообщении
    public void clickLinkText() {
    	linkText.click();  	
    //	app.getNavigationHelper().waitInvisible(By.xpath("//div[@class='loading']"), 10);
	}
    
	// вытягивание названия страницы
	public String getTitleClient(){
		return title.getText();
	}
	
	// вытягивание заголовка фильтра страницы
	public String getTitleFilterClient(){
		return titlefilter.getText();
	}
	
	// ожидание пока отработает прелоэдер
	public void Waiting(){
		app.getNavigationHelper().waitVisible(driver.findElement(By.xpath("//div[@id='menu_popup']")), 10);
	}
	
	public void WaitingMobile(){
		app.getNavigationHelper().waitVisible(driver.findElement(By.xpath("//a[@id='popup-button-to-cart']")), 10);
	}

	public void RefreshTerm(){
		app.getNavigationHelper().refreshPage();
	}
}
