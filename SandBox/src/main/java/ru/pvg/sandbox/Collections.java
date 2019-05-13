package ru.pvg.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
   Created Владимир  at 9:41  13.05.2019
*/
public class Collections {

  public static void main(String[] args) {

    System.out.println(" Массивы объектов типа строки :");
    System.out.println(" (используется, например,  как аргументы функции main");
    String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "PHP";

    String[] langs2 = {"Java", "C#", "Python", "PHP"};

    for (int i=0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs2[i]);
    }

    for (String l: langs) {
      System.out.println("Я хочу выучить " + l);
    }

    System.out.println(" Списки объектов типа строки :");

    List<String> languages = new ArrayList<>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
    languages.add("PHP");

    List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");


    for (String l: languages) {
      System.out.println("Я хочу выучить " + l);
    }

    for (String l: languages2) {
      System.out.println("Я хочу выучить " + l);
    }


    for (int i=0; i < languages2.size(); i++) {
      System.out.println("Я хочу выучить " + languages2.get(i));
    }

    System.out.println(" Список объектов произвольного типа:");
    List languages3 = Arrays.asList("Java", "C#", "Python", "PHP");

    for (Object l: languages3) {
      System.out.println("Я учу " + l);
    }

  }
}
