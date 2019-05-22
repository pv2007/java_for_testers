package ru.pvg.sandbox;

/*
   Created Владимир  at 12:08  09.05.2019
*/
public class Equation {
  private double a;
  private double b;
  private double c;

  private int n;

  public Equation(double a, double b, double c) {


    this.a = a;
    this.b = b;
    this.c = c;
    double d = b * b - 4 * a * c;



    if (a==0) {
      if (b==0) {
        if (c==0) {
          System.out.println("бесконечное количество решений");
          n=-1;
        } else {
          System.out.println("Это обычное равенство, не имеет решение");
          n=0;
        }
      } else {
        System.out.println("Это линейное уравнение, имеет одно решение");
        n=1;
      }

    } else {
      System.out.println("Это квадратное уравнение, имеет два решения");
      if (d > 0) {
        n = 2;
      } else if (d == 0) {
        n = 1;
      } else {
        n = 0;
      }
    }


  }

  public int getEquationNumber() {
    return n;
  }
}
