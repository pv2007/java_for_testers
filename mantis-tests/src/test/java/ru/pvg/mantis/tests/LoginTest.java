package ru.pvg.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pvg.mantis.appmanager.HttpSession;

import java.io.IOException;

/*
   Created Владимир  at 16:14  15.06.2019
*/
public class LoginTest extends TestBase {
  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword")));
    Assert.assertTrue(session.isLoggedInAs(app.getProperty("web.adminLogin")));
  }
}
