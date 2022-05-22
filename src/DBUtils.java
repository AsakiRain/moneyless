import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    Connection c = null;

    public DBUtils() {
        try {
            // create a database connection
            c = DriverManager.getConnection("jdbc:sqlite:moneyManager.db");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        System.out.println("Opened database successfully");
    }

    public void commit(String sql) {
        try {
            Statement s = c.createStatement();
            s.setQueryTimeout(10);

            s.executeUpdate(sql);
            s.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List query(String sql) {
        List list = new ArrayList();
        try {
            Statement s = c.createStatement();
            s.setQueryTimeout(10);

            ResultSet rs = s.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();

            while (rs.next()) {
                Map rowData = new HashMap(colCount);
                for (int i = 1; i <= colCount; i++) {
                    rowData.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public void close() {
        try {
            if (c != null)
                c.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
