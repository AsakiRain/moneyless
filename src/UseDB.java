import java.util.List;
import java.util.Map;

public class UseDB {
    DBUtils dbUtils;

    public UseDB() {
        this.dbUtils = new DBUtils();
    }

    public void init() {
        if (!dbUtils.hasTable("loginInfo")) {
            dbUtils.commit("CREATE TABLE loginInfo " +
                    "(uid varchar(15) NOT NULL, " +
                    "upw varchar(15) NOT NULL)");
            dbUtils.commit("INSERT INTO loginInfo VALUES('hdu', '123');");
        }
        if (!dbUtils.hasTable("IncomeAndSpending")) {
            dbUtils.commit("CREATE TABLE IncomeAndSpending " +
                    "(id varchar(10) NOT NULL, " +
                    "rdate char(8) NOT NULL, " +
                    "rtype char(4) NOT NULL, " +
                    "ritem  char(4) NOT NULL, " +
                    "bal  int NOT NULL)");
            dbUtils.commit("INSERT INTO IncomeAndSpending VALUES('2015080101','20150801','收入','工资',5500);" +
                    "INSERT INTO IncomeAndSpending VALUES('2015080102','20150801','支出','交通',100);" +
                    "INSERT INTO IncomeAndSpending VALUES('2015080301','20150803','支出','娱乐',200);" +
                    "INSERT INTO IncomeAndSpending VALUES('2015080401','20150804','支出','餐饮',55);");
        }
    }

    public boolean auth(String uid, String upwInput) {
        List res = dbUtils.query("SELECT upw FROM loginInfo WHERE uid='" + uid + "';");
        if (res.size() == 0) return false;
        Map data = (Map) res.get(0);
        String upw = data.get("upw").toString();
        return upw.equals(upwInput);
    }

    public void closeDB() {
        dbUtils.close();
    }

    public boolean updatePassword(String username, String upw, String newPWD) {
        boolean pass = this.auth(username, upw);
        if (pass) {
            dbUtils.commit("UPDATE logininfo " +
                    "SET upw = '" + newPWD + "' " +
                    "WHERE uid='" + username + "';");
            return true;
        }
        return false;
    }

    public Object[][] findByType(String rtype) {
        String sql;
        if (rtype.equals("全部")) {
            sql = "SELECT * FROM IncomeAndSpending;";
        } else {
            sql = "SELECT * FROM IncomeAndSpending WHERE rtype ='" + rtype + "';";
        }
        List res = dbUtils.query(sql);
        return parseList(res);
    }

    public Object[][] findByTimeRange(String fromDate, String toDate) {
        List res = dbUtils.query("SELECT * FROM IncomeAndSpending " +
                "WHERE rdate " +
                "BETWEEN '" + fromDate + "' and '" + toDate + "';");
        return parseList(res);
    }

    public int getBalance() {
        List res = dbUtils.query("SELECT * FROM IncomeAndSpending;");
        int balance = 0;
        for (int i = 0; i < res.size(); i++) {
            Map data = (Map) res.get(i);
            balance += Integer.parseInt(
                    data.get("bal").toString()) *
                    (data.get("rtype").equals("收入") ? 1 : -1);
        }
        return balance;
    }

    private Object[][] parseList(List res) {
        Object[][] row = new Object[res.size()][5];
        for (int i = 0; i < res.size(); i++) {
            Map data = (Map) res.get(i);
            row[i][0] = data.get("id");
            row[i][1] = data.get("rdate");
            row[i][2] = data.get("rtype");
            row[i][3] = data.get("ritem");
            row[i][4] = data.get("bal");
        }
        return row;
    }

    public void updateData(String id, String rdate, String rtype, String ritem, int bal) {
        dbUtils.commit("UPDATE IncomeAndSpending SET " +
                "rdate='" + rdate + "', " +
                "rtype='" + rtype + "', " +
                "ritem='" + ritem + "', " +
                "bal=" + bal + " " +
                "WHERE id='" + id + "';");
    }

    public void insertData(String id, String rdate, String rtype, String ritem, int bal) {
        dbUtils.commit("INSERT INTO IncomeAndSpending VALUES(" +
                "'" + id + "', " +
                "'" + rdate + "', " +
                "'" + rtype + "', " +
                "'" + ritem + "', " +
                bal + ");");
    }

    public void deleteData(String id) {
        dbUtils.commit("DELETE FROM IncomeAndSpending WHERE id='" + id + "';");
    }
}
