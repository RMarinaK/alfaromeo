/**
 * @author EDanilova
 *
 */

package tehnosila.tehnosila_automation.tests.Desctop;

import java.io.File;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tehnosila.tehnosila_automation.pages.MyPageFactory;
import tehnosila.tehnosila_automation.pages.Desctop.Page_Tehnosila;
import tehnosila.tehnosila_automation.pages.WorkWithArr;
import tehnosila.tehnosila_automation.pages.WorkWithSFTP;
import tehnosila.tehnosila_automation.pages.WorkWithXML;
import tehnosila.tehnosila_automation.tests.TestBase;

public class RegionCheckOld extends TestBase{
		
	@DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("src"+File.separator+"test"+File.separator+"resources"+File.separator+"DDT"+File.separator+"RegionCheck"+File.separator+"RegionCheck.xls",
                "RegionCheck", "Data");
        return(retObjArr);
    }
	
	@Test (dataProvider = "DP1")
	public void regionTest(String ftpAdr, String port, String user, String password, String FullPathToPutFile, String FilenameOnFTP, String PathToCreateFile) throws Exception{			 	
		
		Page_Tehnosila pagetehnosila = MyPageFactory.getPage(Page_Tehnosila.class);
		
	    WorkWithSFTP workwithsftp = MyPageFactory.getPage(WorkWithSFTP.class);
	    WorkWithXML workwithxml = MyPageFactory.getPage(WorkWithXML.class);
	    WorkWithArr workwitharr = MyPageFactory.getPage(WorkWithArr.class);
	        	
	    workwithsftp.DownloadFileFromFTP(ftpAdr, port, user, password, FullPathToPutFile, FilenameOnFTP);
	    workwithxml.createFile(PathToCreateFile);
	    workwithxml.ReadXMLFileDOM(PathToCreateFile, FullPathToPutFile);
	    workwitharr.getDataFromTxt(PathToCreateFile);
	    app.getNavigationHelper().refreshPage();
	    pagetehnosila.clickCityPopup();
	    workwitharr.cityAmount();

	    //workwitharr.CityCountCheck(NavigationBase.acCount, NavigationBase.arrSize);
	   // workwitharr.getPopUpCity();
	   // workwitharr.checkCityName(NavigationBase.cityXML, NavigationBase.citySite);
		workwitharr.popUpCity();
		//workwitharr.checkCityName(NavigationBase.cityXML, NavigationBase.citySite, 0);
		//workwitharr.checkCityName(NavigationBase.citySite, NavigationBase.cityXML, 1);
		//workwitharr.checkCityDomain(NavigationBase.domainXML, NavigationBase.domainSite, 0);
		//workwitharr.checkCityDomain(NavigationBase.domainSite, NavigationBase.domainXML, 1);       

	}
}