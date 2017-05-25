package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author EDanilova
 *
 */

// Класс общих методов
public class WorkWithArr extends Page_AreaMenu {
	
	@Override
	public boolean isOnThisPage(){
		return true;
	}
	
	//Извлекаем данные из txt-файла и записываем их в массив
	public void getDataFromTxt(String PathToCreateFile) throws IOException{
		FileInputStream fstream = new FileInputStream(PathToCreateFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		int i = 0;
		NavigationBase.cityXML = new String[NavigationBase.arrSize];
		NavigationBase.domainXML = new String[NavigationBase.arrSize];
		while ((strLine = br.readLine()) != null){
			if (strLine.trim().length() != 0){
				String[] cutStr = strLine.split(",");
				NavigationBase.cityXML[i] = cutStr[0];
				NavigationBase.domainXML[i] = cutStr[1];
			    i++;
			}
		}
		br.close();
	}
}
