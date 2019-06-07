package ru.pvg.sandbox;

import org.testng.annotations.Test;

import java.sql.*;

/*
   Created Владимир  at 10:22  07.06.2019
*/
public class DbConnectionTest {

  @Test
  public void testDbConnection(){
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
      // Do something with the Connection
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");
      while (rs.next()) {

      }
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
