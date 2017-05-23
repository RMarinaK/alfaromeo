package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.AppManager.ScreenShot;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import tehnosila.tehnosila_automation.tests.RegionCheck;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * @author MRasstrigina
 *
 */

// Класс общих методов
public class CommonMetods extends Page_AreaMenu {
	private static Logger Log = LoggerFactory.getLogger(CommonMetods.class);

	@Override
	public boolean isOnThisPage(){
		return true;
	}

	@FindBy(linkText = "ссылке")
	public WebElement linkText; // ссылка "ссылке" в информационном сообщении
	
//	@FindBy(xpath = "//div[@id='viewModel']/div/h3") 
//	private WebElement title; // заголовок на страницах
	
	@FindBy(xpath = "//div[@class='filter-tool']/h3") 
	private WebElement titlefilter; // заголовок фильтра на страницах реестров документов
	
	@FindBy(id = "logo")
	public WebElement logo; // логотип
	
	@FindBy(xpath = "//title") 
	private WebElement title; // title на страницах
	
	@FindBy(xpath = "//h1") 
	private WebElement header; // заголовок на страницах
	
	
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
	
	// жмаканье на логотип
	public void clickLogo() throws Exception {
		try {
			logo.click(); 
			Log.info("Логотип");
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}
	
	// вытягивание названия страницы
	public String getTitle(){
		return driver.getTitle();
	}
	
	// вытягивание заголовка страницы
	public String getHeader(){
		return header.getText();
	}
	
	public void scrollPage() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight," + "document.body.scrollHeight,document.documentElement.clientHeight));");
	}
	
	// скрол страницы вниз (не изменять кол-во пикселей)
	public void scrolling() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)","");
	}
	
	// проверка отображения Title
	public void assertTitle(String title) throws Exception {
		try {
			Log.info("***QA: Title "+ getTitle());
			Assert.assertEquals(title, getTitle()); // проверка отображения Title
			Log.info("***QA: Title "+ getTitle());
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}	
		
	// проверка отображения Header
	public void assertHeader(String header) throws Exception {
		try {
			Assert.assertEquals(header, getHeader()); // проверка отображения Header
			Log.info("***QA: Header "+ getHeader());
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
            ScreenShot.takeScreenShot();       
         }    
	}

	// вытаскивание pactionurl
/*	public void getActionURL(){
	//	NavigationBase.pheader = getHeader();
	//	Log.info("***QA: nameaction " +getHeader());
		try {
			String currenturl = driver.getCurrentUrl();
			int s = 0;
			for (int x=1; x< currenturl.indexOf("action/"); x++){
				s = x++;}
			Log.info("***QA: s "+ s);
			NavigationBase.pactionurl = currenturl.substring(s + 1);
			Log.info("***QA: pactionurl "+ NavigationBase.pactionurl);
		}
	    catch(Exception e) {      
	    	Log.info("Element Not Found");     
         }  
	}*/	
	
	// Получение HTTP response code
	public void getHTTPResponseCode() throws IOException {
		String currenturl = driver.getCurrentUrl();
		URL url = new URL(currenturl);
		Log.info("***QA: текущая страница: " + currenturl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
		connection.connect();
		int code = connection.getResponseCode();
	    Log.info("***QA: HTTP response code " + code);
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------
	//Проверка смены города	@author EDanilova
	// Скачиваем с sftp файл region_shop_data.xml
		public void SFTPDownloadFile() {
	        JSch jsch = new JSch();
	        Session session = null;

	        String host = "10.9.1.209";
	        int port = 22;
	        String login = "programmers";
	        String password = "yjdsqgfhjkmghjuhfvvbcnjd";
	        String downloadFolder = "/srv/test/exchange_test7/region_shop_data.xml";
	        String uploadFolder = "/src/test/resources/DDT/RegionCheck/region_shop_data.xml";
	        try {
	            session = jsch.getSession(login, host, port);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword(password);
	            session.connect();

	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel;
	            sftpChannel.get(downloadFolder, uploadFolder);
	            sftpChannel.exit();
	            session.disconnect();
	        } catch (JSchException e) {
	            e.printStackTrace();
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
		}
		
		// Создаем файл, куда будет записывать извлекаемые из xml города и доменные имена
			public void createFile(){
			    try(FileWriter fw = new FileWriter("/repo/tests/src/test/resources/DDT/RegionCheck/city_list.txt")) {
			        fw.close();
			    } catch (IOException e) {
			        System.err.println(e.getMessage());
			    }
			}
			
			// Извлекаем данные из xml и записываем их в файл
			public void ReadXMLFileDOM() {
		        try {
		        	
		        	PrintWriter pw = new PrintWriter("/repo/tests/src/test/resources/DDT/RegionCheck/city_list.txt");

		            // Создается построитель документа
		            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		            
		            // Создается дерево DOM документа из файла
		            Document document = documentBuilder.parse("/repo/tests/src/test/resources/DDT/RegionCheck/region_shop_data.xml");
		            
		            // Выполнять нормализацию не обязательно, но рекомендуется
		            document.getDocumentElement().normalize();
		            
		            // Получаем все узлы с именем "Region"
		            NodeList region = document.getElementsByTagName("Region");

		            for (int i = 0; i < region.getLength(); i++) {
		                Node node = region.item(i);
		                
		                if (Node.ELEMENT_NODE == node.getNodeType()) {
		                    Element element = (Element) node;
		                    String visibility = element.getElementsByTagName("Invisible").item(0).getTextContent();
		                    
		                    if (visibility.equals("No")){
		                	   pw.println((element.getElementsByTagName("Name").item(0).getTextContent()) + "," + (element.getElementsByTagName("Domain").item(0).getTextContent()));
		                	   NavigationBase.arrSize++;
		                    }
		                }
		          } 
		          pw.close();
		            
		        } catch (ParserConfigurationException | SAXException | IOException ex) {
		    	    Log.info("Read XML-file DOM error");   
		        }

			}
			
			public void CityCountCheck (int x, int y){
			       if(x == y){
			    	   	Log.info("Количество городов в xml и попапе совпадает = " + x);
			        	} else {
			        		Log.info("Количество городов НЕ совпадает! В xml: " + y + ", в попапе:  " + x);
			        	}

				
			}
}
