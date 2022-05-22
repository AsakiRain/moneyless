import java.util.List;
import java.util.Map;

public class UseDB {
    DBUtils dbUtils;

    public UseDB() {
        this.dbUtils = new DBUtils();
    }

    public void init() {
        List LIList = dbUtils.query("SELECT count(*) " +
                "FROM sqlite_master " +
                "WHERE type='table' " +
                "AND name='loginInfo';");

        Map LIData = (Map) LIList.get(0);
        int LICount = Integer.parseInt(LIData.get("count(*)").toString());

        if (LICount == 0) {
            dbUtils.commit("CREATE TABLE loginInfo " +
                    "(uid varchar(15) NOT NULL, " +
                    "upw varchar(15) NOT NULL)");
            dbUtils.commit("INSERT INTO loginInfo VALUES('hdu', '123');");
        }

        List IaSList = dbUtils.query("SELECT count(*) " +
                "FROM sqlite_master " +
                "WHERE type='table' " +
                "AND name='IncomeAndSpending';");

        Map IaSData = (Map) IaSList.get(0);
        int IaSCount = Integer.parseInt(IaSData.get("count(*)").toString());

        if (IaSCount == 0) {
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

    public void check() {
        List list = dbUtils.query("SELECT * FROM loginInfo;");
        for (Object rowData : list) {
            Map data = (Map) rowData;
            System.out.println(data.get("uid").toString());
            System.out.println(data.get("upw").toString());
        }
    }

    public void closeDB() {
        dbUtils.close();
    }
}
