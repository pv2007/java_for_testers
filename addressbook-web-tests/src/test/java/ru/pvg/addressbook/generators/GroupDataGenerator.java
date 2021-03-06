package ru.pvg.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
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

  // пример: -c 3 -f src/test/resources/groups.json -d json

  @Parameter(names = "-c", description = "Group count")
  public int count;     // количество групп - первый параметр командной строки

  @Parameter(names = "-f", description = "Target file")
  public String file;   // путь к файлу  - второй параметр командной строки

  @Parameter(names = "-d", description = "Data format")
  public String format;   // формат файла   - третий  параметр командной строки


  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander.newBuilder()
            .addObject(generator)
            .build()
            .parse(args);
    generator.run();
  }

  private void run() throws IOException {
    //генерация тестовых данных
    List<GroupData> groups = generateGroup(count);
    if (format.equals("csv")) {
      //запись данных в файл
      saveAsCsv(groups, new File(file));
    } else if ((format.equals("xml"))) {
      saveAsXml(groups, new File(file));
    } else if ((format.equals("json"))) {
      saveAsJson(groups, new File(file));
    } else {
      System.out.println("Unrecognized format -d " + format + ". Use csv or xml or json");
    }
  }

  private void saveAsJson(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .serializeNulls()
            .create();
    String json = gson.toJson(groups);
    try(Writer writer = new FileWriter(file)) {
      writer.write(json);
      // writer.close();    //не нужно выполнять - файл закроется автоматически
    }
  }

  private void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    String xml = xstream.toXML(groups);
    try(Writer writer = new FileWriter(file)) {
      writer.write(xml);
      // writer.close();    //не нужно выполнять - файл закроется автоматически
    }

  }

  private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    System.out.println(file.getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (GroupData group : groups) {
        writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getGroupHeader(), group.getGroupFooter()));
      }
    }
  }

  private List<GroupData> generateGroup(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData()
              .withName(String.format("test %s", i))
              .withHeader(String.format("test header", i))
              .withFooter(String.format("test footer %s", i)));
    }
    return groups;
  }
}
