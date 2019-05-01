package ru.pvg.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    String someBody = "Привет, чижик!";
    System.out.println(someBody);
    hello("Ванюша");
    hello("Костя");
/*
    double len = 6.9;
        System.out.println("Площадь квадрата со стороной " + len + " м = " + area(len) + " кв.м.");
*/

    Rectangle r = new Rectangle(6.9, 8.9);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " м = " + r.area() + " кв.м.");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " м = " + s.area() + " кв.м.");
  }

  public static void hello(String nameGuest) {
    System.out.println("ПРиветствуем тебя в JAVA, " + nameGuest + "!!!");
  }

}