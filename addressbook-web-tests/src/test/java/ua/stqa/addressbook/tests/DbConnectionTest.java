package ua.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ua.stqa.addressbook.model.GroupData;
import ua.stqa.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {

    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?useSSL=false&serverTimezone=UTC&user=root&password=");
      // useSSL=false&serverTimezone=UTC - явная установка таймзоны, чтобы не быдл exception
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name")).withHeader(rs.getString("group_header"))
                .withFooter(rs.getString("group_footer")));
      }
      // Do something with the Connection
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

