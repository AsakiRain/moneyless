import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    Connection c = null;

    public DBUtils() {
        try {
            Class.forName("../lib/sqlite-jdbc-3.36.0.3.jar");
            // create a database connection
            c = DriverManager.getConnection("jdbc:sqlite:master.db");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        System.out.println("Opened database successfully");
    }

    public void init() {
        try {
            Statement s = c.createStatement();
            s.setQueryTimeout(10); // set timeout to 30 sec.
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(account STRING PRIMARY KEY NOT NULL," +
                    "password STRING NOT NULL);";
            s.executeUpdate(sql);
            sql = "INSERT INTO users values('hdu', '123');";
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (c != null)
                c.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void check() {
        try {
            Statement s = c.createStatement();
            s.setQueryTimeout(10); // set timeout to 30 sec.
            String sql = "SELECT * FROM users;";
            ResultSet res = s.executeQuery(sql);
            while (res.next()) {
                // read the result set
                System.out.println("account = " + res.getString("account"));
                System.out.println("password = " + res.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}