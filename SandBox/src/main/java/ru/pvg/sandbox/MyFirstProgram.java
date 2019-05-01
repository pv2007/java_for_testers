package ru.pvg.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    String someBody = "Привет, чижик!";
    System.out.println(someBody);
    hello("Ванюша");
    hello("Костя");
/*
    double len = 6.9;
        System.out.println("Площадь квадрата со стороной " + len + " м = " + sQuard(len) + " кв.м.");
*/

    Rectangle r = new Rectangle(6.9, 8.9);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " м = " + sRect(r) + " кв.м.");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " м = " + sQuard(s) + " кв.м.");
    System.out.println(sQuard(s));
  }

  public static void hello(String nameGuest) {
    System.out.println("ПРиветствуем тебя в JAVA, " + nameGuest + "!!!");
  }

  public static double sQuard(Square s) {
    return s.l * s.l;
  }

  public static double sRect(Rectangle r) {
    return r.a * r.b;
  }

}