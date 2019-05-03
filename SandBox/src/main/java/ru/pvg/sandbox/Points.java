package ru.pvg.sandbox;

import java.util.Objects;

/*
   Created Владимир  at 16:14  01.05.2019
*/
public class Points {
    public Point p7;
    public Point p8;


    public Points(Point p1, Point p2) {
      this.p7 = p1;
      this.p8 = p2;
    }

    public double distance(Point p7, Point p8) {
      return Math.sqrt(Math.pow((p7.x - p8.x),2) + Math.pow((p7.y - p8.y),2));
    }

}
