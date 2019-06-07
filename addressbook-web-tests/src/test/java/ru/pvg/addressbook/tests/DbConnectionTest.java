package ru.pvg.addressbook.tests;

import org.testng.annotations.Test;
import ru.pvg.addressbook.model.GroupData;
import ru.pvg.addressbook.model.Groups;


import java.sql.*;

/*
   Created Владимир  at 10:22  07.06.2019
*/
public class DbConnectionTest {

  @Test
  public void testDbConnection(){


/*
    //vs MariaDB connector
    //create connection for a server installed in localhost, with a user "root" with no password
    try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/addressbook", "root", null)) {
      // create a Statement
      try (Statement st = conn.createStatement()) {
        //execute query
        try (ResultSet rs = st.executeQuery("SELECT 'Hello World!'")) {
          //position result to first
          rs.first();
          System.out.println(rs.getString(1)); //result is "Hello World!"
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
*/

    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");

      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");

      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withName(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }
      //закрываем все коннекты 
      rs.close();
      st.close();
      conn.close();

      System.out.println(groups);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

  }
}
