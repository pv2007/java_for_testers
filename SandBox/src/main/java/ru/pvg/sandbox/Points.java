package ru.pvg.sandbox;

import java.util.Objects;

/*
   Created Владимир  at 16:14  01.05.2019
*/
public class Points {
    public Point p7;
    public Point p8;


    public Points(Point p1, Point p2) {

      if (p7 != null) {
        this.p7.x = p1.x;
        this.p7.y = p1.y;
      }
      if (p8 != null) {
        this.p8.x = p2.x;
        this.p8.y = p2.y;
      }

    }

    public double distance(Point p7, Point p8) {
      return Math.sqrt(Math.pow((p7.x - p8.x),2) + Math.pow((p7.y - p8.y),2));
    }

}
