/**
 * 
 */
package tehnosila.tehnosila_automation.tests;

/**
 * @author RasstriginaMK
 *
 */
import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import tehnosila.tehnosila_automation.AppManager.ApplicationManager;
import tehnosila.tehnosila_automation.pages.MyPageFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

public class TestBase {
	private static Logger Log = LoggerFactory.getLogger(TestBase.class);

	protected ApplicationManager app;
	public static MyPageFactory pf;
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	@BeforeClass
	public void setUp() throws Exception {
		app = ApplicationManager.getInstance();
		pf = new MyPageFactory();
		app.getWebDriverHelper().warmUp(20);
		System.setProperty(ESCAPE_PROPERTY, "false");
		System.setProperty("file.encoding", "UTF-8");
	  }

	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws BiffException, IOException{
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName);
            int startRow,startCol, endRow, endCol,ci,cj;
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            Log.info("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                    //Log.debug(tabArray[ci][cj].getClass().getName());
                }
            }
       }
        catch (Exception e)    {
        	e.printStackTrace();
            Log.error("error in getTableArray()");
       }

        return(tabArray);
    }


}
