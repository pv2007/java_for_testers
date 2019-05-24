package ru.pvg.addressbook.generators;

import ru.pvg.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/*
   Created Владимир  at 14:56  24.05.2019
*/
public class GroupDataGenerator {

  public static void main (String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);  // количество групп - первый параметр командной строки
    File file = new File(args[1]);          // путь к файлу  - второй параметр командной строки
    //генерация тестовых данных
    List<GroupData> groups = generateGroup(count);
    //запись данных в файл
    save(groups, file);


  }

  private static void save(List<GroupData> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getGroupName(),group.getGroupHeader(),group.getGroupFooter()));
    }
    writer.close();   //ОБЯЗАТЕЛЬНО закрыть файл после записи!!!
  }

  private static List<GroupData> generateGroup(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i=0;i<count;i++) {
      groups.add(new GroupData()
              .withName(String.format("test %s",i))
              .withHeader(String.format("test header %s",i))
              .withFooter(String.format("test footer %s",i)));
    }
    return groups;
  }
}
