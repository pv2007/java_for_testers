package ru.pvg.addressbook.tests;

import com.beust.jcommander.Parameter;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.pvg.addressbook.appmanager.ApplicationManager;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;

import java.io.IOException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
   Created Владимир  at 12:53  04.05.2019
*/
public class TestBase {


  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  protected static ApplicationManager app;

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  static {
    try {
      app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
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

  @BeforeMethod
  public void logTestStart(Method m, Object[] obj) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(obj));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] obj) {
    logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(obj));
  }

  public void verifyGroupListInUI() {
    String s1 = System.getProperty("verifyUI");
    if (Boolean.getBoolean("verifyUI")) {
    //if (1==1) {
      Groups dbGroups = app.db().groups();
      app.goTo().gotoPage("groups");
      Groups uiGroups = app.group().all();
      MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getGroupName()))
              .collect(Collectors.toSet())));
    }
  }
}
