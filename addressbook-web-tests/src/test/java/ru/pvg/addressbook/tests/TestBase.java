package ru.pvg.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ru.pvg.addressbook.appmanager.ApplicationManager;

import java.io.IOException;

/*
   Created Владимир  at 12:53  04.05.2019
*/
public class TestBase {

  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

  protected static ApplicationManager app;

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

}
