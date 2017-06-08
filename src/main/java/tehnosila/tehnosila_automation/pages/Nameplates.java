package tehnosila.tehnosila_automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * @author MRasstrigina
 *
 */

public class Nameplates extends PagesBase {
	private static Logger Log = LoggerFactory.getLogger(Nameplates.class);
	// DSE: url to check page
	protected String URL_MATCH = super.getBaseURL() + "#/action";

	@FindBy(xpath = "//div[@class='new-sticker action-sticker helper open-down open-right open-click close-other white custom']/span[contains(text(),'Акция')]/..")
	private WebElement actionsuperthree; // Шильдик Акция

	@FindBy(xpath = "//span[@class='promoword-discount']")
	private WebElement promoworddiscount; // Шильдик N%

	@FindBy(xpath = "//span[@class='sticker-title-additional']")
	private WebElement actionsticker; // Шильдик акция

	// private String sale5 = "Скидка 5% при онлайн-оплате";

	@Override
	void tryToOpen() {
		driver.get(this.URL_MATCH);
	}

	// проверка наличия шильдика Акция
	public void checkAction() {
		try {
			actionsuperthree.click();
			Log.info("***QA: Шильдик Акция есть");
		} catch (Exception e) {
			Log.info("Element Not Found");
			// ScreenShot.takeScreenShot();
		}
	}

	// проверка наличия ссылки
	public void checkAdditionalPromo(String actionnameplates) {
		try {
			WebElement additionalpromo = driver.findElement(By.xpath(
					"//div[@class='additional-promo ']/a[contains(text()," + "\"" + actionnameplates + "\"" + ")]"));
			Assert.assertEquals(additionalpromo.getText(), actionnameplates);
			Log.info("***QA: Скидон " + additionalpromo.getText());
		} catch (Exception e) {
			Log.info("Element Not Found");
			// ScreenShot.takeScreenShot();
		}
	}

	// проверка наличия шильдика Акция
	public void checkPromowordDiscount() {
		try {
			app.getNavigationHelper().isElementPresent(By.xpath("//a[@href='/action/discount_world']/img"));
			Log.info("***QA: Шильдик N% есть");
		} catch (Exception e) {
			Log.info("Element Not Found");
			// ScreenShot.takeScreenShot();
		}
	}

	// проверка наличия шильдика Акция
	public void checkActionSticker() {
		try {
			app.getNavigationHelper().waitVisible(actionsticker, 10);
			Log.info("***QA: Шильдик акции есть");
		} catch (Exception e) {
			Log.info("Element Not Found");
		}
	}

}
