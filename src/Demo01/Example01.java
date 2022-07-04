package Demo01;


import java.sql.*;

/**
 * MySQL:8.0.11
 *
 */
public class Example01 {
    public static void main(String[] args) throws SQLException {
        testJdbc();
//        deleteStudentById("20200002");
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
                不需要在URL中添加时区
                Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lxc?","root","li123");

                MySQL8+版本时
                需要时区
                中国共5个时区：Asia/ShangHai（UTC+8）、Asia/Harbin...
                UTC:世界标准时间
                东八区（UTC+8）
                在数据库编码不是utf-8时是可以在url中加入"useUnicode=ture&characterEncoding=utf-8"解决中文乱码
             */
            Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lxc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Harbin","root","li123");

            //3、根据连接对象获取Statement  - 可以执行SQL语句的对象
            Statement stmt= conn.createStatement();

            //4、编写SQL语句
            //String sql = "select * from student";         //取出表中所有的数据（不推荐使用）
            String sql = "select sid , sname from student";   //按照表中列的名称取出数据


            //5、执行SQL语句,获取结果集对象
            ResultSet rs = stmt.executeQuery(sql);   //执行查询
            /*
                ResultSet对象表示查询结果集，只有在执行查询操作后才会有结果集产生;
                结果集是一个二维的表格有行有列;
                操作结果集要移动ResultSet内部的"行光标"，以获取当前行上的每一列上的数据;
                boolean next();使"行光标"移动到下一行，并返回移动后的行是否存在;
                XXX getXXX(int col):获取当前行指定列上的值，参数就是列数，列数从1开始，而不是0;

             */
            //数据库列的索引从1开始
            while (rs.next()){
                System.out.println(rs.getInt("id")+" "+rs.getString("name"));
            }



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }

    /*对表中的数据进行删除操作*/
    public static void deleteStudentById(String sid) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

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
                不需要在URL中添加时区
                Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lxc?","root","li123");

                MySQL8+版本时
                需要时区
                中国共5个时区：Asia/ShangHai（UTC+8）、Asia/Harbin...
                UTC:世界标准时间
                东八区（UTC+8）
                在数据库编码不是utf-8时是可以在url中加入"useUnicode=ture&characterEncoding=utf-8"解决中文乱码
             */
            conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/lxc?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Harbin","root","li123");

            //3、根据连接对象获取Statement  - 可以执行SQL语句的对象
            stmt = conn.createStatement();

            //4、编写SQL语句
            //String sql = "select * from student";         //取出表中所有的数据（不推荐使用）
            String sql = "delete from student where sid = " + sid ;   //按照表中列的名称取出数据


            //5、执行SQL语句,获取结果集对象
            int rows = stmt.executeUpdate(sql);   //执行查询
            System.out.println("rows:" + rows);



        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally{
            /*
                关闭
                与IO流一样，使用后的东西都需要关闭！关闭的顺序是先得到的后关闭，后得到的先关闭。
                rs。close();
                stmt.close();
                con.close();
             */
            stmt.close();
            conn.close();
        }

    }
}
