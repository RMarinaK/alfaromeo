/**
 * 
 */
package tehnosila.tehnosila_automation.AppManager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;

import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.PageArea_Header;
import tehnosila.tehnosila_automation.pages.Desctop.Page_LoginPage;


/**
 * @author RasstriginaMK
 *
 */

public class LoginHelper extends NavigationHelper{
	
	
	public LoginHelper (ApplicationManager appManager){
		super(appManager);
	}

	public void login(String login,String password) {
		
		Page_LoginPage loginPage = MyPageFactory.getPage(Page_LoginPage.class);
		driver.manage().deleteAllCookies();
		loginPage.login(login, password);
	}
	
//	public void loginDemo(String login,String password) {
//		
//		Page_LoginPage loginPage = MyPageFactory.getPage(Page_LoginPage.class);
//		driver.manage().deleteAllCookies();
//		loginPage.loginDemo(login, password);
//	}
//	
	public void logout(){
		
		Page_LoginPage loginPage = null;
		
		int counter = 0;
		while(loginPage == null){
			PageArea_Header pageHeader = MyPageFactory.getPage(PageArea_Header.class);
			loginPage = pageHeader.logout();
			if(counter++>5){break;}
		}
		
	}
	
	public void fitWindowTo(int width,int height){
		Dimension dd = new Dimension(width,height);
		driver.manage().window().setSize(dd);
	}

	public void login_if_the_first_time(String login,String password) {
		PageArea_Header head = MyPageFactory.getPage(PageArea_Header.class);
		if(!head.isProfileNamePresence()){
			login(login,password);
		}
	}

	public void logout_if_logined() {
		try{
			PageArea_Header pageHeader = MyPageFactory.getPage(PageArea_Header.class);
			if(pageHeader.isProfileNamePresence()){
				pageHeader.logout();
			}
		}catch(WebDriverException e){
			
		}
	}
}
