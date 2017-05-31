package tehnosila.tehnosila_automation.pages;

import tehnosila.tehnosila_automation.pages.Page_AreaMenu;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
/*import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;*/

/**
 * @author EDanilova
 *
 */

public class WorkWithSFTP extends Page_AreaMenu {

	@Override
	public boolean isOnThisPage(){
		return true;
	}	
	
	// Скачиваем с ftp файл region_shop_data.xml - для ПРОДА, ftp
    public void DownloadFileFromFTP(String ftpAdr, String port, String user, String password, String FullPathToPutFile, String FilenameOnFTP)
    {

    	int portI = Integer.parseInt(port);
    	
        FTPClient client = new FTPClient();
        FileOutputStream fos = null;

        try {

            client.connect(ftpAdr, portI);
            client.login(user, password);
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);

            String filename = FullPathToPutFile;
            fos = new FileOutputStream(filename);
            
            client.retrieveFile("/export/webdata/"+FilenameOnFTP, fos);

        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
          	try {
                if (fos != null) {
                    fos.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
	/* Скачиваем с sftp файл region_shop_data.xml - Для тестовых стендов, sftp
	public void DownloadFileFromSFTP() {
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
	}*/
}
