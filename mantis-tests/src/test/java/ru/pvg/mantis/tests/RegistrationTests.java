package ru.pvg.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.pvg.mantis.model.MailMessage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/*
   Created Владимир  at 12:58  18.06.2019
*/
public class RegistrationTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }


  @Test
  public void testRegistration() throws IOException {
    int userId =  (int) (Math.random() * 10000);
    String user = String.format("user%s",userId);
    String email = String.format("user%s@localhost.localdomain",userId) ;
    String password = "password";
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(user, confirmationLink, password);
    Assert.assertTrue(app.newSession().login(user, password));


  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }


}
