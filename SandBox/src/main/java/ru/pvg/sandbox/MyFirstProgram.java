package ru.pvg.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    String someBody = "Привет, чижик!";
    System.out.println(someBody);
    hello("Ванюша");

    Rectangle r = new Rectangle(6.9, 8.9);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " м = " + r.area() + " кв.м.");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " м = " + s.area() + " кв.м.");

    Point p=new Point(2.5, 5.0);
    System.out.println("Расстояние от (0,0) до" +"("+p.x+", "+p.y+") = " + p.distance());

    Point p1=new Point(2.5, 5.0);
    Point p2=new Point(3.5, 6.0);
    System.out.println("Расстояние от (2.5,5.0) до" +"("+p2.x+", "+p2.y+") = " + distance(p1, p2));

    Point p3=new Point(1.5, 5);
    Point p4=new Point(13, 14);
    Points pts = new Points(p3,p4);
    System.out.println("Расстояние от p3(1.5,5.0) до" +"("+p4.x+", "+p4.y+") = " + pts.distance(p3, p4));


  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p1.x - p2.x),2) + Math.pow((p1.y - p2.y),2));
  }

  public static void hello(String nameGuest) {
    System.out.println("ПРиветствуем тебя в JAVA, " + nameGuest + "!!!");
  }

}