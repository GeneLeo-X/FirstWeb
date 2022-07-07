package Demo02.pool;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 1、可以支持配置连接池容量、连接时间、支持多线程下连接（并发）
 * 2、没有独立的配置文件，需要使用属性文件读取相关信息
 */
public class DbcpPool {
    private static BasicDataSource dataSource;
    static {
        dataSource = new BasicDataSource();
        ResourceBundle rb = ResourceBundle.getBundle("Demo02/db");
        dataSource.setUrl(rb.getString("db.url"));
        dataSource.setDriverClassName(rb.getString("db.driverClass"));
        dataSource.setUsername(rb.getString("db.username"));
        dataSource.setPassword(rb.getString("db.password"));

    }
    public static Connection getConnection(){

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
