package ru.pvg.sandbox;

/*
   Created Владимир  at 15:21  01.05.2019
*/
public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x=x;
    this.y=y;
  }

  public double distance() {
    return Math.sqrt(this.x*this.x + this.y*this.y);
  }
}

