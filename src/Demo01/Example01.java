package Demo01;


import java.sql.*;

/**
 * MySQL:8.0.11
 *
 */
public class Example01 {
    public static void main(String[] args) {
        try {
            testJdbc();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void testJdbc() throws SQLException {

        try {
            //1、加载、注册驱动
            /*
                MySQL版本8以下时
                Class.forName("com.mysql.jdbc.Driver")
             */

            Class.forName("com.mysql.cj.jdbc.Driver");
            //2、使用DriverManager类去获取数据库连接
            /*
                MySQL版本8以下时
                Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lxc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","li123");
             */
            Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lxc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","li123");
            //3、根据连接对象获取Statement  - 可以执行SQL语句的对象
            Statement stmt= conn.createStatement();
            //4、编写SQL语句
            String sql = "select id , name from student";
            //5、执行SQL语句,获取结果集对象
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name"));
            }



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }
}
