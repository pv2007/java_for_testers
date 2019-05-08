package ru.pvg.addressbook.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import ru.pvg.addressbook.appmanager.ConAppManager;

/*
   Created Владимир  at 14:55  08.05.2019
*/
public class ConTestBase {

  protected final ConAppManager conApp = new ConAppManager();

  @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
    conApp.initCon();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    conApp.stopCon();
  }

  public ConAppManager getConApp() {
    return conApp;
  }
}
