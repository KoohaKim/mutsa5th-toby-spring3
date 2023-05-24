package com.example.tobyspring3.db;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {
  public void check() throws SQLException, ClassNotFoundException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Map<String, String> env = getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");


    Connection con = DriverManager.getConnection(dbHost,dbUser,dbPassword);

    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SHOW DATABASES");
    rs = st.getResultSet();
    while (rs.next()) {
      String str = rs.getString(1);
      System.out.println(str);
    }
  }


  public void add() throws ClassNotFoundException, SQLException {

    Class.forName("com.mysql.cj.jdbc.Driver");
    Map<String, String> env = getenv();
    String dbHost = env.get("DB_HOST");
    String dbUser = env.get("DB_USER");
    String dbPassword = env.get("DB_PASSWORD");


    Connection con = DriverManager.getConnection(dbHost,dbUser,dbPassword);

    PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
    pstmt.setString(1, "1");
    pstmt.setString(2, "hihi");
    pstmt.setString(3, "12345678");
    pstmt.executeUpdate();
  }


  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    ConnectChecker cc = new ConnectChecker();
    cc.check();
    cc.add();
  }
}
