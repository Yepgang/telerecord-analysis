package tr.common.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
    private static final String MYSQL_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_URL = "jdbc:mysql://master:3306/ctanalysis?useUnicode=true&characterEncoding=UTF-8";
    private static final String MYSQL_USERNAME = "ct";
    private static final String MYSQL_PASSWORD = "mysql";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(MYSQL_DRIVER_CLASS);
            conn = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch ( Exception e ) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }

        return conn;

    }
}
