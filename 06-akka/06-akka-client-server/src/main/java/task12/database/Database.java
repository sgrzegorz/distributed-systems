package task12.database;

import java.sql.*;

public class Database {


    public static int get(String productName)  {
        try {
            Connection c = openConnection();

            Statement stmt = c.createStatement();
            String sql = String.format("SELECT * FROM PRODUCT WHERE NAME= '%s' LIMIT 1;\n", productName);
            ResultSet rs = null;

            String name =null;
            int nquestions =-1;
            try {
                rs = stmt.executeQuery(sql);
                name = rs.getString("name");
                nquestions = rs.getInt("nquestions");
            } catch (SQLException e) {
                return 0;
            }


            rs.close();
            stmt.close();
            c.close();

            return (nquestions);
        } catch (Exception e) {
            e.printStackTrace();

            return -1;
        }

    }

    public static void insert(String productName)  {
        Connection c = null;
        Statement stmt = null;
        String sql = null;

        try {
            c = openConnection();
            stmt = c.createStatement();

            int nquestions = -1;
            sql = String.format("SELECT * FROM PRODUCT WHERE NAME= '%s' LIMIT 1;\n", productName);
            try {
                ResultSet rs = stmt.executeQuery(sql);
                nquestions = rs.getInt("nquestions");

            } catch (SQLException e) {
                nquestions = 0;
            }


            sql = String.format("INSERT OR REPLACE INTO PRODUCT (NAME,NQUESTIONS) VALUES ('%s', '%d'); ", productName, nquestions + 1);
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (c != null) {
                    c.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

        }
    }

    public static void createTable() {
        try {
            Connection c = Database.openConnection();
            Statement stmt = c.createStatement();

            String sql = "CREATE TABLE PRODUCT " +
                    " (NAME TEXT PRIMARY KEY  NOT NULL, " +
                    " NQUESTIONS INT NOT NULL) ";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:database.db");

        return c;
    }



//    public static void main(String args[]) {
//        createTable();
//
//    }
}