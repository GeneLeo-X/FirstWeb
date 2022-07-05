package Demo02;

import Demo02.bean.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example02 {

    public static void main(String[] args) {
        User user = login("李展","123");
        if(user!=null){
            System.out.println(user.getName()+"已经成功登录");
        }
        else{
            System.out.println("用户名或密码错误");
        }

    }
    public static User login(String userName, String passWord){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.createStatement();
            //给表起别名  表名 别名
            String sql = "select u.uid,u.name from user u where u.username='"+userName+"' and password = '"+passWord+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                //user.setUserName(rs.getString("username"));
                user.setUid(rs.getInt("uid"));
                //user.setPassWord(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.releaseRes(conn,stmt,rs);
        }
        return user;

    }

}
