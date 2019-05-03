package ru.pvg.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/*
   Created Владимир  at 10:50  03.05.2019
*/
public class SquareTests {

  @Test
  public void testArea(){
    Square s=new Square(5);
    Assert.assertEquals( s.area(),25.0);
  }
}
