package Demo02;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class JdbcUtil {
    private static String driverClass = "";
    private static String url = "";
    private static String username = "";
    private static String password = "";


    static {
        // 读取配置文件的名称写错了。不要写后缀properties,它会自动读取 （名称 + .properties）这个文件。
        ResourceBundle rb = ResourceBundle.getBundle("Demo02/db");

        /* 从属性文件db.properties中获取信息 */
        driverClass = rb.getString("db.driverClass");
        url = rb.getString("db.url");
        username = rb.getString("db.username");
        password = rb.getString("db.password");

    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 释放资源
     */
    public static void releaseRes(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
