package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.AppManager.NavigationBase;
import tehnosila.tehnosila_automation.pages.Page_AreaMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * @author EDanilova
 *
 */

// Класс общих методов
public class WorkWithXML extends Page_AreaMenu {
	private static Logger Log = LoggerFactory.getLogger(CommonMetods.class);

	@Override
	public boolean isOnThisPage(){
		return true;
	}
			
	//Создаем файл, куда будет записывать извлекаемые из xml города и доменные имена
	public void createFile(String PathToCreateFile){
		try(FileWriter fw = new FileWriter(PathToCreateFile)) {
			fw.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
			
	// Извлекаем данные из xml и записываем их в файл
	public void ReadXMLFileDOM(String PathToCreateFile, String FullPathToPutFile) {
		try {        	
			 PrintWriter pw = new PrintWriter(PathToCreateFile);
	
			 // Создается построитель документа
			 DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			           
			 // Создается дерево DOM документа из файла
			 Document document = documentBuilder.parse(FullPathToPutFile);
			            
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
		
}
