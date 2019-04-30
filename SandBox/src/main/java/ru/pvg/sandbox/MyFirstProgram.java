package ru.pvg.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    String someBody = "Привет, чижик!";
    System.out.println(someBody);
    hello("Ванюша");
    hello("Костя");

    double len = 6.9;
        System.out.println("Площадь квадрата со стороной " + len + " м = " + sQuard(len) + " кв.м.");

    double len1 = 6.9;
    double len2 = 8.9;
    System.out.println("Площадь прямоугольника со сторонами " + len1 + " и "+ len2 +" м = " + sQuard(len1, len2) + " кв.м.");


    double s2=sQuard(3.67);
    System.out.println(sQuard(len));
  }

  public static void hello(String nameGuest) {
    System.out.println("ПРиветствуем " + nameGuest + "!!!");
  }

  public static double sQuard(double l) {
    return l*l;
  }

  public static double sQuard(double a, double b) {
    return a*b;
  }

}