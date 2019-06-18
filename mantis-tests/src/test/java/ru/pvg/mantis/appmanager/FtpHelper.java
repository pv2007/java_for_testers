package ru.pvg.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
   Created Владимир  at 15:14  18.06.2019
*/
public class FtpHelper {
  private ApplicationManager app;
  private FTPClient ftp;

  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient();
  }


  public void upload(File file, String target, String backup) throws IOException {
    String pth1 = app.getProperty("ftp.host");
    String pth2 = app.getProperty("ftp.login");
    String pth3 = app.getProperty("ftp.password");
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(backup);
    ftp.rename(target, backup);
    ftp.enterLocalPassiveMode();
    ftp.storeFile(target, new FileInputStream(file));
    ftp.disconnect();
  }

  public void restore(String backup, String target) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }

}
