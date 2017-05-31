package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author MRasstrigina
 *
 */

public class Mobile_Page_Order extends PagesBase{
//	private static Logger Log = LoggerFactory.getLogger(Mobile_Page_Order.class);
	//DSE: url to check page
	protected String URL_MATCH = super.getBaseURL()+"#/order";
	
	@FindBy(id="OrderForm_orderContact_fio")
	private WebElement orderfromordercontactfio; // Поле "Ваше имя"
	
	@FindBy(id="OrderForm_orderContact_phone")
	private WebElement orderfromordercontactphone; // Поле "Телефон"
	
	@FindBy(id="OrderForm_orderContact_email")
	private WebElement orderfromordercontactemail; // Поле "Электронная почта"
		
	@FindBy(xpath="//*[@id='submitOrderForm']/div[2]/button")
	private WebElement radiobuttondelivery; // Пункты вывоза
	
	@FindBy(xpath="//div[1]/div[2]/div[6]/div[1]/a[@class='point-chooser-order']")
	private WebElement clickfirstdeliverybutton;
	
	@FindBy(xpath="//button[@class='button-control buy-button buy-button--mod submitOrder']")
	private WebElement buttonsubmitorder; // Кнопка "Завершить оформление"

	
	protected boolean isNecessaryToChangeParam(String param){
		if(param.equals(" ")||param.equals("")){
			return false;
		}else{
			return true;
		}		
	}
	
	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}
	
	// ожидание пока страница прогрузится
	public void getWaitPage(){
		app.getNavigationHelper().getPage(URL_MATCH);
	}
	
	public void setOrderFromOrderContactFio(String string) {
		if(isNecessaryToChangeParam(string)){
			orderfromordercontactfio.click();
			orderfromordercontactfio.clear();
			orderfromordercontactfio.sendKeys(string);
		}
	}
	
	public void setOrderFromOrderContactPhone(String string) {
		if(isNecessaryToChangeParam(string)){
			orderfromordercontactphone.click();
			orderfromordercontactphone.clear();
			orderfromordercontactphone.sendKeys(string);
		}
	}
	
	public void setOrderFromOrderContactEmail(String string) {
		if(isNecessaryToChangeParam(string)){
			orderfromordercontactemail.click();
			orderfromordercontactemail.clear();
			orderfromordercontactemail.sendKeys(string);
		}
	}
	
	// Пункты вывоза
	public void clickRadioButtonDelivery() {
		radiobuttondelivery.click(); 
	}
	
	// жмаканье на "Завершить оформление"
	public void clickButtonSubmitOrder() {
		buttonsubmitorder.click(); 
	}
	
	// Выбор первого пункта самовывоза
	public void clickFirstDeliveryButton() {
		clickfirstdeliverybutton.click();
	}
}
