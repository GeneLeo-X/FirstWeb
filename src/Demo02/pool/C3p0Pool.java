package Demo02.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;


/**
 * c3p0-config.xml:自动识别的位置
 *
 * 1、早期防放于src根目录下即可
 * 2、新版本idea 的webapp 可能有一个resource目录，c3p0-config.xml 放于此处
 */

public class C3p0Pool {

    private static ComboPooledDataSource dataSource;

//    private static String driverClass = "";
//    private static String url = "";
//    private static String username = "";
//    private static String password = "";


    static {
        dataSource = new ComboPooledDataSource();
//        ResourceBundle rb = ResourceBundle.getBundle("Demo02/db");
//        driverClass = rb.getString("db.driverClass");
//        url = rb.getString("db.url");
//        username = rb.getString("db.username");
//        password = rb.getString("db.password");
//
//
//        try {
//            dataSource.setJdbcUrl(url);
//            dataSource.setPassword(password);
//            dataSource.setUser(username);
//            dataSource.setDriverClass(driverClass);
//            dataSource.seyMinPoolSize(5);
//            dataSource.setInitiaPoolSize(5);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }


    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
