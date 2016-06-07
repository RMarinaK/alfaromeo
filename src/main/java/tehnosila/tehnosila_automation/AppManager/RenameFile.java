package tehnosila.tehnosila_automation.AppManager;
/**
 * @author RasstriginaMK
 *
 */
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class RenameFile {
	
	public String changeDateInXmlName(String path) throws IOException{
		File f = new File(path);
		File[] matchingfiles = f.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name){
				return name.endsWith(".xml");
			}
		});

		String simpleFileName = matchingfiles[0].getName();
		String[] splittedFileName = simpleFileName.split("_");
		ApplicationManager app = ApplicationManager.getInstance();
		String date = app.getToday("yyyyMMdd");
		String newName = simpleFileName.replace(splittedFileName[4], date);

		File file = new File(matchingfiles[0].getParent()+System.getProperty("file.separator")+newName);
		boolean success = matchingfiles[0].renameTo(file);
		
		if (!success) {
				System.out.println("Не удалось переименовать файл.");		
			}		
		return file.getAbsolutePath();
	}
}
