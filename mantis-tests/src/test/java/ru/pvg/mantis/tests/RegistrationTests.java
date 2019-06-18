package ru.pvg.mantis.tests;

import org.testng.annotations.Test;

/*
   Created Владимир  at 12:58  18.06.2019
*/
public class RegistrationTests extends TestBase{
  @Test
  public void testRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");


  }

}
