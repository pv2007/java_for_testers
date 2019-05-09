package ru.pvg.sandbox;

/*
   Created Владимир  at 15:50  09.05.2019
*/
public class Equality {

  public static void main(String[] args) {
    String s1 = "firefox";
    String s2 = s1; // в этом случае новый объект не создается. В s2 присваивается ссылка равная ссылке s1
    String s3 = new String(s1);   //создается новый объект с таким же значением
    String s4 = "firefox";

    System.out.println(s1==s2);
    System.out.println(s1==s3);
    System.out.println(s1==s4);
    System.out.println(s1.equals(s2));
    System.out.println(s1.equals(s3));
    System.out.println(s1.equals(s4));
  }
}
